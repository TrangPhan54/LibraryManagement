package com.axonactive.PersonalProject.service.imple;

import com.axonactive.PersonalProject.entity.Customer;
import com.axonactive.PersonalProject.entity.OrderBook;
import com.axonactive.PersonalProject.exception.BookStoreException;
import com.axonactive.PersonalProject.repository.CustomerRepository;
import com.axonactive.PersonalProject.repository.OrderBookRepository;
import com.axonactive.PersonalProject.service.OrderBookService;
import com.axonactive.PersonalProject.service.dto.CustomerDTO;
import com.axonactive.PersonalProject.service.dto.OrderBookDTO;
import com.axonactive.PersonalProject.service.mapper.OrderBookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static com.axonactive.PersonalProject.exception.BooleanMethod.isAlpha;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderBookServiceImplementation implements OrderBookService {
    private final OrderBookRepository orderBookRepository;
    private final CustomerRepository customerRepository;
    private final OrderBookMapper orderBookMapper;
    @Override
    public List<OrderBookDTO> getAllOrder() {
        List<OrderBook> orderBooks = orderBookRepository.findAll();
        return orderBookMapper.toDtos(orderBooks);
    }

    @Override
    public OrderBookDTO createOrder(OrderBookDTO orderBookDTO, Long customerID) {
        OrderBook orderBook = new OrderBook();
        Customer customer = customerRepository.findById(customerID).orElseThrow(BookStoreException::CustomerNotFound);
        orderBook.setOrderingDate(orderBookDTO.getOrderingDate());
        orderBook.setAddress(orderBookDTO.getAddress());
        orderBook.setStatusDeliver(orderBookDTO.getStatusDeliver());
        orderBook.setCustomer(customer);
        orderBook = orderBookRepository.save(orderBook);
        return orderBookMapper.toDto(orderBook);


    }

    @Override
    public OrderBookDTO updateOrder(Long orderID, OrderBookDTO orderBookDTO) {
        OrderBook orderBook = orderBookRepository.findById(orderID).orElseThrow(BookStoreException::OrderNotFound);
//        Customer customer = customerRepository.findById(orderBookDTO.getCustomerID()).orElseThrow();
//        orderBook.setOrderingDate(orderBookDTO.getOrderingDate());
//        orderBook.setAddress(orderBookDTO.getAddress());
        orderBook.setStatusDeliver(orderBookDTO.getStatusDeliver());
//        orderBook.setCustomer(customer);
        orderBook = orderBookRepository.save(orderBook);
        return orderBookMapper.toDto(orderBook);
    }

    @Override
    public void deleteOrderByID(Long orderID) {
        OrderBook orderBook = orderBookRepository.findById(orderID).orElseThrow(BookStoreException::OrderNotFound);
        orderBookRepository.delete(orderBook);

    }

    @Override
    public OrderBookDTO getOrderById(Long orderID) {
        return orderBookMapper.toDto(orderBookRepository.findById(orderID).orElseThrow(BookStoreException::OrderNotFound));
    }
    private void orderException (OrderBookDTO orderBookDTO){
        if (orderBookDTO.getOrderingDate().isBefore(LocalDate.now())){
            throw BookStoreException.badRequest("WrongTime","Ordering Date Must Be After Now");
        }
        if (orderBookDTO.getAddress().isBlank()){
            throw BookStoreException.badRequest("WrongAddressFormat","Address Cannot Be Empty");
        }
        if (orderBookDTO.getStatusDeliver().equals("")){
            throw BookStoreException.badRequest("WrongStatusFormat","Status Cannot Be Empty");
        }

    }
    private void customerException (CustomerDTO customerDTO){
        if (customerDTO.getCustomerLastName().isBlank() || customerDTO.getCustomerFirstName().isBlank()
        || !isAlpha(customerDTO.getCustomerFirstName()) || !isAlpha(customerDTO.getCustomerLastName())){
            throw BookStoreException.badRequest("WrongNameFormat","Name Cannot Be Empty And Should Contains Only Letters");
        }
    }
}
