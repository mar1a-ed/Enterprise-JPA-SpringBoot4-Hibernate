package com.dev.enterprise.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.enterprise.entities.Order;
import com.dev.enterprise.entities.OrderItem;
import com.dev.enterprise.entities.Product;
import com.dev.enterprise.entities.enums.OrderStatus;
import com.dev.enterprise.repositories.OrderItemRepository;
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
	
	@Autowired
	private OrderItemRepository orderIRepository;
	
	//create
	@Transactional
	public Order insert(Order order) {
		order.setDate(Instant.now());
		
		order = orderRepository.save(order);
		
		for(OrderItem x: order.getItems()) {
			Product prodAtual = productRepository.findById(x.getProduct().getId()).orElseThrow(() ->  new NoSuchElementException(x));
		
			if(prodAtual.getStockQuantity() >= x.getQuantity()) {
				prodAtual.setStockQuantity(prodAtual.getStockQuantity() - x.getQuantity());
				x.setOrder(order);
				x.setPrice(prodAtual.getPrice());
				
				orderIRepository.save(x);
				productRepository.save(prodAtual);
			}else {
				throw new RuntimeException("Estoque insuficiente.");
			}
		
		}
		
		return orderRepository.save(order);
		
	}
	
	
	//read
	public Order findById(Long id) {
		Optional<Order> o = orderRepository.findById(id);
		return o.orElseThrow(() -> new NoSuchElementException(id));
	}
	
	public List<Order> findAll(){
		return orderRepository.findAll();
	}
	
	//cancelled
	public Order cancelled(Long id) {
		try {
			Order order = orderRepository.getReferenceById(id);
			
			if(order.getStatus() == OrderStatus.SENT || order.getStatus() == OrderStatus.DELIVERED) {
				throw new RuntimeException("Order Sent or Delivered cannot be Cancelled!");
			}
			
			order.setOrderStatus(OrderStatus.CANCELED);
			
			for(OrderItem x: order.getItems()) {
				Product prod = x.getProduct();
				prod.setStockQuantity(x.getQuantity() + prod.getStockQuantity());
				productRepository.save(prod);
			}
			
			return orderRepository.save(order);
		}catch(NoSuchElementException e) {
			throw new NoSuchElementException(id);
		}
	}
	
}
