package com.zsalcedo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.zsalcedo.model.DetCliPro;

@Repository
public interface IDetCliProRepository extends MongoRepository<DetCliPro, String> {

}
