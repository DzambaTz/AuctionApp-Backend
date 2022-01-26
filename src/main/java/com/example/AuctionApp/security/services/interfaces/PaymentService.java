package com.example.AuctionApp.security.services.interfaces;

import com.example.AuctionApp.payload.request.ItemPaymentRequest;
import com.example.AuctionApp.security.services.implementations.UserDetailsImpl;
import com.stripe.exception.StripeException;

public interface PaymentService {
    String createCheckoutSession(UserDetailsImpl userDetails, ItemPaymentRequest itemData) throws StripeException;
}
