package com.zsalcedo.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zsalcedo.dto.Product;

@FeignClient(name = "service-product", url = "http://localhost:8004")
@RequestMapping("/product")
public interface ProductFeignCliet {

	@GetMapping("/byIdentityNumber/{IdentityNumber}")
	List<Product> getProducts(@PathVariable("IdentityNumber") String IdentityNumber);
	
	@GetMapping("/getProducts")
	List<Product> getProducts();
	
	
	
}
