package com.axonactive.PersonalProject.api;


import com.axonactive.PersonalProject.service.OrderBookService;
import com.axonactive.PersonalProject.service.dto.GenreBookDTO;
import com.axonactive.PersonalProject.service.dto.OrderBookDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/orderBooks")
public class OrderBookResource {
    @Autowired
    private final OrderBookService orderBookService;

    @GetMapping
    public ResponseEntity<List<OrderBookDTO>> getAllOrderBook() {
        return ResponseEntity.ok(orderBookService.getAllOrder());
    }


    @PostMapping(value = "/{customerId}")
    public ResponseEntity<OrderBookDTO> createOrderBook(@PathVariable("customerId") Long customerID,
                                                        @RequestBody OrderBookDTO orderBookDTO) {
        OrderBookDTO book = orderBookService.createOrder(orderBookDTO,customerID);
        return ResponseEntity.created(URI.create("/api/orderBooks" + book.getOrderBookID())).body(book);
    }

    @PutMapping(value = "/{orderBookId}")
    public ResponseEntity<OrderBookDTO> updateOrderBook(@PathVariable("orderBookId") Long orderBookID,
                                                        @RequestBody OrderBookDTO orderBookDTO) {
        OrderBookDTO book = orderBookService.updateOrder(orderBookID, orderBookDTO);
        return ResponseEntity.created(URI.create("/api/orderBooks" + book.getOrderBookID())).body(book);
    }

    @DeleteMapping(value = "/{orderAndBookId}")

    public ResponseEntity<GenreBookDTO> deleteGenreBook(@PathVariable("orderAndBookId") Long orderAndBookID) {
        orderBookService.deleteOrderByID(orderAndBookID);
        return ResponseEntity.noContent().build();
    }
}