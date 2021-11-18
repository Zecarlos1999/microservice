package com.zsalcedo.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "client")
public class Client implements Serializable {

	@Id
	private String id;

//	@Size(max = 200)
	@Field(name = "name")
	private String name;

	@Field(name = "lastname")
	private String lastname;
	
	@Field(name = "identityType")
	private String identityType;

	@Field(name = "identityNumber")
	private String identityNumber;

	@Field(name = "address")
	private String address;

	@Field(name = "phoneNumber")
	private String phoneNumber;

	@Field(name = "email")
	private String email;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime registerDate;
	
	@Field(name = "clientType")
	private String clientType;
	
//	@Field(name = "nameProduct")
//	private String nameProduct;
	
//	@Field(name = "product")
//	private Producto product;

	private static final long serialVersionUID = -3550940932275173856L;

}
