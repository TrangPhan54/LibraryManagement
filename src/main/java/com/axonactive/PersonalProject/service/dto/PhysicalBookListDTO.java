package com.axonactive.PersonalProject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhysicalBookListDTO {
    private List<Long> physicalBookIdList;
}
