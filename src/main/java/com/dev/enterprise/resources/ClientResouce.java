package com.dev.enterprise.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dev.enterprise.entities.Client;
import com.dev.enterprise.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientResouce {
	
	@Autowired
	private ClientService service;
	
	//create
	@PostMapping
	public ResponseEntity<Client> insert(@RequestBody Client client){
		client = service.insert(client);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(client.getId()).toUri(); //atualiza a uri do cliente inserido
		return ResponseEntity.created(uri).body(client);
	}
	
	//read
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
	
	//update
	@PutMapping(value = "/{id}")
	public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client){
		client = service.update(id, client);
		return ResponseEntity.ok().body(client);
	}
	
	//delete
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.ok().body(null);
	}
	
}
