package com.venthon.ecommerce.user.service;

public interface KeyCloakRoleService {
    void assignRoles(String userId, String roleNameWithDelimiter);
}
