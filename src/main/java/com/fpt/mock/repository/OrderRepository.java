package com.fpt.mock.repository;

import com.fpt.mock.dto.OrderProductDto;
import com.fpt.mock.entity.Order;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Query("""
        select new com.fpt.mock.dto.OrderProductDto(p, o)
        from Product p
        inner join Order o on p.id = o.productId and o.customerId = :customerId
    """)
    List<OrderProductDto> findOrderByCustomerId(UUID customerId, Pageable pageable);

}
