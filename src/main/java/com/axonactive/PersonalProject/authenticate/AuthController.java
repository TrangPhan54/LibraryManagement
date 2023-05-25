package com.axonactive.PersonalProject.authenticate;

import com.axonactive.PersonalProject.security.jwt.JwtRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public interface AuthController {

    @PostMapping("/signin")
    ResponseEntity<?> authenticateUser(@Valid @RequestBody JwtRequest loginRequest);
}
