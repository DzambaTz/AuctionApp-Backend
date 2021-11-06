package com.example.AuctionApp.controllers;

import com.example.AuctionApp.payload.request.LogOutRequest;
import com.example.AuctionApp.payload.request.LoginRequest;
import com.example.AuctionApp.payload.request.RefreshTokenRequest;
import com.example.AuctionApp.payload.request.SignupRequest;
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

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return userAuthService.signInUser(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
       return userAuthService.signUpUser(signUpRequest);
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        return userAuthService.refreshUserToken(request);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@Valid @RequestBody LogOutRequest logOutRequest) {
        return userAuthService.logoutUser(logOutRequest);
    }
}
