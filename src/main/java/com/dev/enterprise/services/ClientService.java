package com.dev.enterprise.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.enterprise.entities.Client;
import com.dev.enterprise.repositories.ClientRepository;

//camada onde há a lógica de negócio
@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	//retorna um cliente pelo id inserido (localhost:8080/clients/id) pelo request "get"
	public Client findById(Long id) {
		Optional<Client> c = repository.findById(id);
		return c.get();
	}
	
	//retorna todos os clientes do banco de dados
	public List<Client> findAll(){
		return repository.findAll();
	}
}
