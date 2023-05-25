package com.axonactive.PersonalProject.api;

import com.axonactive.PersonalProject.service.OrderBookDetailService;
import com.axonactive.PersonalProject.service.OrderBookService;
import com.axonactive.PersonalProject.service.dto.GenreBookDTO;
import com.axonactive.PersonalProject.service.dto.OrderBookDTO;
import com.axonactive.PersonalProject.service.dto.OrderDetailDTO;
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
@RequestMapping(value = "/api/orderDetails")
public class OrderDetailResource {
    @Autowired
    private final OrderBookDetailService orderBookDetailService;

    @GetMapping
    public ResponseEntity<List<OrderDetailDTO>> getAllOrderBookdetail() {
        return ResponseEntity.ok(orderBookDetailService.getAllOrderDetail());
    }


    @PostMapping(value = "/{orderId}/{bookId}")
    public ResponseEntity<OrderDetailDTO> createOrderBookDetail(@PathVariable("orderId") Long orderID,
                                                        @PathVariable ("bookId") Long bookID,
                                                        @RequestBody OrderDetailDTO orderDetailDTO) {
        OrderDetailDTO book = orderBookDetailService.createOrderDetail(orderDetailDTO,bookID,orderID);
        return ResponseEntity.created(URI.create("/api/orderDetails" + book.getOrderDetailID())).body(book);
    }


    @DeleteMapping(value = "/{orderDetailId}")

    public ResponseEntity<OrderDetailDTO> deleteOrderBookDetail(@PathVariable("orderDetailId") Long orderDetailID) {
        orderBookDetailService.deleteOrderDetailByID(orderDetailID);
        return ResponseEntity.noContent().build();
    }
}
