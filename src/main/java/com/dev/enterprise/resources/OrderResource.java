package com.dev.enterprise.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.enterprise.entities.Order;
import com.dev.enterprise.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

	@Autowired
	private OrderService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id){
		Order o = service.findById(id);
		return ResponseEntity.ok().body(o);
	}
	
	@GetMapping
	public ResponseEntity<List<Order>> findAll(){
		List<Order> o = service.findAll();
		return ResponseEntity.ok().body(o);
	}
	
}
