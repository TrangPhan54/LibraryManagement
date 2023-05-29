package com.axonactive.PersonalProject.service;


import com.axonactive.PersonalProject.service.dto.ResponseContentDTO;
import com.axonactive.PersonalProject.service.dto.ResponseDTO;

import java.util.List;

public interface ResponseService {
    List<ResponseDTO> getAllResponse ();
    ResponseDTO createResponse (Long customerID, Long bookID, ResponseDTO responseDTO);
    ResponseDTO updateResponse (Long responseID, ResponseDTO responseDTO);
    void deleteResponseByID (Long responseID);
    ResponseDTO getResponseById (Long responseId);
    String getResponseOfABook (Long bookId);

//    ResponseContentDTO findResponseOfABookByBookName (String bookName);

    String findResponseOfABookByBookName (String bookName);


}
