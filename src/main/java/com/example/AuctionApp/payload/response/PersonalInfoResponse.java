package com.example.AuctionApp.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Tuple;

@Getter
@Setter
@AllArgsConstructor
public class PersonalInfoResponse {
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String gender;
    private String dateOfBirth;
    private String streetAddress;
    private String city;
    private String zipCode;
    private String state;
    private String country;
    private String nameOnCard;
    private String cardNumber;
    private String expirationDate;
    private String cvv;

    public PersonalInfoResponse(Tuple queryResult){
        email = (String) queryResult.get(0);
        firstName = (String) queryResult.get(1);
        lastName = (String) queryResult.get(2);
        phoneNumber = (String) queryResult.get(3);
        nameOnCard = (String) queryResult.get(4);
        cardNumber = (String) queryResult.get(5);
        expirationDate = (String) queryResult.get(6);
        cvv = (String) queryResult.get(7);
        streetAddress = (String) queryResult.get(8);
        city = (String) queryResult.get(9);
        zipCode = (String) queryResult.get(10);
        state = (String) queryResult.get(11);
        country = (String) queryResult.get(12);
        dateOfBirth = (String) queryResult.get(13);
        gender = (String) queryResult.get(14);
    }
}
