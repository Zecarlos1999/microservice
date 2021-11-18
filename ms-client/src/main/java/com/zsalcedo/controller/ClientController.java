package com.zsalcedo.controller;

import java.util.List;
import java.util.Map;

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

import com.zsalcedo.model.Client;
import com.zsalcedo.service.Impl.ClientServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientServiceImpl clientServiceImpl;

	@GetMapping("/getClients")
	public ResponseEntity<List<Client>> listClients() {
		log.info("Get list of clients");
		List<Client> clients = clientServiceImpl.findAll();
		if (clients.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(clients);
		}
	}

	@GetMapping("/getClient/{id}")
	public ResponseEntity<Client> findById(@PathVariable("id") String id) {
		Client client = clientServiceImpl.findById(id);
		if (client == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(client);
		}
	}

	@GetMapping("/getClientByIdentityNumber/{identityNumber}")
	public ResponseEntity<Client> getClientByIdentityNumber(@PathVariable("identityNumber") String identityNumber) {
		Client client = clientServiceImpl.findByIdentityNumber(identityNumber);
		if (client == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(client);
		}
	}

	@PostMapping("/addClient")
	public ResponseEntity<Client> createClient(@RequestBody Client client) {
		log.info("Create client");
		Client clientNew = (Client) clientServiceImpl.save(client);
//		Client clientNew = (Client) clientServiceImpl.save1(client);
		return ResponseEntity.ok(clientNew);
	}

	@PutMapping("/updateClient/{id}")
	public ResponseEntity<Client> updateClient(@RequestBody Client client, @PathVariable("id") String id) {
		log.info("Update client");
		Client clientNew = (Client) clientServiceImpl.save(client);
		return ResponseEntity.ok(clientNew);
	}

	@DeleteMapping("/deleteClient/{id}")
	public void deleteClient(@PathVariable("id") String id) {
		log.info("Delete a specific client");
		clientServiceImpl.deleteById(id);
	}

	@GetMapping("getAll/{IdentityNumber}")
	public ResponseEntity<Map<String, Object>> getProductAndClient(
			@PathVariable("IdentityNumber") String IdentityNumber) {
//		Map<String, Object> result = clientServiceImpl.getClientAndProduct(IdentityNumber);
		Map<String, Object> result = clientServiceImpl.getProductAndClient(IdentityNumber);
		System.out.println("Resultado final -----------------> " + result);
		return ResponseEntity.ok(result);
	}

//-----------------Codigo WebFlux-------------------------

//	@GetMapping("/getClients")
//	public Flux<Client> listClients() {
//		log.info("Get list of clients");
//		return clientService.findAll();
//	}
//
//	@GetMapping("/getClient/{id}")
//	public Mono<Client> getClient(@PathVariable("id") String id) {
//		log.info("Get a specific client");
//		return clientService.findById(id);
//	}
//	
//	@PostMapping("/addClient")
//	public void createClient(@RequestBody Client client) {
//		log.info("Create client");
//		clientService.save(client).subscribe();
//	}
//
//	@PutMapping("/updateClient/{id}")
//	public Mono<Client> updateClient(@RequestBody Client client, @PathVariable("id") String id) {
//		log.info("Update client");
//		return clientService.update(client);
//	}
//
//	@DeleteMapping("/deleteClient/{id}")
//	public Mono<Void> deleteClient(@PathVariable("id") String id) {
//		log.info("Delete a specific client");
//		return clientService.deleteById(id);
//	}

}
