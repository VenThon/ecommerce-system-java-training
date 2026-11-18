package com.venthon.ecommerce.user.service;

import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface KeyCloakRoleService {
    void assignRoles(String userId, String roleNameWithDelimiter);
    List<UserRepresentation> getKeycloakUsersByRoleName(String roleName);
}
