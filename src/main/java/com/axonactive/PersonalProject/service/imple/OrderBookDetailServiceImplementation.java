package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.Book;
import com.axonactive.PersonalProject.entity.OrderBook;
import com.axonactive.PersonalProject.entity.OrderDetail;
import com.axonactive.PersonalProject.exception.BookStoreException;
import com.axonactive.PersonalProject.repository.BookRepository;
import com.axonactive.PersonalProject.repository.OrderBookRepository;
import com.axonactive.PersonalProject.repository.OrderDetailRepository;
import com.axonactive.PersonalProject.service.OrderBookDetailService;
import com.axonactive.PersonalProject.service.dto.OrderBookDTO;
import com.axonactive.PersonalProject.service.dto.OrderDetailDTO;
import com.axonactive.PersonalProject.service.mapper.OrderBookDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Order;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderBookDetailServiceImplementation implements OrderBookDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final BookRepository bookRepository;
    private final OrderBookRepository orderBookRepository;

    private final OrderBookDetailMapper orderBookDetailMapper;
    @Override
    public List<OrderDetailDTO> getAllOrderDetail() {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        return orderBookDetailMapper.toDtos(orderDetails);

    }

    @Override
    public OrderDetailDTO createOrderDetail(OrderDetailDTO orderDetailDTO, Long bookID, Long orderID) {
        OrderDetail orderDetail = new OrderDetail();
        Book book = bookRepository.findById(bookID).orElseThrow(BookStoreException::BookNotFound);
        OrderBook order = orderBookRepository.findById(orderID).orElseThrow(BookStoreException::OrderNotFound);
        orderDetail.setQuantityOfBooks(orderDetail.getQuantityOfBooks());
        orderDetail.setBook(book);
        orderDetail.setOrderBook(order);
        orderDetail = orderDetailRepository.save(orderDetail);
        return orderBookDetailMapper.toDto(orderDetail);
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

    }
}
