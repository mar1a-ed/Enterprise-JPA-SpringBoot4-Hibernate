package com.dev.enterprise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.enterprise.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
