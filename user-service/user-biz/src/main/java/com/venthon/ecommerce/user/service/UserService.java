package com.venthon.ecommerce.user.service;

import com.venthon.ecommerce.user.dto.UserCreateRequest;
import com.venthon.ecommerce.user.dto.UserResponse;

public interface UserService {

    UserResponse create(UserCreateRequest userCreateRequest);

}
