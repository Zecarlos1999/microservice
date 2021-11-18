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

import com.zsalcedo.model.DetCliPro;
import com.zsalcedo.service.Impl.DetCliProServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/detCliPro")
public class DetCliProController {

	@Autowired
	private DetCliProServiceImpl detCliProServiceImpl;

	@GetMapping("/getDetCliProducts")
	public ResponseEntity<List<DetCliPro>> listDetCliProducts() {
		log.info("Get list of clients and products");
		List<DetCliPro> detCliProducts = detCliProServiceImpl.findAll();
		if (detCliProducts.isEmpty()) {
			log.info("no hay registro");
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(detCliProducts);
		}
	}

	@GetMapping("/getDetCliPro/{id}")
	public ResponseEntity<DetCliPro> findById(@PathVariable("id") String id) {
		DetCliPro detCliPro = detCliProServiceImpl.findById(id);
		if (detCliPro == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(detCliPro);
		}
	}

	@PostMapping("/addDetCliPro")
	public ResponseEntity<DetCliPro> createDetCliPro(@RequestBody DetCliPro detCliPro) {
		log.info("Create client");
		DetCliPro detCliProNew = detCliProServiceImpl.save(detCliPro);
		return ResponseEntity.ok(detCliProNew);
	}

	@PutMapping("/updateDetCliPro/{id}")
	public ResponseEntity<DetCliPro> updateDetCliPro(@RequestBody DetCliPro detCliPro, @PathVariable("id") String id) {
		log.info("Update client");
		DetCliPro detCliProNew = detCliProServiceImpl.save(detCliPro);
		return ResponseEntity.ok(detCliProNew);
	}

	@DeleteMapping("/deleteDetCliPro/{id}")
	public void deleteDetCliPro(@PathVariable("id") String id) {
		log.info("Delete detCliPro");
		detCliProServiceImpl.deleteById(id);
	}

}
