package com.example.AuctionApp.security.services.implementations;

import com.example.AuctionApp.models.User;
import com.example.AuctionApp.payload.request.UpdatePersonalDetailsRequest;
import com.example.AuctionApp.repository.UserRepository;
import com.example.AuctionApp.security.jwt.JwtUtils;
import com.example.AuctionApp.security.services.interfaces.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDataServiceImpl implements UserDataService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public Optional<User> getPersonalDetails(Long userId) {
        return userRepository.findUsersById(userId);
    }

    @Override
    public void updatePersonalDetails(Long userId, UpdatePersonalDetailsRequest body) {
        userRepository.changePersonalInfo(userId, body);
    }

    @Override
    public void deactivateUser(Long userId) {
        userRepository.deactivateUser(userId);
    }
}
