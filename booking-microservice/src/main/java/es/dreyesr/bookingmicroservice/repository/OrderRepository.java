package es.dreyesr.bookingmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.dreyesr.bookingmicroservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
