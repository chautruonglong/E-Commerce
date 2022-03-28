package com.fpt.mock.api;

import com.fpt.mock.dto.OrderDto;
import com.fpt.mock.entity.Order;
import com.fpt.mock.exception.GlobalRequestException;
import com.fpt.mock.service.OrderService;
import java.net.URI;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/v1/orders")
    public ResponseEntity<Order> postOrder(@Valid @RequestBody OrderDto orderDto) {
        try {
            Order order = orderService.createOrder(orderDto);

            return ResponseEntity.created(URI.create("/api/v1/orders/" + order.getId()))
                .body(order);
        }
        catch(Exception exception) {
            throw new GlobalRequestException(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

}
