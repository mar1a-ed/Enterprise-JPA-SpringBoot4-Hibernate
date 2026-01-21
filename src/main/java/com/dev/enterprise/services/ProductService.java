package com.dev.enterprise.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.dev.enterprise.entities.Product;
import com.dev.enterprise.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	public Product findById(@PathVariable Long id) {
		Optional<Product> p = repository.findById(id);
		return p.get();
	}
	
	public List<Product> findAll(){
		return repository.findAll();
	}
	
}
