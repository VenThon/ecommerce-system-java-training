package com.venthon.ecommerce.user.service.impl;

import com.venthon.ecommerce.user.config.KeycloakAdminClientConfig;
import com.venthon.ecommerce.user.dto.UserCreateRequest;
import com.venthon.ecommerce.user.dto.UserResponse;
import com.venthon.ecommerce.user.dto.UserUpdateRequest;
import com.venthon.ecommerce.user.entity.UserEntity;
import com.venthon.ecommerce.user.exception.PasswordMismatchException;
import com.venthon.ecommerce.user.exception.UsernameConflictException;
import com.venthon.ecommerce.user.mapper.UserMapper;
import com.venthon.ecommerce.user.repository.UserRepository;
import com.venthon.ecommerce.user.service.UserService;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final Keycloak keycloak;

    @Override
    public UserResponse create(UserCreateRequest userCreateRequest) {
        UserEntity userEntity = userMapper.toEntity(userCreateRequest);

        // Validate username
        if (userRepository.existsByUsername(userCreateRequest.username())) {
            throw new UsernameConflictException(HttpStatus.CONFLICT,
                    "Username already exists");
        }

        // Validate password
        if (!userCreateRequest.password().equals(userCreateRequest.confirmedPassword())) {
            throw new PasswordMismatchException(HttpStatus.BAD_REQUEST,
                    "Password does not match");
        }

        // Save user into Keycloak
        // After saved user, save user into DB
        UserRepresentation userRepresentation = userMapper.toRepresentation(userCreateRequest);
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(false);

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(userCreateRequest.password());

        userRepresentation.setCredentials(List.of(credentialRepresentation));

        try (Response response = keycloak.realm("ecommerce")
                .users()
                .create(userRepresentation)) {
            log.info("Keycloak response: {}", response.getStatus());
            if (response.getStatus() == HttpStatus.CREATED.value()) {
                log.info("Created user");
            } else if (response.getStatus() == HttpStatus.CONFLICT.value()) {
                log.info("Keycloak user data conflict");
            } else {
                log.info("Cannot create user");
            }
        }



        return null;
    }



    @Override
    @Transactional
    public UserResponse update(String id, UserUpdateRequest request) {

        // 1. Find user in database
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found with ID: " + id
                ));

        // 2. Validate username
        if (!userEntity.getUsername().equals(request.username())
                && userRepository.existsByUsername(request.username())) {

            throw new UsernameConflictException(
                    HttpStatus.CONFLICT,
                    "Username already exists: " + request.username()
            );
        }

        // 3. Validate password
        boolean changePassword = request.password() != null
                && !request.password().isBlank();

        if (changePassword &&
                !request.password().equals(request.confirmedPassword())) {

            throw new PasswordMismatchException(
                    HttpStatus.BAD_REQUEST,
                    "Password and confirmed password do not match"
            );
        }

        // 4. Find user in Keycloak
        // Assumes database ID equals Keycloak user ID
        var keycloakUser = keycloak.realm(
                        KeycloakAdminClientConfig.REALM_NAME
                )
                .users()
                .get(userEntity.getId());

        // 5. Update profile in Keycloak
        UserRepresentation representation =
                keycloakUser.toRepresentation();

        representation.setUsername(request.username());
        representation.setEmail(request.email());
        representation.setFirstName(request.givenName());
        representation.setLastName(request.familyName());

        keycloakUser.update(representation);

        // 6. Update password in Keycloak if provided
        if (changePassword) {
            CredentialRepresentation credential =
                    new CredentialRepresentation();

            credential.setType(CredentialRepresentation.PASSWORD);
            credential.setValue(request.password());
            credential.setTemporary(false);

            keycloakUser.resetPassword(credential);
        }

        // 7. Update local database
        userEntity.setUsername(request.username());

        UserEntity updatedUser = userRepository.save(userEntity);

        log.info("User updated successfully: {}", id);

        // 8. Return mapped response
        return userMapper.toResponse(updatedUser);
    }



}
