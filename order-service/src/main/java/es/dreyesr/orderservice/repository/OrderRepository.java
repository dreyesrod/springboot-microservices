package es.dreyesr.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.dreyesr.orderservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
