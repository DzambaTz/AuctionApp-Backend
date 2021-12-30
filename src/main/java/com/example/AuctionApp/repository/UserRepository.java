/**
 * UserRepository is a JPA repository used for querying the users database table.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.repository;

import com.example.AuctionApp.models.User;
import com.example.AuctionApp.payload.request.PersonalInfoChangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Query(
            value = "SELECT profile_photo FROM users WHERE id = :userId",
            nativeQuery = true
    )
    String getProfilePhoto(Long userId);

    @Modifying
    @Transactional
    @Query(
            value = "UPDATE users SET profile_photo = :url WHERE id = :userId",
            nativeQuery = true
    )
    void changeProfilePhoto(Long userId, String url);

    @Query(
            value = "SELECT email,first_name,last_name,phone_number,name_on_card,card_number,expiration_date,cvv," +
                    "street_address,city,zip_code,state,country,date_of_birth,gender FROM users WHERE id = :userId",
            nativeQuery = true
    )
    Optional<Tuple> getPersonalInfo(Long userId);

    @Modifying
    @Transactional
    @Query(
            value = "UPDATE users SET first_name = :#{#body.firstName},last_name = :#{#body.lastName},phone_number = :#{#body.phoneNumber}," +
                    "name_on_card = :#{#body.nameOnCard},card_number = :#{#body.cardNumber},expiration_date = :#{#body.expirationDate}," +
                    "cvv = :#{#body.cvv},street_address = :#{#body.streetAddress},city = :#{#body.city},zip_code = :#{#body.zipCode}," +
                    "state = :#{#body.state},country = :#{#body.country},date_of_birth = :#{#body.dateOfBirth},gender = :#{#body.gender} " +
                    "WHERE id = :userId",
            nativeQuery = true
    )
    void changePersonalInfo(Long userId, PersonalInfoChangeRequest body);

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