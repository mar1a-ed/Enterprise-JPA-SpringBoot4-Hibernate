package com.dev.enterprise.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.enterprise.entities.Product;
import com.dev.enterprise.repositories.ProductRepository;
import com.dev.enterprise.resources.exceptions.NoSuchElementException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	//create
	public Product insert(Product product) {
		return repository.save(product);
	}
	
	//read
	public Product findById(Long id) {
		Optional<Product> p = repository.findById(id);
		return p.get();
	}
	
	public List<Product> findAll(){
		return repository.findAll();
	}
	
	//update
	public Product update(Long id, Product prod) {
		try {
			Product p1 = repository.getReferenceById(id);
			updateDatabase(p1, prod);
			
			return repository.save(p1);
		
		}catch(EntityNotFoundException e) {
			throw new NoSuchElementException(id);
		}
	}
	
	public void updateDatabase(Product p1, Product p2) {
		p1.setPrice(p2.getPrice());
		p1.setStockQuantity(p2.getStockQuantity());
	}
	
}
