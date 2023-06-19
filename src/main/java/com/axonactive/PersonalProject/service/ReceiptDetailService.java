package com.axonactive.PersonalProject.service;

import com.axonactive.PersonalProject.service.dto.*;

import java.util.List;

public interface ReceiptDetailService {
    List<ReceiptDetailDTO> getAllReceiptDetail ();
    CreateReceiptDTO createReceiptDetail (CreateReceiptDetailDTO createReceiptDetailDTO);

}
