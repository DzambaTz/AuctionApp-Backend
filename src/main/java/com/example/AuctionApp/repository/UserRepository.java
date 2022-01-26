/**
 * UserRepository is a JPA repository used for querying the users database table.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.repository;

import com.example.AuctionApp.models.User;
import com.example.AuctionApp.payload.request.UpdatePersonalDetailsRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    Optional<User> findUsersById(Long userId);

    @Modifying
    @Transactional
    default void changePersonalInfo(Long userId, UpdatePersonalDetailsRequest body){
        Optional<User> queryResult = findUsersById(userId);
        if(queryResult.isPresent()){
            User user = queryResult.get();
            if(body.getFirstName() != null){
                user.setFirstName(body.getFirstName());
            }
            if(body.getLastName() != null){
                user.setLastName(body.getLastName());
            }
            if(body.getPhoneNumber() != null){
                user.setPhoneNumber(body.getPhoneNumber());
            }
            if(body.getGender() != null){
                user.setGender(body.getGender());
            }
            if(body.getDateOfBirth() != null){
                user.setDateOfBirth(body.getDateOfBirth());
            }
            if(body.getStreetAddress() != null){
                user.setStreetAddress(body.getStreetAddress());
            }
            if(body.getCity() != null){
                user.setCity(body.getCity());
            }
            if(body.getZipCode() != null){
                user.setZipCode(body.getZipCode());
            }
            if(body.getState() != null){
                user.setState(body.getState());
            }
            if(body.getCountry() != null){
                user.setCountry(body.getCountry());
            }
            if(body.getNameOnCard() != null){
                user.setNameOnCard(body.getNameOnCard());
            }
            if(body.getCardNumber() != null){
                user.setCardNumber(body.getCardNumber());
            }
            if(body.getExpirationDate() != null){
                user.setExpirationDate(body.getExpirationDate());
            }
            if(body.getCvv() != null){
                user.setCvv(body.getCvv());
            }
            if(body.getProfilePhotoUrl() != null){
                user.setProfilePhoto(body.getProfilePhotoUrl());
            }
            save(user);
        }
    }

    @Modifying
    @Transactional
    @Query(
            value = "UPDATE users SET is_active = false WHERE id = ?1",
            nativeQuery = true
    )
    void deactivateUser(Long id);

    @Query(
            value = "SELECT is_active FROM users WHERE id = ?1",
            nativeQuery = true
    )
    Boolean getUserStatus(Long id);
}