package es.dreyesr.productmicroservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import es.dreyesr.productmicroservice.entity.ProductEntity;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {

}
