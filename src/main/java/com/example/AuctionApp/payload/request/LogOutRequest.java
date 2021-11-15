/**
 * LogOutRequest is a class used for mapping of incoming logout requests.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.payload.request;

import lombok.Getter;

@Getter
public class LogOutRequest {
    private Long userId;
}