package com.venthon.ecommerce.user.service;

import org.keycloak.representations.idm.UserRepresentation;

public interface KeycloakUserService {

    void createKeycloakUser(UserRepresentation userRepresentation);

    UserRepresentation getKeycloakUserByUsername(String username);

}

