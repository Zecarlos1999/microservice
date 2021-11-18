package com.zsalcedo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.zsalcedo.model.Product;

@Repository
public interface IProductRepository extends MongoRepository<Product, String> {
}
