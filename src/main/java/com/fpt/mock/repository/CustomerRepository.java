package com.fpt.mock.repository;

import com.fpt.mock.entity.Customer;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    @Query("""
        select c.password from Customer c where lower(c.email) = lower(:email)
    """)
    String findPasswordByEmail(String email);

}
