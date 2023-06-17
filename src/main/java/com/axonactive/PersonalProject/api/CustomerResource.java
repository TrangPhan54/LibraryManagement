package com.axonactive.PersonalProject.api;

import com.axonactive.PersonalProject.service.CustomerService;
import com.axonactive.PersonalProject.service.dto.CustomerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/auth/customers")
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
        return ResponseEntity.created(URI.create("/api/customers/" + customer.getId())).body(customer);
    }
    @PutMapping(value = "/{customerID}")
    public ResponseEntity<CustomerDTO> updateCustomer (@PathVariable("customerID") Long customerID,
                                                @RequestBody CustomerDTO customerDTO){
        CustomerDTO cus = customerService.updateCustomer(customerID,customerDTO);
        return ResponseEntity.created(URI.create("/api/customers"+cus.getId())).body(cus);
    }
    @DeleteMapping
    public ResponseEntity<CustomerDTO> deleteCustomerById (@PathVariable("customerID") Long customerID){
        customerService.deleteCustomerByID(customerID);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/customer_id")
    public ResponseEntity<CustomerDTO> getCustomerById (@RequestParam ("customerID") Long customerID){
         return ResponseEntity.ok(customerService.getCustomerByID(customerID));
    }
    @GetMapping("/customer_first_name")
    public ResponseEntity <List<CustomerDTO>> getCustomerByCustomerFirstName (@RequestParam ("customerFirstName") String customerFirstName){
         return ResponseEntity.ok(customerService.getCustomerByFirstName(customerFirstName));
    }

    @GetMapping("/customer_last_name")
    public ResponseEntity <List<CustomerDTO>> getCustomerByCustomerLastName (@RequestParam ("customerLastName") String customerLastName){
        return ResponseEntity.ok(customerService.getCustomerByLastName(customerLastName));
    }
    @GetMapping("/customer_email")
    public ResponseEntity<List<CustomerDTO>> getCustomerByCustomerEmail (@RequestParam ("customerEmail") String customerEmail){
         return ResponseEntity.ok(customerService.getCustomerByEmail(customerEmail));
    }
    @GetMapping("/first_name_containing")
    public ResponseEntity<List<CustomerDTO>> getCustomerByFirstNameContaining (@RequestParam ("partOfName") String partOfName){
         return ResponseEntity.ok(customerService.getCustomerByFirstNameContaining(partOfName));
    }
    @GetMapping("/last_name_containing")
    public ResponseEntity<List<CustomerDTO>> getCustomerByLastNameContaining (@RequestParam ("partOfName") String partOfName){
        return ResponseEntity.ok(customerService.getCustomerByLastNameContaining(partOfName));
    }


}
