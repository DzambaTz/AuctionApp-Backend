package com.example.AuctionApp.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class UpdatePersonalDetailsRequest {
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
    private String profilePhotoUrl;
}
