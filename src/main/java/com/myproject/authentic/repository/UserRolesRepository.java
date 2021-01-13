package com.myproject.authentic.repository;

import com.myproject.authentic.model.UserRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRolesRepository extends JpaRepository<UserRolesEntity, Long> {
    List<UserRolesEntity> findByUserId(Long userId);
}
