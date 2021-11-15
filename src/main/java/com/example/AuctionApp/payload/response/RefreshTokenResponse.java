/**
 * RefreshTokenResponse is a class used for constructing response objects after
 * a successful JWT refresh request.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";

    public RefreshTokenResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
