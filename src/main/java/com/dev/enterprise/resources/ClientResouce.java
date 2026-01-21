package com.dev.enterprise.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.enterprise.entities.Client;
import com.dev.enterprise.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientResouce {
	
	@Autowired
	private ClientService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Client> findById(@PathVariable Long id){
		Client c = service.findById(id);
		return ResponseEntity.ok().body(c);
	}
	
	@GetMapping
	public ResponseEntity<List<Client>> findAll(){
		List<Client> c = service.findAll();
		return ResponseEntity.ok().body(c);
	}
}
