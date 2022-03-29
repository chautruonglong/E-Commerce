package com.fpt.mock.service.internal;

import com.fpt.mock.dto.OrderProductDto;
import com.fpt.mock.entity.Order;
import com.fpt.mock.repository.OrderRepository;
import com.fpt.mock.service.OrderService;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public List<OrderProductDto> getOrderProductByCustomerId(UUID customerId, int limit, int page) {
        Sort sort = Sort.by(Sort.Direction.ASC, "createdAt");
        Pageable pageable = PageRequest.of(page, limit, sort);

        return orderRepository.findOrderByCustomerId(customerId, pageable);
    }

    @Override
    public void removeOrder(String id) {
        orderRepository.deleteById(UUID.fromString(id));
    }

}
