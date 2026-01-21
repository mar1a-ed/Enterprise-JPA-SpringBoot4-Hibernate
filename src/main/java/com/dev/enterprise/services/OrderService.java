package com.dev.enterprise.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.dev.enterprise.entities.Order;
import com.dev.enterprise.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	public Order findById(@PathVariable Long id) {
		Optional<Order> o = repository.findById(id);
		return o.get();
	}
	
	public List<Order> findAll(){
		return repository.findAll();
	}
	
}
