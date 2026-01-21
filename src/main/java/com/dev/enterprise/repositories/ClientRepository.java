package com.dev.enterprise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.enterprise.entities.Client;

//repositório onde é guardado as informações das entitys
@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
