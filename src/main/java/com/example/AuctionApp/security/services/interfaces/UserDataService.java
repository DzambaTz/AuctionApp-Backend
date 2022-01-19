package com.example.AuctionApp.security.services.interfaces;

import com.example.AuctionApp.models.User;
import com.example.AuctionApp.payload.request.UpdatePersonalDetailsRequest;

import java.util.Optional;

public interface UserDataService {
    /**
     * getPersonalDetails method returns users personal info
     *
     * @param userId Id of the user that we are fetching the personal info for
     * @return User object that contains the users personal info
     */
    Optional<User> getPersonalDetails(Long userId);

    /**
     * updatePersonalDetails method is used for updating users personal info in the database
     *
     * @param userId Id of the user whose personal info we are updating
     * @param body   an object that contains the updated personal info
     * @return MessageResponse whether the change request was successful or not
     */
    void updatePersonalDetails(Long userId, UpdatePersonalDetailsRequest body);

    /**
     * deactivateUser method changes a specified user status to inactive
     *
     * @param userId ID of the user that we are deactivating
     * @return MessageResponse whether the deactivation request was successful or not
     */
    void deactivateUser(Long userId);
}
