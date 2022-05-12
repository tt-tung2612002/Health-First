package com.springboot.userservice.repository;

import com.springboot.userservice.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
