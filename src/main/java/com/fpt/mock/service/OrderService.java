package com.fpt.mock.service;

import com.fpt.mock.entity.Order;
import java.util.UUID;

public interface OrderService {

    Order createOrder(UUID productId, UUID customerId);

}
