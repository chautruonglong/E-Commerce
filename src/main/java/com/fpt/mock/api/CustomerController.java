package com.fpt.mock.api;

import com.fpt.mock.dto.CustomerDto;
import com.fpt.mock.dto.CustomerEditDto;
import com.fpt.mock.dto.LoginDto;
import com.fpt.mock.entity.Customer;
import com.fpt.mock.exception.GlobalRequestException;
import com.fpt.mock.service.CustomerService;
import java.net.URI;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/api/v1/customers/login")
    public ResponseEntity<Customer> login(HttpSession session, @Valid @RequestBody LoginDto loginDto) {
        try {
            Customer customer = customerService.login(loginDto);
            session.setAttribute("customer", customer);

            return ResponseEntity.ok(customer);
        }
        catch(Exception exception) {
            throw new GlobalRequestException(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/api/v1/customers/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        try {
            session.removeAttribute("customer");

            return ResponseEntity.ok("ok");
        }
        catch(Exception exception) {
            throw new GlobalRequestException(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/api/v1/customers")
    public ResponseEntity<Customer> postCustomer(@Valid @RequestBody CustomerDto customerDto) {
        try {
            Customer customer = customerService.createCustomer(customerDto);

            return ResponseEntity.created(URI.create("/api/v1/customers/" + customer.getId()))
                .body(customer);
        }
        catch(Exception exception) {
            throw new GlobalRequestException(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/api/v1/customers/{id}")
    public ResponseEntity<Customer> putCustomer(@Valid @RequestBody CustomerEditDto customerEditDto,
                                                @PathVariable String id,
                                                HttpSession session) {
        try {
            Customer customer = customerService.updateCustomer(customerEditDto, id);
            session.setAttribute("customer", customer);

            return ResponseEntity.ok(customer);
        }
        catch(Exception exception) {
            throw new GlobalRequestException(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

}
