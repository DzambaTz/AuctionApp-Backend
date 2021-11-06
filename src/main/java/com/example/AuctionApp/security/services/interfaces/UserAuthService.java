package com.example.AuctionApp.security.services.interfaces;

import com.example.AuctionApp.payload.request.LogOutRequest;
import com.example.AuctionApp.payload.request.LoginRequest;
import com.example.AuctionApp.payload.request.SignupRequest;
import com.example.AuctionApp.payload.request.RefreshTokenRequest;
import org.springframework.http.ResponseEntity;

public interface UserAuthService {
    ResponseEntity<?> signInUser(LoginRequest loginRequest);
    ResponseEntity<?> signUpUser(SignupRequest signupRequest);
    ResponseEntity<?> refreshUserToken(RefreshTokenRequest request);
    ResponseEntity<?> logoutUser(LogOutRequest logOutRequest);
}
