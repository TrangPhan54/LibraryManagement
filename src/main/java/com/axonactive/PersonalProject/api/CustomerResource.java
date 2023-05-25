package com.axonactive.PersonalProject.api;

import com.axonactive.PersonalProject.entity.Customer;
import com.axonactive.PersonalProject.service.CustomerService;
import com.axonactive.PersonalProject.service.dto.CustomerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/customers")
@RequiredArgsConstructor
public class CustomerResource {
    @Autowired
    private final CustomerService customerService;

     @GetMapping
     public ResponseEntity<List<CustomerDTO>> getAllCustomer() {
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO customer = customerService.createCustomer(customerDTO);
        return ResponseEntity.created(URI.create("/api/customers/" + customer.getCustomerID())).body(customer);
    }
    @PutMapping(value = "/{customerID}")
    public ResponseEntity<CustomerDTO> updateCustomer (@PathVariable("customerID") Long customerID,
                                                @RequestBody CustomerDTO customerDTO){
        CustomerDTO cus = customerService.updateCustomer(customerID,customerDTO);
        return ResponseEntity.created(URI.create("/api/customers"+cus.getCustomerID())).body(cus);
    }
    @DeleteMapping
    public ResponseEntity<CustomerDTO> deleteCustomerById (@PathVariable("customerID") Long customerID){
        customerService.deleteCustomerByID(customerID);
        return ResponseEntity.noContent().build();
    }


}
