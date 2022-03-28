package com.fpt.mock.service;

import com.fpt.mock.dto.OrderDto;
import com.fpt.mock.entity.Order;

public interface OrderService {

    Order createOrder(OrderDto orderDto);

}
