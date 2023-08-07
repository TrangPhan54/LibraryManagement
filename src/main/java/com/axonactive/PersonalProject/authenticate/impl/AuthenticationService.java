package com.axonactive.PersonalProject.authenticate.impl;

import com.axonactive.PersonalProject.entity.Role;
import com.axonactive.PersonalProject.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse register(RegisterRequest registerRequest){
        var user = User.builder()
                .username(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .roles(String.valueOf(Role.ROLE_USER))
                .build();
        userRepository.save(user);


    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest){

                .
    }
}
