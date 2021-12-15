/**
 * RefreshTokenService is an interface that exposes public methods for usage inside the controllers.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.security.services.interfaces;

import com.example.AuctionApp.models.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {
    /**
     * findByToken checks if a particular refresh token exists in the database
     *
     * @param token token that we are looking for in the database
     * @return RefreshToken object if the token exists in database
     */
    Optional<RefreshToken> findByToken(String token);

    /**
     * createRefreshToken creates a new refresh token for the provided userId
     *
     * @param userId id of user that we are creating the refresh token for
     * @return RefreshToken
     */
    RefreshToken createRefreshToken(Long userId);

    /**
     * verifyExpiration verifies if the refresh token is still valid
     *
     * @param token refresh token that we are validating
     * @return token if the token is valid
     * @throws RefreshTokenException
     */
    RefreshToken verifyExpiration(RefreshToken token);

    /**
     * deleteByUsedId deletes all refresh tokens for a provided userId
     *
     * @param userId id that we want to delete refresh tokens for
     * @return number of refresh tokens deleted
     */
    int deleteByUserId(Long userId);
}
