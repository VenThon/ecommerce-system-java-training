package com.venthon.ecommerce.user.service;

import com.venthon.ecommerce.user.dto.UserCreateRequest;
import com.venthon.ecommerce.user.dto.UserResponse;
import com.venthon.ecommerce.user.dto.UserUpdateRequest;

import java.util.List;

public interface UserService {

    UserResponse create(UserCreateRequest userCreateRequest);

    UserResponse update(String id, UserUpdateRequest request);

    List<UserResponse> getUsersByRoleName(String roleName);

    List<UserResponse> getUsers();

    UserResponse getUserById(String id);

    UserResponse getUserByUsername(String username);

    void delete(String id);

}
