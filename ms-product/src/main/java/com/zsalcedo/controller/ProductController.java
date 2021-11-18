package com.zsalcedo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zsalcedo.model.Product;
import com.zsalcedo.service.impl.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductServiceImpl productServiceImpl;

	@GetMapping("/getProducts")
	public ResponseEntity<List<Product>> findAll() {
		log.info("Get list of Products");
		List<Product> products = productServiceImpl.findAll();
		if (products.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(products);
		}

	}

	@GetMapping("/getProduct/{id}")
	public ResponseEntity<Product> findById(@PathVariable("id") String id) {
		Product product = productServiceImpl.findById(id);
		if (product == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(product);
		}
	}

	@PostMapping("/addProduct")
	public ResponseEntity<Product> save(@RequestBody Product product) {
		Product productNew = productServiceImpl.save(product);
		return ResponseEntity.ok(productNew);
	}

	@PutMapping("/updateProduct/{id}")
	public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable("id") String id) {
		Product productNew = productServiceImpl.save(product);
		return ResponseEntity.ok(productNew);
	}

	@DeleteMapping("/deleteProduct/{id}")
	public void delete(@PathVariable String id) {
		productServiceImpl.delete(id);
	}

}

//-----------------------------Codigo WebFlux------------------------

//	@GetMapping("/listProducts")
//	public Flux<Product> findAll() {
//		log.info("Get list of products");
//		return productServiceImpl.findAll();
//	}
//
//	@GetMapping("/getProduct/{id}")
//	public Mono<Product> findById(@PathVariable("id") String id) {
//		log.info("Get a specific product");
//		return productServiceImpl.findById(id);
//	}
//
//	@PostMapping("/addProduct")
//	public Mono<Product> save(@RequestBody Product product) {
//		log.info("Create product");
//		return productServiceImpl.save(product); // Ojo con el metodo suscribe
//	}
//
//	@PutMapping("/updateProduct/{id}")
//	public Mono<Product> update(@RequestBody Product product) {
//		log.info("Update product");
//		return productServiceImpl.save(product);
//	}
//
//	@DeleteMapping("/deleteProduct/{id}")
//	public Mono<Void> delete(@PathVariable String id) {
//		log.info("Delete a specific product");
//		return productServiceImpl.delete(id);
//	}
