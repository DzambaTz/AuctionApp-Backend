package com.example.AuctionApp.repository;

import com.example.AuctionApp.models.Role;
import com.example.AuctionApp.models.Roles_enum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Roles_enum name);
}
