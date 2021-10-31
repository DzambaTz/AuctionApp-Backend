package com.example.AuctionApp.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String email;
    private String first_name;
    private String last_name;
    private List<String> roles;

    public JwtResponse(String token, Long id, String email, String first_name, String last_name, List<String> roles) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.roles = roles;
    }
}
