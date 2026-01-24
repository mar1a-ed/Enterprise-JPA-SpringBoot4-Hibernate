package com.dev.enterprise.test;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.dev.enterprise.entities.Client;
import com.dev.enterprise.entities.Order;
import com.dev.enterprise.entities.OrderItem;
import com.dev.enterprise.entities.Product;
import com.dev.enterprise.entities.enums.OrderStatus;
import com.dev.enterprise.repositories.ClientRepository;
import com.dev.enterprise.repositories.OrderItemRepository;
import com.dev.enterprise.repositories.OrderRepository;
import com.dev.enterprise.repositories.ProductRepository;

@Configuration
@Profile("test")
public class Teste implements CommandLineRunner{
	
	@Autowired
	private ClientRepository clientR;

	@Autowired
	private OrderRepository orderR;
	
	@Autowired
	private ProductRepository productR;
	
	@Autowired
	private OrderItemRepository orderIR;
	
	@Override
	public void run(String... args) throws Exception {
		
		Client c1 = new Client(null, "Maria Eduarda Corrêa", "mecs@email.com", "12345", "123.456.789-10", "38500-000");
		Client c2 = new Client(null, "Artur Mandela Flanela", "artigo@email.com", "23187", "213.451.772-12", "74580-900");
		
		clientR.saveAll(Arrays.asList(c1,c2));
		
		Order o1 = new Order(null, Instant.parse("2026-01-20T10:30:00Z"), c1, OrderStatus.CONFIRMED);
		Order o2 = new Order(null, Instant.parse("2021-11-29T22:31:09Z"), c1, OrderStatus.SENT);
		
		orderR.saveAll(Arrays.asList(o1,o2));
		
		Product p1 = new Product(null, "Notebook", 1950.00, 10);
		Product p2 = new Product(null, "Iphone 17", 7800.10, 15);
		
		productR.saveAll(Arrays.asList(p1,p2));
		
		OrderItem oi1 = new OrderItem(o2, p2, 3, p2.getPrice());
		OrderItem oi2 = new OrderItem(o1, p1, 2, p1.getPrice());
		
		orderIR.saveAll(Arrays.asList(oi1,oi2));
		
	}

	
	
	
}
