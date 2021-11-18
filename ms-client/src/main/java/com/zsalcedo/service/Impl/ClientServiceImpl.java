package com.zsalcedo.service.Impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.zsalcedo.dto.DetCliPro;
import com.zsalcedo.dto.Product;
import com.zsalcedo.feignclients.DetCliProFeignClient;
import com.zsalcedo.feignclients.ProductFeignCliet;
import com.zsalcedo.model.Client;
import com.zsalcedo.model.Producto;
import com.zsalcedo.repository.IClientRepository;
import com.zsalcedo.service.IClientService;

//@Slf4j
@Service
public class ClientServiceImpl implements IClientService {
	String nameProduct = null;
	@Autowired
	private IClientRepository clientRepository;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ProductFeignCliet productFeignCliet;

	@Autowired
	DetCliProFeignClient detCliProFeignClient;

	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Client findById(String id) {
		return clientRepository.findById(id).orElse(null);
	}

	@Transactional
	public Object save1(Client cliet1) {// metodo de prueba
		return clientRepository.save(cliet1);
	}

	@Override
	public Object save(Client client) {
		Client client2 = null;
		client.setRegisterDate(LocalDateTime.now());
		List<Client> listClients = clientRepository.findAll();
		List<Product> listProducts = productFeignCliet.getProducts();
		List<DetCliPro> listDetCliProducts = detCliProFeignClient.getDetCliPro();
		if (listClients.isEmpty()) {
			client2 = clientRepository.save(client);
			List<Client> listClients1 = clientRepository.findAll();
			if (listClients1.get(0).getClientType().equals("Empresa")) {
				for (int i = 0; i < listProducts.size(); i++) {
					if (listProducts.get(i).getNameProduct().equals("Cuenta Corriente")
							|| listProducts.get(i).getNameProduct().equals("Empresarial")
							|| listProducts.get(i).getNameProduct().equals("Tarjeta Credito")) {
						nameProduct = listProducts.get(i).getNameProduct();
					}
					// Full if
				}
			} else {
				if (listClients1.get(0).getClientType().equals("Persona")) {
					for (int i = 0; i < listProducts.size(); i++) {
						if (listProducts.get(i).getNameProduct().equals("Cuenta Ahorro")
								|| listProducts.get(i).getNameProduct().equals("Cuenta Corriente")
								|| listProducts.get(i).getNameProduct().equals("Cuenta Plazo Fijo")
								|| listProducts.get(i).getNameProduct().equals("Personal")
								|| listProducts.get(i).getNameProduct().equals("Tarjeta Credito")) {
							nameProduct = listProducts.get(i).getNameProduct();
						}
					}
				}
			}
			DetCliPro detCliPro = new DetCliPro();
			detCliPro.setIdCliente(listClients1.get(0).getId());
			detCliPro.setIdProducto(gettingProductId(nameProduct));
			detCliProFeignClient.createDetCliPro(detCliPro);
		}
//		else {
//			//Codigo por Agregar
//			gettingProductName(client);
//
//		}
		return client2;

	}

	public String gettingProductId(String nameProduct) {
		List<Product> listProducts = productFeignCliet.getProducts();
		for (int i = 0; i < listProducts.size(); i++) {
			if (listProducts.get(i).getNameProduct().equals(nameProduct)) {
				return listProducts.get(i).getId();
			}
		}
		return null;

	}

	// Este codigo es para saber que producto tiene el cliente
	public String gettingProductName(Client client) {
		List<Client> listClients = clientRepository.findAll();
		List<Product> listProducts = productFeignCliet.getProducts();
		List<DetCliPro> listDetCliProducts = detCliProFeignClient.getDetCliPro();
		for (int i = 0; i < listClients.size(); i++) {
			if (listClients.get(i).getIdentityNumber().equals(client.getIdentityNumber())) {
				for (int j = 0; j < listDetCliProducts.size(); j++) {
					if (listDetCliProducts.get(j).getIdCliente().equals(listClients.get(i).getId())) {
						for (int j2 = 0; j2 < listProducts.size(); j2++) {
							if (listProducts.get(j2).getId().equals(listDetCliProducts.get(j).getIdProducto())) {
								return listProducts.get(j2).getNameProduct();

							}
						}
					}
				}

			}
		}
		return null;
	}

	@Override
	@Transactional
	public Client update(Client client) {
		return clientRepository.save(client);
	}

	@Override
	@Transactional
	public void deleteById(String id) {
		clientRepository.deleteById(id);
	}

	@Override
	public Client findByIdentityNumber(String identityNumber) {
		return clientRepository.findByIdentityNumber(identityNumber);
	}

	@Override
	public Map<String, Object> getClientAndProduct(String identityNumber) {
		Map<String, Object> result = new HashMap<>();
		Client client = clientRepository.findByIdentityNumber(identityNumber);
		if (client == null) {
			result.put("Mensaje", "no existe el cliente");
			return result;
		} else {
			result.put("Client", client);
		}
		List<Product> products = productFeignCliet.getProducts(identityNumber);
		if (products.isEmpty()) {
			result.put("Product", "el cliente no tiene producto");
		} else {
			result.put("Product", products);
		}
		return result;

	}

	@Override
	public Map<String, Object> getProductAndClient(String identityNumber) {
		Map<String, Object> result = new HashMap<>();
		Client client = clientRepository.findByIdentityNumber(identityNumber);
		if (client == null) {
			result.put("Mensaje", "no existe el cliente");
			return result;
		} else {
			result.put("Client", client);
		}
		List<Product> products = productFeignCliet.getProducts();
		System.out.println("Cantidad de productos: " + products.size());
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getNameProduct().equals("")) {
				result.put("Product", products.get(i));
				return result;
			} else {
				result.put("Product", "el cliente no tiene producto");
			}
		}
		return result;
	}

//-----------------Codigo WebFlux-------------------------

//	@Override
//	@Transactional(readOnly = true)
//	public Flux<Client> findAll() {
//		return clientRepository.findAll();
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public Mono<Client> findById(String id) {
//		return clientRepository.findById(id);
//	}
//
//	@Override
//	@Transactional
//	public Mono<Client> save(Client client) {
//		client.setRegisterDate(LocalDateTime.now());
//		return clientRepository.save(client);
//	}
//
//	@Override
//	@Transactional
//	public Mono<Client> update(Client client) {
//		return clientRepository.save(client);
//	}
//
//	@Override
//	@Transactional
//	public Mono<Void> deleteById(String id) {
//		return clientRepository.deleteById(id);
//	}

}
