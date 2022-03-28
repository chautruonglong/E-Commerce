package com.fpt.mock.service.internal;

import com.fpt.mock.dto.CustomerDto;
import com.fpt.mock.dto.CustomerEditDto;
import com.fpt.mock.dto.LoginDto;
import com.fpt.mock.entity.Customer;
import com.fpt.mock.repository.CustomerRepository;
import com.fpt.mock.service.CustomerService;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Override
    public Customer login(LoginDto loginDto) {
        Customer customer = customerRepository.findByEmail(loginDto.getEmail());

        if(customer == null) {
            throw new RuntimeException("Email not in database");
        }

        if(!customer.getPassword().equals(loginDto.getPassword())) {
            throw new RuntimeException("Password do not match");
        }

        return customer;
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

    @Override
    public Customer updateCustomer(CustomerEditDto customerEditDto, String id) {
        Customer customer = customerRepository.findById(UUID.fromString(id))
            .orElseThrow(() -> new RuntimeException("Customer not in database"));

        customer.setPassword(customerEditDto.getPassword());
        customer.setName(customerEditDto.getName());
        customer.setPhone(customerEditDto.getPhone());
        customer.setAddress(customerEditDto.getAddress());

        return customerRepository.save(customer);
    }

}
