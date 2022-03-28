package com.fpt.mock.repository;

import com.fpt.mock.entity.Customer;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Customer findByEmail(String email);

}
