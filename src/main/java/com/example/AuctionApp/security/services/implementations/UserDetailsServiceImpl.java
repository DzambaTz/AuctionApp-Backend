/**
 * UserDetailsServiceImpl is a class that implements the loadUserByUsername method,
 * which tries to construct a User object from the passed username (email in our case)
 * that is later used during the request and JWT filtering process.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.security.services.implementations;

import com.example.AuctionApp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.example.AuctionApp.repository.UserRepository;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws BadCredentialsException {
        final User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("User Not Found with email: " + email));

        return UserDetailsImpl.build(user);
    }

}