/**
 * RefreshTokenRequest is a class used for mapping incoming JWT refresh requests.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RefreshTokenRequest {
    @NotBlank
    private String refreshToken;
}
