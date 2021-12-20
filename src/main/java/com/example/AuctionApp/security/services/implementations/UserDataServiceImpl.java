package com.example.AuctionApp.security.services.implementations;

import com.example.AuctionApp.models.User;
import com.example.AuctionApp.payload.request.PersonalInfoChangeRequest;
import com.example.AuctionApp.payload.request.PhotoChangeRequest;
import com.example.AuctionApp.payload.response.MessageResponse;
import com.example.AuctionApp.payload.response.PersonalInfoResponse;
import com.example.AuctionApp.repository.UserRepository;
import com.example.AuctionApp.security.services.interfaces.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.Optional;

@Service
public class UserDataServiceImpl implements UserDataService {
    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<?> getProfilePhoto(Long userId) {
        final Optional<String> imageURL= Optional.ofNullable(userRepository.getProfilePhoto(userId));
        return  ResponseEntity.ok(new MessageResponse(imageURL.get()));
    }

    @Override
    public ResponseEntity<?> changeProfilePhoto(Long userId, PhotoChangeRequest body) {
        userRepository.changeProfilePhoto(userId, body.getUrl());
        return ResponseEntity.ok(new MessageResponse("Profile picture successfully changed!"));
    }

    @Override
    public ResponseEntity<?> getPersonalInfo(Long userId) {
        Optional<Tuple> queryResult = userRepository.getPersonalInfo(userId);
        PersonalInfoResponse userInfo = new PersonalInfoResponse(queryResult.get());
        return ResponseEntity.ok(userInfo);
    }

    @Override
    public ResponseEntity<?> changePersonalInfo(Long userId, PersonalInfoChangeRequest body) {
        userRepository.changePersonalInfo(userId, body);
        return ResponseEntity.ok(new MessageResponse("Info successfully updated!"));
    }
}
