/**
 * MessageResponse is a class used for constructing basic message response objects.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageResponse {
    private String message;
}
