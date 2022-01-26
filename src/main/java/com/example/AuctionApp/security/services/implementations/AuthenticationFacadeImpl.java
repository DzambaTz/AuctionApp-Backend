package com.example.AuctionApp.security.services.implementations;

import com.example.AuctionApp.security.services.interfaces.AuthenticationFacade;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {
    @Override
    public UserDetailsImpl getUserPrincipal() {
        return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
