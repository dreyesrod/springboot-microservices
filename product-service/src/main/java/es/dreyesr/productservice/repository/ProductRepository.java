package es.dreyesr.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import es.dreyesr.productservice.entity.ProductEntity;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {

}
