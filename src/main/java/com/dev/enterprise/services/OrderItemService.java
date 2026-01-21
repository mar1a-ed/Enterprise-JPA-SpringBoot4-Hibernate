package com.dev.enterprise.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.enterprise.entities.OrderItem;
import com.dev.enterprise.repositories.OrderItemRepository;

@Service
public class OrderItemService {
	
	@Autowired
	private OrderItemRepository repository;

	public OrderItem findById(Long id_pedido, Long id_produto) {
		return repository.findAll().stream().filter(x -> x.getOrder().getId().equals(id_pedido) && 
				x.getProduct().getId().equals(id_produto))
				.findFirst().orElse(null);
	}
	
	public List<OrderItem> findAll(){
		return repository.findAll();
	}

}
