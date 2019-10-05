package com.example.demo.repository;

import com.example.demo.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order,Integer> {
    Optional<Order> findByCustomerName(String customerName);
    List<Order> findAll();

}
