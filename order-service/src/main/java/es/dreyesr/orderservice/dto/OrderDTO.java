package es.dreyesr.orderservice.dto;

import java.util.List;

import es.dreyesr.orderservice.entity.OrderItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {

	private List<OrderItem> orderItems;
}
