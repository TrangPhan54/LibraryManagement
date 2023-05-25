package com.axonactive.PersonalProject.security.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerSecurityDTO {
    private String principalName;
    private String customerEmail;

}
