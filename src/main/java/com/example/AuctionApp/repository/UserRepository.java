/**
 * UserRepository is a JPA repository used for querying the users database table.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.repository;

import com.example.AuctionApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
}