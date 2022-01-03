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

    /**
     * changeProfilePhoto method takes a user id and an image URL as a parameter, and updates the users
     * profile image URL in the database.
     *
     * @param userId Id of the user whose profile photo we are changing
     * @param body an object that just contains the new profile image URL
     * @return MessageResponse whether the change request was successful or not
     */
    ResponseEntity<?> changeProfilePhoto(Long userId, PhotoChangeRequest body);

    /**
     * getPersonalInfo method returns users personal info
     *
     * @param userId Id of the user that we are fetching the personal info for
     * @return PersonalInfoResponse that contains the users personal info
     */
    ResponseEntity<?> getPersonalInfo(Long userId);

    /**
     * changePersonalInfo method is used for updating users personal info in the database
     *
     * @param userId Id of the user whose personal info we are updating
     * @param body an object that contains the updated personal info
     * @return MessageResponse whether the change request was successful or not
     */
    ResponseEntity<?> changePersonalInfo(Long userId, PersonalInfoChangeRequest body);

    /**
     * deactivateUser method changes a specified user status to inactive
     *
     * @param jwt JWT of the user that we are deactivating
     * @return MessageResponse whether the deactivation request was successful or not
     */
    ResponseEntity<?> deactivateUser(String jwt);
}
