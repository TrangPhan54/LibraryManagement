package com.axonactive.PersonalProject.service.dto;

import com.axonactive.PersonalProject.entity.PhysicalBook;
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
