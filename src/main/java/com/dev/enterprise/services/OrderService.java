package com.dev.enterprise.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.dev.enterprise.entities.Order;
import com.dev.enterprise.entities.OrderItem;
import com.dev.enterprise.entities.Product;
import com.dev.enterprise.repositories.OrderRepository;
import com.dev.enterprise.repositories.ProductRepository;
import com.dev.enterprise.resources.exceptions.NoSuchElementException;

import jakarta.transaction.Transactional;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	//create
	@Transactional
	public Order insert(Order order) {
		order.setDate(Instant.now());
		
		order = orderRepository.save(order);
		
		for(OrderItem x: order.getItems()) {
			Product prodAtual = productRepository.findById(x.getProduct().getId()).orElseThrow(() ->  new NoSuchElementException(x));
		
			if(prodAtual.getStockQuantity() >= x.getQuantity()) {
				prodAtual.setStockQuantity(prodAtual.getStockQuantity() - x.getQuantity());
				x.setPrice(prodAtual.getPrice());
				productRepository.save(prodAtual);
			}else {
				throw new RuntimeException("Estoque insuficiente.");
			}
		
		}
		
		return orderRepository.save(order);
		
	}
	
	
	//read
	public Order findById(@PathVariable Long id) {
		Optional<Order> o = orderRepository.findById(id);
		return o.orElseThrow(() -> new NoSuchElementException(id));
	}
	
	public List<Order> findAll(){
		return orderRepository.findAll();
	}
	
}
