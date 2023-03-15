package es.dreyesr.orderservice.controller;

import es.dreyesr.orderservice.client.StockClient;
import es.dreyesr.orderservice.dto.OrderDTO;
import es.dreyesr.orderservice.entity.Order;
import es.dreyesr.orderservice.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockClient stockClient;

    @PostMapping
    @CircuitBreaker(name = "orderCB", fallbackMethod = "fallBackSaveOrder")
    public String saveOrder(@RequestBody OrderDTO orderDTO) {

        log.info("In: {}", orderDTO);

        boolean inStock = orderDTO.getOrderItems().stream()
                .allMatch(orderItem -> stockClient.stockAvailable(orderItem.getCode()));

        if (inStock) {
            Order order = new Order();
            order.setOrderNo(UUID.randomUUID().toString());
            order.setOrderItems(orderDTO.getOrderItems());

            orderRepository.save(order);

            return "Order Saved";
        }

        return "Order Cannot be Saved";
    }

    private String fallBackSaveOrder(@RequestBody OrderDTO orderDTO, RuntimeException e) {
        return "Something went wrong, please try after some time.";
    }
}
