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
import com.venthon.ecommerce.user.service.KeyCloakRoleService;
import com.venthon.ecommerce.user.service.UserService;

import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final Keycloak keycloak;
    private final KeyCloakRoleService keyCloakRoleService;

    private static final String REALM_NAME =
            KeycloakAdminClientConfig.REALM_NAME;

    // =========================
    // 1. CREATE USER
    // =========================
    @Override
    public UserResponse create(UserCreateRequest request) {

        if (userRepository.existsByUsername(request.username())) {
            throw new UsernameConflictException(
                    HttpStatus.CONFLICT,
                    "Username already exists"
            );
        }

        if (!request.password().equals(request.confirmedPassword())) {
            throw new PasswordMismatchException(
                    HttpStatus.BAD_REQUEST,
                    "Password does not match"
            );
        }

        UserEntity userEntity = userMapper.toEntity(request);

        UserRepresentation representation =
                userMapper.toRepresentation(request);

        representation.setEnabled(true);
        representation.setEmailVerified(false);

        CredentialRepresentation credential =
                new CredentialRepresentation();

        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(request.password());
        credential.setTemporary(false);

        representation.setCredentials(List.of(credential));

        String keycloakUserId;

        try (Response response = keycloak.realm(REALM_NAME)
                .users()
                .create(representation)) {

            if (response.getStatus() == HttpStatus.CONFLICT.value()) {
                throw new UsernameConflictException(
                        HttpStatus.CONFLICT,
                        "Username or email already exists in Keycloak"
                );
            }

            if (response.getStatus() != HttpStatus.CREATED.value()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_GATEWAY,
                        "Keycloak user creation failed. Status: "
                                + response.getStatus()
                );
            }

            keycloakUserId = CreatedResponseUtil.getCreatedId(response);
        }

        // Assign Keycloak UUID to local entity
        userEntity.setId(keycloakUserId);

        try {
            // Assign Keycloak realm roles
            keyCloakRoleService.assignRoles(
                    keycloakUserId,
                    request.roleId()
            );

            // Save local user
            UserEntity savedUser = userRepository.save(userEntity);

            log.info("Created user: {}", keycloakUserId);

            return userMapper.toResponse(savedUser);

        } catch (Exception ex) {
            log.error(
                    "User provisioning failed for {}. Reconciliation required.",
                    keycloakUserId,
                    ex
            );
            throw ex;
        }
    }

    // =========================
    // 2. UPDATE USER
    // =========================
    @Override
    @Transactional
    public UserResponse update(String id, UserUpdateRequest request) {

        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found with ID: " + id
                ));

        if (!userEntity.getUsername().equals(request.username())
                && userRepository.existsByUsername(request.username())) {
            throw new UsernameConflictException(
                    HttpStatus.CONFLICT,
                    "Username already exists"
            );
        }

        boolean changePassword = request.password() != null
                && !request.password().isBlank();

        if (changePassword
                && !request.password().equals(request.confirmedPassword())) {
            throw new PasswordMismatchException(
                    HttpStatus.BAD_REQUEST,
                    "Password and confirmed password do not match"
            );
        }

        var keycloakUser = keycloak.realm(REALM_NAME)
                .users()
                .get(id);

        UserRepresentation representation =
                keycloakUser.toRepresentation();

        representation.setUsername(request.username());
        representation.setEmail(request.email());
        representation.setFirstName(request.givenName());
        representation.setLastName(request.familyName());

        keycloakUser.update(representation);

        if (changePassword) {
            CredentialRepresentation credential =
                    new CredentialRepresentation();

            credential.setType(CredentialRepresentation.PASSWORD);
            credential.setValue(request.password());
            credential.setTemporary(false);

            keycloakUser.resetPassword(credential);
        }

        userEntity.setUsername(request.username());

        UserEntity updatedUser = userRepository.save(userEntity);

        log.info("Updated user: {}", id);

        return userMapper.toResponse(updatedUser);
    }

    // =========================
    // 3. GET USERS BY ROLE NAME
    // =========================
    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getUsersByRoleName(String roleName) {

        List<UserResponse> users = new ArrayList<>();

        var roleResource = keycloak.realm(REALM_NAME)
                .roles()
                .get(roleName);

        int pageSize = 100;

        for (int offset = 0; ; offset += pageSize) {

            List<UserRepresentation> members =
                    roleResource.getUserMembers(offset, pageSize);

            for (UserRepresentation member : members) {

                userRepository.findById(member.getId())
                        .map(userMapper::toResponse)
                        .ifPresent(users::add);
            }

            if (members.size() < pageSize) {
                break;
            }
        }

        return users;
    }

    // =========================
    // 4. GET ALL USERS
    // =========================
    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {

        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    // =========================
    // 5. GET USER BY ID
    // =========================
    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(String id) {

        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found with ID: " + id
                ));

        return userMapper.toResponse(userEntity);
    }

    // =========================
    // 6. GET USER BY USERNAME
    // =========================
    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserByUsername(String username) {

        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found with username: " + username
                ));

        return userMapper.toResponse(userEntity);
    }

    // =========================
    // 7. DELETE USER
    // =========================
    @Override
    @Transactional
    public void delete(String id) {

        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found with ID: " + id
                ));

        try (Response response = keycloak.realm(REALM_NAME)
                .users()
                .delete(id)) {

            if (response.getStatus() != HttpStatus.NO_CONTENT.value()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_GATEWAY,
                        "Failed to delete Keycloak user. Status: "
                                + response.getStatus()
                );
            }
        }

        userRepository.delete(userEntity);

        log.info("Deleted user: {}", id);
    }
}
