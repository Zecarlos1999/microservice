package com.zsalcedo.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product implements Serializable {

	@Id
	private String id;
	@Field(name = "typeProduct")
	private String typeProduct;
	@Field(name = "nameProduct")
	private String nameProduct;

	private static final long serialVersionUID = 1L;
}
