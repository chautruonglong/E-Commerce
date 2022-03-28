package com.fpt.mock.api;

import com.fpt.mock.dto.CustomerDto;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/api/v1/customers/login")
    public ResponseEntity<String> login(HttpSession session, @Valid @RequestBody LoginDto loginDto) {
        try {
            customerService.login(loginDto);
            session.setAttribute("email", "demo");

            return ResponseEntity.ok("ok");
        }
        catch(Exception exception) {
            throw new GlobalRequestException(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/api/v1/customers/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        try {
            session.removeAttribute("email");

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

}
