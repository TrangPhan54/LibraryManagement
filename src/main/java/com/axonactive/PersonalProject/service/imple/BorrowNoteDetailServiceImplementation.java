package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.entity.BorrowNote;
import com.axonactive.PersonalProject.entity.BorrowNoteDetail;
import com.axonactive.PersonalProject.exception.LibraryException;
import com.axonactive.PersonalProject.repository.BookRepository;
import com.axonactive.PersonalProject.repository.BorrowNoteRepository;
import com.axonactive.PersonalProject.repository.BorrowNoteDetailRepository;
import com.axonactive.PersonalProject.service.BorrowNoteDetailService;
import com.axonactive.PersonalProject.service.dto.BorrowNoteDetailDTO;
import com.axonactive.PersonalProject.service.mapper.BorrowNoteDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BorrowNoteDetailServiceImplementation implements BorrowNoteDetailService {
    private final BorrowNoteDetailRepository borrowNoteDetailRepository;
    private final BookRepository bookRepository;
    private final BorrowNoteRepository borrowNoteRepository;

    private final BorrowNoteDetailMapper borrowNoteDetailMapper;
    @Override
    public List<BorrowNoteDetailDTO> getAllOrderDetail() {
        List<BorrowNoteDetail> borrowNoteDetails = borrowNoteDetailRepository.findAll();
        return borrowNoteDetailMapper.toDtos(borrowNoteDetails);

    }

    @Override
    public BorrowNoteDetailDTO createOrderDetail(BorrowNoteDetailDTO borrowNoteDetailDTO, Long bookID, Long orderID) {
        BorrowNoteDetail borrowNoteDetail = new BorrowNoteDetail();
        Book book = bookRepository.findById(bookID).orElseThrow(LibraryException::BookNotFound);
        BorrowNote order = borrowNoteRepository.findById(orderID).orElseThrow(LibraryException::OrderNotFound);
        Long n= borrowNoteDetailDTO.getQuantityOfBooks();
        borrowNoteDetail.setQuantityOfBooks(n);
        borrowNoteDetail.setBook(book);
        borrowNoteDetail.setBorrowNote(order);
        borrowNoteDetail = borrowNoteDetailRepository.save(borrowNoteDetail);


        return borrowNoteDetailMapper.toDto(borrowNoteDetail);
    }

//    @Override
//    public OrderDetailDTO updateOrderDetail(Long orderDetailID, OrderDetailDTO orderDetailDTO) {
//        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailID).orElseThrow();
//        Book book = bookRepository.findById(orderDetailDTO.getBookID()).orElseThrow();
//        OrderBook order = orderBookRepository.findById(orderDetailDTO.getOrderBookID()).orElseThrow();
//        orderDetail.setQuantityOfBooks(orderDetail.getQuantityOfBooks());
//        orderDetail.setBook(book);
//        orderDetail.setOrderBook(order);
//        orderDetail = orderDetailRepository.save(orderDetail);
//        return orderBookDetailMapper.toDto(orderDetail);
//
//    }

    @Override
    public void deleteOrderDetailByID(Long orderDetailID) {
        BorrowNoteDetail borrowNoteDetail = borrowNoteDetailRepository.findById(orderDetailID).orElseThrow(LibraryException::OrderDetailNotFound);

        borrowNoteDetailRepository.delete(borrowNoteDetail);

    }
}
