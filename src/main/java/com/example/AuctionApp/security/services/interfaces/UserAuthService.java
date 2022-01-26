/**
 * UserAuthService is an interface that exposes public methods for usage inside the controllers.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.security.services.interfaces;

import com.example.AuctionApp.exception.UserAuthExceptions.EmailAlreadyInUseException;
import com.example.AuctionApp.exception.UserAuthExceptions.UserDeactivatedException;
import com.example.AuctionApp.payload.request.LogOutRequest;
import com.example.AuctionApp.payload.request.LoginRequest;
import com.example.AuctionApp.payload.request.RefreshTokenRequest;
import com.example.AuctionApp.payload.request.SignupRequest;
import com.example.AuctionApp.payload.response.JwtResponse;
import com.example.AuctionApp.payload.response.MessageResponse;
import com.example.AuctionApp.payload.response.RefreshTokenResponse;
import org.springframework.http.ResponseEntity;

public interface UserAuthService {
    /**
     * signInUser signs tries to sign in the user with provided credentials
     *
     * @param loginRequest object that contains sign in credentials
     * @return MessageResponse if the sign in request was successful or not
     */
    JwtResponse signInUser(LoginRequest loginRequest) throws UserDeactivatedException;

    /**
     * signUpUser tries to sign up a user with provided data
     *
     * @param signupRequest object that contains sign up data
     * @return MessageResponse if the sign up request was successful or not
     */
    MessageResponse signUpUser(SignupRequest signupRequest) throws EmailAlreadyInUseException;

    /**
     * refreshUserToken tries to refresh a users JWT using the provided refresh token
     *
     * @param request object that contains a refresh token of the user we are refreshing the JWT for
     * @return RefreshTokenResponse with new JWT and refresh token
     */
    RefreshTokenResponse refreshUserToken(RefreshTokenRequest request);

    /**
     * logoutUser logs out user from an active session
     *
     * @param logOutRequest object that contains the id of the user trying to log out
     * @return MessageResponse if the logout request was successful or not
     */
    MessageResponse logoutUser(LogOutRequest logOutRequest);
}
