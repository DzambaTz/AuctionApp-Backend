/**
 * JwtResponse class is used for constructing a response object after user signup/signin requests.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String refreshToken;
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private List<String> roles;

    public JwtResponse(String token, Long id, String refreshToken, String email, String firstName, String lastName, List<String> roles) {
        this.token = token;
        this.id = id;
        this.refreshToken = refreshToken;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }
}
