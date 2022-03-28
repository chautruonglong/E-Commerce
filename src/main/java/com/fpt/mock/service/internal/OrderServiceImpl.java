package com.fpt.mock.service.internal;

import com.fpt.mock.entity.Order;
import com.fpt.mock.repository.OrderRepository;
import com.fpt.mock.service.OrderService;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    @Override
    public Order createOrder(UUID productId, UUID customerId) {
        Order order = Order.builder()
            .customerId(customerId)
            .productId(productId)
            .build();

        return orderRepository.save(order);
    }
}
