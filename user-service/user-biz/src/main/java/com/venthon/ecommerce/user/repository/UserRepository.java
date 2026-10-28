package com.venthon.ecommerce.user.repository;


import com.venthon.ecommerce.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    boolean existsByUsername(String username);

}

