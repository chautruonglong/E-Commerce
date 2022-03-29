package com.fpt.mock.api;

import com.fpt.mock.dto.OrderCreationDto;
import com.fpt.mock.dto.OrderProductDto;
import com.fpt.mock.entity.Customer;
import com.fpt.mock.entity.Order;
import com.fpt.mock.exception.GlobalRequestException;
import com.fpt.mock.service.OrderService;
import java.net.URI;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/v1/orders")
    public ResponseEntity<Order> postOrder(@Valid @RequestBody OrderCreationDto orderCreationDto, HttpSession session) {
        try {
            Customer customer = (Customer) session.getAttribute("customer");
            Order order = orderService.createOrder(orderCreationDto.getProductId(), customer.getId());

            return ResponseEntity.created(URI.create("/api/v1/orders/" + order.getId()))
                .body(order);
        }
        catch(Exception exception) {
            throw new GlobalRequestException(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/v1/orders")
    public ResponseEntity<List<OrderProductDto>> getOrders(@RequestParam(defaultValue = "20") int limit,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           HttpSession session) {
        try {
            Customer customer = (Customer) session.getAttribute("customer");
            List<OrderProductDto> products = orderService.getOrderProductByCustomerId(customer.getId(), limit, page);

            return ResponseEntity.ok(products);
        }
        catch(Exception exception) {
            throw new GlobalRequestException(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/v1/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable String id) {
        try {
            orderService.removeOrder(id);
            return ResponseEntity.noContent().build();
        }
        catch(Exception exception) {
            throw new GlobalRequestException(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
