package com.example.AuctionApp.controllers;

import com.example.AuctionApp.exception.UserAuthExceptions.UserAuthException;
import com.example.AuctionApp.payload.request.ItemPaymentRequest;
import com.example.AuctionApp.payload.response.MessageResponse;
import com.example.AuctionApp.security.services.implementations.UserDetailsImpl;
import com.example.AuctionApp.security.services.interfaces.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/create-checkout-session")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<MessageResponse> createCheckoutSession(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody ItemPaymentRequest itemData) throws StripeException {
        try {
            return ResponseEntity.ok(new MessageResponse(paymentService.createCheckoutSession(userDetails, itemData)));
        } catch (StripeException exception) {
            return ResponseEntity.status(exception.getStatusCode()).body(new MessageResponse(exception.getMessage()));
        } catch (UserAuthException exception) {
            return ResponseEntity.status(exception.getStatusCode()).body(new MessageResponse(exception.getMessage()));
        }
    }
}
