package com.springboot.userservice.repository;

import com.springboot.userservice.entity.AppPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AppPermissionRepository extends JpaRepository<AppPermission, Long> {
    AppPermission findByName(String name);
}
