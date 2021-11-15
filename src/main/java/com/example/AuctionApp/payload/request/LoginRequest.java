/**
 * LoginRequest is a class used for mapping incoming login requests.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class LoginRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}