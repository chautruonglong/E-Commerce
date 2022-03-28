package com.fpt.mock.service.internal;

import com.fpt.mock.dto.CustomerDto;
import com.fpt.mock.dto.LoginDto;
import com.fpt.mock.entity.Customer;
import com.fpt.mock.repository.CustomerRepository;
import com.fpt.mock.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Override
    public void login(LoginDto loginDto) {
        String password = customerRepository.findPasswordByEmail(loginDto.getEmail());

        if(password == null) {
            throw new RuntimeException("Email not in database");
        }

        if(!password.equals(loginDto.getPassword())) {
            throw new RuntimeException("Password do not match");

        }
    }

    @Override
    public Customer createCustomer(CustomerDto customerDto) {
        Customer customer = Customer.builder()
            .email(customerDto.getEmail())
            .password(customerDto.getPassword())
            .name(customerDto.getName())
            .phone(customerDto.getPhone())
            .address(customerDto.getAddress())
            .build();

        return customerRepository.save(customer);
    }

}
