package es.dreyesr.productservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(value = "product")
public class ProductEntity {

	@Id
	private String id;
	private String productName;
	private String productDescription;
	private Double unitPrice;
}
