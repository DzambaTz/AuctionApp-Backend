/**
 * ErrorMessage class that is used for constructing response objects after error handling
 * token refreshing requests in TokenControllerAdvice class.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;


@Getter
@AllArgsConstructor
public class ErrorMessage {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;
}