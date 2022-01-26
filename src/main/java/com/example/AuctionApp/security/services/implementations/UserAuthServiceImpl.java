/**
 * Default implementation for {@link UserAuthService}
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.security.services.implementations;

import com.example.AuctionApp.exception.RefreshTokenException;
import com.example.AuctionApp.exception.RoleNotFoundException;
import com.example.AuctionApp.exception.UserAuthExceptions.EmailAlreadyInUseException;
import com.example.AuctionApp.exception.UserAuthExceptions.UserDeactivatedException;
import com.example.AuctionApp.models.RefreshToken;
import com.example.AuctionApp.models.Role;
import com.example.AuctionApp.models.RolesEnum;
import com.example.AuctionApp.models.User;
import com.example.AuctionApp.payload.request.LogOutRequest;
import com.example.AuctionApp.payload.request.LoginRequest;
import com.example.AuctionApp.payload.request.RefreshTokenRequest;
import com.example.AuctionApp.payload.request.SignupRequest;
import com.example.AuctionApp.payload.response.JwtResponse;
import com.example.AuctionApp.payload.response.MessageResponse;
import com.example.AuctionApp.payload.response.RefreshTokenResponse;
import com.example.AuctionApp.repository.RoleRepository;
import com.example.AuctionApp.repository.UserRepository;
import com.example.AuctionApp.security.jwt.JwtUtils;
import com.example.AuctionApp.security.services.interfaces.RefreshTokenService;
import com.example.AuctionApp.security.services.interfaces.UserAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserAuthServiceImpl implements UserAuthService {
    private static final Logger logger = LoggerFactory.getLogger(UserAuthServiceImpl.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RefreshTokenService refreshTokenService;

    public JwtResponse signInUser(LoginRequest loginRequest) throws UserDeactivatedException {
        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        final String jwt = jwtUtils.generateJwtToken(userDetails);

        if (!userRepository.getUserStatus(userDetails.getId())) {
            throw new UserDeactivatedException("User account has been deactivated");
        }

        final List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        final RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        return new JwtResponse(jwt,
                userDetails.getId(),
                refreshToken.getToken(),
                userDetails.getEmail(),
                userDetails.getFirstName(),
                userDetails.getLastName(),
                roles);
    }

    public MessageResponse signUpUser(SignupRequest signupRequest) throws EmailAlreadyInUseException {
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new EmailAlreadyInUseException("Error: Email is already in use!");
        }

        final User user = getUser(signupRequest);

        user.setRoles(getRoles(signupRequest.getRole()));

        userRepository.save(user);

        return new MessageResponse("User successfully registered");
    }

    public RefreshTokenResponse refreshUserToken(RefreshTokenRequest request) {
        final String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    final String token = jwtUtils.generateTokenFromEmail(user.getEmail());
                    logger.error(token);
                    return new RefreshTokenResponse(token, requestRefreshToken);
                })
                .orElseThrow(() -> new RefreshTokenException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    public MessageResponse logoutUser(LogOutRequest logOutRequest) {
        refreshTokenService.deleteByUserId(logOutRequest.getUserId());
        return new MessageResponse("Log out successful!");
    }

    private User getUser(SignupRequest signupRequest) {
        return new User(
                signupRequest.getFirstName(),
                signupRequest.getLastName(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()),
                signupRequest.getPhoneNumber()
        );
    }

    private Set<Role> getRoles(Set<String> strRoles) {
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            final Role userRole = roleRepository.findByName(RolesEnum.ROLE_USER)
                    .orElseThrow(() -> new RoleNotFoundException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if ("seller".equals(role)) {
                    final Role sellerRole = roleRepository.findByName(RolesEnum.ROLE_SELLER)
                            .orElseThrow(() -> new RoleNotFoundException("Error: Role is not found."));
                    roles.add(sellerRole);
                } else {
                    final Role userRole = roleRepository.findByName(RolesEnum.ROLE_USER)
                            .orElseThrow(() -> new RoleNotFoundException("Error: Role is not found."));
                    roles.add(userRole);
                }
            });
        }

        return roles;
    }
}
