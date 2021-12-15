/**
 * UserAuthService is an interface that exposes public methods for usage inside the controllers.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.security.services.interfaces;

import com.example.AuctionApp.payload.request.LogOutRequest;
import com.example.AuctionApp.payload.request.LoginRequest;
import com.example.AuctionApp.payload.request.SignupRequest;
import com.example.AuctionApp.payload.request.RefreshTokenRequest;
import org.springframework.http.ResponseEntity;

public interface UserAuthService {
    /**
     * signInUser signs tries to sign in the user with provided credentials
     *
     * @param loginRequest object that contains sign in credentials
     * @return MessageResponse if the sign in request was successful or not
     */
    ResponseEntity<?> signInUser(LoginRequest loginRequest);

    /**
     * signUpUser tries to sign up a user with provided data
     *
     * @param signupRequest object that contains sign up data
     * @return MessageResponse if the sign up request was successful or not
     */
    ResponseEntity<?> signUpUser(SignupRequest signupRequest);

    /**
     * refreshUserToken tries to refresh a users JWT using the provided refresh token
     *
     * @param request object that contains a refresh token of the user we are refreshing the JWT for
     * @return RefreshTokenResponse with new JWT and refresh token
     */
    ResponseEntity<?> refreshUserToken(RefreshTokenRequest request);

    /**
     * logoutUser logs out user from an active session
     *
     * @param logOutRequest object that contains the id of the user trying to log out
     * @return MessageResponse if the logout request was successful or not
     */
    ResponseEntity<?> logoutUser(LogOutRequest logOutRequest);
}
