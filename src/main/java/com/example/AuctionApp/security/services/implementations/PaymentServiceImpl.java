package com.example.AuctionApp.security.services.implementations;

import com.example.AuctionApp.models.User;
import com.example.AuctionApp.payload.request.ItemPaymentRequest;
import com.example.AuctionApp.repository.UserRepository;
import com.example.AuctionApp.security.services.interfaces.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.checkout.Session;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {


    @Autowired
    UserRepository userRepository;

    @Value("${auction.app.stripeApiKey}")
    private String stripeApiKey;

    @Value("${auction.app.successUrl}")
    private String successUrl;

    @Value("${auction.app.cancelUrl}")
    private String cancelUrl;


    @Override
    public String createCheckoutSession(UserDetailsImpl userDetails, ItemPaymentRequest itemData) throws StripeException {
        System.out.println(itemData);
        Stripe.apiKey = stripeApiKey;
        final Optional<User> userData = userRepository.findUsersById(userDetails.getId());
        if (userData.isPresent()) {
            final User user = userData.get();
            CustomerCreateParams customerParams = CustomerCreateParams.builder()
                    .setName(user.getNameOnCard())
                    .setEmail(user.getEmail())
                    .setPhone(user.getPhoneNumber())
                    .setShipping(CustomerCreateParams.Shipping.builder()
                            .setName(user.getFirstName() + " " + user.getLastName())
                            .setAddress(CustomerCreateParams.Shipping.Address.builder()
                                    .setCity(user.getCity())
                                    .setCountry(user.getCountry())
                                    .setPostalCode(user.getZipCode())
                                    .setState(user.getState())
                                    .setLine1(user.getStreetAddress())
                                    .build()
                            )
                            .build()
                    )
                    .build();

            final Customer customer = Customer.create(customerParams);

            SessionCreateParams sessionParams = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl(successUrl)
                    .setCancelUrl(cancelUrl)
                    .setCustomer(customer.getId())
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setQuantity(1L)
                                    .setPriceData(
                                            SessionCreateParams.LineItem.PriceData.builder()
                                                    .setCurrency("usd")
                                                    .setUnitAmount(itemData.getPaymentAmount())
                                                    .setProductData(
                                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                    .setName(itemData.getName())
                                                                    .addImage(itemData.getImageUrl())
                                                                    .build())
                                                    .build())
                                    .build())
                    .build();

            final Session session = Session.create(sessionParams);

            return session.getUrl();
        } else {
            throw new RuntimeException("Payment failed!");
        }
    }
}
