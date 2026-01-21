package com.dev.enterprise.test;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.dev.enterprise.entities.Client;
import com.dev.enterprise.entities.Order;
import com.dev.enterprise.repositories.ClientRepository;
import com.dev.enterprise.repositories.OrderRepository;

@Configuration
@Profile("test")
public class Teste implements CommandLineRunner{
	
	@Autowired
	private ClientRepository clientR;

	@Autowired
	private OrderRepository orderR;
	
	@Override
	public void run(String... args) throws Exception {
		
		Client c1 = new Client(null, "Maria Eduarda Corrêa", "mecs@email.com", "12345", "123.456.789-10");
		Client c2 = new Client(null, "Artur Mandela Flanela", "artigo@email.com", "23187", "213.451.772-12");
		
		clientR.saveAll(Arrays.asList(c1,c2));
		
		Order o1 = new Order(null, Instant.parse("2026-01-20T10:30:00Z"), c1);
		Order o2 = new Order(null, Instant.parse("2021-11-29T22:31:09Z"), c1 );
		
		orderR.saveAll(Arrays.asList(o1,o2));
		
	}

	
	
	
}
