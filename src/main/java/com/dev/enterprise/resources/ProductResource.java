package com.dev.enterprise.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dev.enterprise.entities.Product;
import com.dev.enterprise.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired
	private ProductService service;
	
	//create
	@PostMapping
	public ResponseEntity<Product> insert(@RequestBody Product product){
		product = service.insert(product);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(product);
	}
	
	//read
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id){
		Product p = service.findById(id);
		return ResponseEntity.ok().body(p);
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> findAll(){
		List<Product> ps = service.findAll();
		return ResponseEntity.ok().body(ps);
	}
	
	//update
	@PutMapping(value = "/{id}")
	public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product prod){
		prod = service.update(id, prod);
		return ResponseEntity.ok().body(prod);
	}
}
