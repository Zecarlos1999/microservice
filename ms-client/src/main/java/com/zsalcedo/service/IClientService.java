package com.zsalcedo.service;

import java.util.List;
import java.util.Map;

import com.zsalcedo.model.Client;

public interface IClientService {

	public List<Client> findAll();

	public Client findById(String id);

	public Client findByIdentityNumber(String identityNumber);

	public Object save(Client client);

	public Client update(Client client);

	public void deleteById(String id);

	public Map<String, Object> getClientAndProduct(String identityNumber);
	
	public Map<String, Object> getProductAndClient(String identityNumber);

//	public Flux<Client> findAll();
//
//	public Mono<Client> findById(String id);
//
//	public Mono<Client> save(Client client);
//
//	public Mono<Client> update(Client client);
//
//	public Mono<Void> deleteById(String id);

}
