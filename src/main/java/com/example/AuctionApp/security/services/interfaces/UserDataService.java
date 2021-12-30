package com.example.AuctionApp.security.services.interfaces;

import com.example.AuctionApp.payload.request.PersonalInfoChangeRequest;
import com.example.AuctionApp.payload.request.PhotoChangeRequest;
import org.springframework.http.ResponseEntity;

public interface UserDataService {
    /**
     * getProfilePhoto method returns an image URL of the users profile photo
     *
     * @param userId Id of the user that we are fetching the profile photo for
     * @return URL of users profile image
     */
    ResponseEntity<?> getProfilePhoto(Long userId);

    ResponseEntity<?> changeProfilePhoto(Long userId, PhotoChangeRequest body);

    ResponseEntity<?> getPersonalInfo(Long userId);

    ResponseEntity<?> changePersonalInfo(Long userId, PersonalInfoChangeRequest body);

    ResponseEntity<?> deactivateUser(String jwt);
}
