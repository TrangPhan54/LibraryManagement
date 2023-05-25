package com.axonactive.PersonalProject.security.jwt;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class JwtResponse implements Serializable {
    private String token;
    private String type = "Bearer";
    private String customerEmail;
    private List<String> roles;

    public JwtResponse(String token, String customerEmail, List<String> roles) {
        this.token = token;
        this.customerEmail = customerEmail;
        this.roles = roles;
    }
}
