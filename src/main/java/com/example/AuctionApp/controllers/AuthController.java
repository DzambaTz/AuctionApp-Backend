/**
 * AuthController is a class that provides API endpoints for user login, registration,
 * logout, and JWT refreshing. All the actual logic is done inside the UserAuthService
 * and this controller serves merely as a middleware.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.controllers;

import com.example.AuctionApp.exception.UserAuthExceptions.UserAuthException;
import com.example.AuctionApp.exception.UserAuthExceptions.UserDeactivatedException;
import com.example.AuctionApp.payload.request.LogOutRequest;
import com.example.AuctionApp.payload.request.LoginRequest;
import com.example.AuctionApp.payload.request.RefreshTokenRequest;
import com.example.AuctionApp.payload.request.SignupRequest;
import com.example.AuctionApp.payload.response.MessageResponse;
import com.example.AuctionApp.payload.response.RefreshTokenResponse;
import com.example.AuctionApp.security.services.interfaces.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    UserAuthService userAuthService;

    @PostMapping("/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            return ResponseEntity.ok(userAuthService.signInUser(loginRequest));
        } catch (UserAuthException exception) {
            return ResponseEntity.status(exception.getStatus()).body(new MessageResponse(exception.getMessage()));
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        try {
            return ResponseEntity.ok(userAuthService.signUpUser(signUpRequest));
        } catch (UserAuthException exception){
            return ResponseEntity.status(exception.getStatus()).body(new MessageResponse(exception.getMessage()));
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(userAuthService.refreshUserToken(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<MessageResponse> logoutUser(@Valid @RequestBody LogOutRequest logOutRequest) {
        return ResponseEntity.ok(userAuthService.logoutUser(logOutRequest));
    }
}
