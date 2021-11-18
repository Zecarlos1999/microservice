package com.zsalcedo.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zsalcedo.dto.DetCliPro;

@FeignClient(name = "service-detalle", url = "http://localhost:8005")
@RequestMapping("/detCliPro")
public interface DetCliProFeignClient {

	@GetMapping("/getDetCliProducts")
	List<DetCliPro> getDetCliPro();
	
	@PostMapping("/addDetCliPro")
	public DetCliPro createDetCliPro(@RequestBody DetCliPro detCliPro);

}
