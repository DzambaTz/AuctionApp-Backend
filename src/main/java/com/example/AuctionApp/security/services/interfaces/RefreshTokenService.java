/**
 * RefreshTokenService is an interface that exposes public methods for usage inside the controllers.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.security.services.interfaces;

import com.example.AuctionApp.models.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {
    Optional<RefreshToken> findByToken(String token);
    RefreshToken createRefreshToken(Long userId);
    RefreshToken verifyExpiration(RefreshToken token);
    int deleteByUserId(Long userId);
}
