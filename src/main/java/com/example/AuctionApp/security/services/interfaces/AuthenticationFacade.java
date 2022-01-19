package com.example.AuctionApp.security.services.interfaces;

import com.example.AuctionApp.security.services.implementations.UserDetailsImpl;

public interface AuthenticationFacade {
    UserDetailsImpl getUserPrincipal();
}
