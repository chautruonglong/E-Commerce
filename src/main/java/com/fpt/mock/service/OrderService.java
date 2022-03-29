package com.fpt.mock.service;

import com.fpt.mock.dto.OrderProductDto;
import com.fpt.mock.entity.Order;
import java.util.List;
import java.util.UUID;

public interface OrderService {

    Order createOrder(UUID productId, UUID customerId);
    List<OrderProductDto> getOrderProductByCustomerId(UUID customerId, int limit, int page);
    void removeOrder(String id);

}
