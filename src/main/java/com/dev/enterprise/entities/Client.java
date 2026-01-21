package com.dev.enterprise.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_client")
public class Client	implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //pk auto_increment
	private Long id;
	
	@Column(nullable = false, length = 200) //nao pode ser nulo e a capacidade maxima de 200 caracteres
	private String name;
	
	@Column(unique = true) //tem que ser unico
	private String email;
	
	@JsonIgnore
	private String password;
	
	@Column(unique = true, nullable = false, length = 14) //tem que ser unico, nao pode ser nulo e capacidade de 11 caracteres
	private String cpf;
	
	@OneToMany(mappedBy = "client")
	private List<Order> orders = new ArrayList<Order>();
	
	public Client() {
	}

	public Client(Long id, String name, String email, String password, String cpf) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.cpf = cpf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@JsonIgnore //evita looping no request 'get'
	public List<Order> getOrders(){
		return orders;
	}
	
	public void addOrder(Order order) { //add um pedido à lista
		this.orders.add(order);
	}
	
	public void deleteOrder(Order order) { //remove um pedido da lista
		this.orders.remove(order);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(id, other.id);
	}

}
