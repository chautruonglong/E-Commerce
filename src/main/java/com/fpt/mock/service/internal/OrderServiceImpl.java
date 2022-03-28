package com.fpt.mock.service.internal;

import com.fpt.mock.dto.OrderDto;
import com.fpt.mock.entity.Order;
import com.fpt.mock.repository.OrderRepository;
import com.fpt.mock.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order createOrder(OrderDto orderDto) {
        Order order = Order.builder()
            .customerId(orderDto.getCustomerId())
            .productId(orderDto.getProductId())
            .build();

        return orderRepository.save(order);
    }

}
