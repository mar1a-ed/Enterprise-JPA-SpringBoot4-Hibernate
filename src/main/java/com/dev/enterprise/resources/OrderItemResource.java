package com.dev.enterprise.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.enterprise.entities.OrderItem;
import com.dev.enterprise.services.OrderItemService;

@RestController
@RequestMapping(value = "/order_items")
public class OrderItemResource {
	
	@Autowired
	private OrderItemService service;
	
	@GetMapping
	public ResponseEntity<List<OrderItem>> findAll(){
		List<OrderItem> oi = service.findAll();
		return ResponseEntity.ok().body(oi);
	}
	
}
