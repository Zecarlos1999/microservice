package com.zsalcedo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.zsalcedo.model.Client;

@Repository
public interface IClientRepository extends MongoRepository<Client, String> {

	Client findByIdentityNumber(String identityNumber);

//	Map<String, Object> getClientAndProduct(String identityNumber);
}
