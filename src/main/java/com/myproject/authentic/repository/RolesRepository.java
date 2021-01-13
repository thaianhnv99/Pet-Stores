package com.myproject.authentic.repository;

import com.myproject.authentic.model.ERole;
import com.myproject.authentic.model.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<RolesEntity, Long> {
    Optional<RolesEntity> findByName(ERole name);

    Optional<RolesEntity> findById(Long id);
}
