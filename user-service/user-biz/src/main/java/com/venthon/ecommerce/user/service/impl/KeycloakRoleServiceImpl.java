package com.venthon.ecommerce.user.service.impl;

import com.venthon.ecommerce.user.config.KeycloakAdminClientConfig;
import com.venthon.ecommerce.user.exception.KeycloakException;
import com.venthon.ecommerce.user.service.KeyCloakRoleService;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class KeycloakRoleServiceImpl implements KeyCloakRoleService {

    private final Keycloak keycloak;

    @Override
    public void assignRoles(String userId, String roleNameWithDelimiter) {

        String[] roleNameList = roleNameWithDelimiter.split(",");
        List<RoleRepresentation> roleRepresentationList = Arrays.stream(roleNameList)
                .map(this::getKeycloakRoleByName)
                .toList();

        keycloak.realm(KeycloakAdminClientConfig.REALM_NAME)
                .users()
                .get(userId)
                .roles()
                .realmLevel()
                .add(roleRepresentationList);
    }


    private RoleRepresentation getKeycloakRoleByName(String roleName) {
        return keycloak.realm(KeycloakAdminClientConfig.REALM_NAME)
                .roles()
                .get(roleName)
                .toRepresentation();
    }

    @Override
    public List<UserRepresentation> getKeycloakUsersByRoleName(String roleName) {
        try {
            return keycloak.realm(KeycloakAdminClientConfig.REALM_NAME)
                    .roles()
                    .get(roleName)
                    .getUserMembers();
        } catch (NotFoundException e) {
            throw new KeycloakException(HttpStatus.NOT_FOUND, "Role " + roleName + " not found");
        }
    }

}
