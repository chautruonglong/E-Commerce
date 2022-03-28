package com.fpt.mock.service;

import com.fpt.mock.dto.CustomerDto;
import com.fpt.mock.dto.LoginDto;
import com.fpt.mock.entity.Customer;

public interface CustomerService {

    void login(LoginDto loginDto);
    Customer createCustomer(CustomerDto customerDto);

}
