package es.dreyesr.orderservice.entity;

import java.util.List;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String orderNo;
	@OneToMany(cascade = CascadeType.ALL)
	private List<OrderItem> orderItems;
}
