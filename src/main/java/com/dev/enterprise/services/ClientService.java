package com.dev.enterprise.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dev.enterprise.entities.Client;
import com.dev.enterprise.repositories.ClientRepository;
import com.dev.enterprise.resources.exceptions.DatabasePermissionException;
import com.dev.enterprise.resources.exceptions.NoSuchElementException;

import jakarta.persistence.EntityNotFoundException;

//camada onde há a lógica de negócio

//implementar: atualizar cliente e deletar cliente

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	//create
	public Client insert(Client client) {
		return repository.save(client);
	}
	
	//read
	//retorna um cliente pelo id inserido (localhost:8080/clients/id) pelo request "get"
	public Client findById(Long id) {
		Optional<Client> c = repository.findById(id);
		return c.orElseThrow(() -> new NoSuchElementException(id));
	}
	
	//retorna todos os clientes do banco de dados
	public List<Client> findAll(){
		return repository.findAll();
	}
	
	//update
	public Client update(Long id, Client client) {
		try {
			Client c1 = repository.getReferenceById(id);
			updateDatabase(c1, client);
			
			return repository.save(c1);
		}catch(EntityNotFoundException e) {
			throw new NoSuchElementException(id);
		}
	}
	
	public void updateDatabase(Client c1, Client c2) {
		c1.setName(c2.getName());
		c1.setEmail(c2.getEmail());
		c1.setPassword(c2.getPassword());
	}
	
	//delete
	public void delete(Long id) {
		try{
			if(!repository.existsById(id)){
				throw new NoSuchElementException(id);
			}
			
			repository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DatabasePermissionException(id);
		}
	}
	
}
