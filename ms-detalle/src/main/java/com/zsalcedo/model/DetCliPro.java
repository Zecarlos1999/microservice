package com.zsalcedo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "detalleClienteProducto")
public class DetCliPro {//DetalleClienteProducto

	@Id
	private String id;

	@Field(name = "idCliente")
	private String idCliente;

	@Field(name = "idProducto")
	private String idProducto;

	@Field(name = "estate")
	private String estate;

}
