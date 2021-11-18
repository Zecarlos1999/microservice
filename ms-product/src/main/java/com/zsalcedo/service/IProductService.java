package com.zsalcedo.service;

import java.util.List;

import com.zsalcedo.model.Product;

public interface IProductService {

	public List<Product> findAll();

	public Product findById(String id);

	public Product save(Product product);

	public void delete(String id);
	
	
//-----------Codigo WebFlux---------------------------------
	
	
//	public Flux<Product> findAll();
//	public Mono<Product> findById(String id);
//	public Mono<Product> save(Product product);
//	public Mono<Void> delete(String id);

}
