/**
 * RefreshTokenRepository is a JPA repository used to query the refreshtoken database table.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.repository;

import com.example.AuctionApp.models.RefreshToken;
import com.example.AuctionApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByUser(User user);
}