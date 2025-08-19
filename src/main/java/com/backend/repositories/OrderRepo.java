package com.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.Order;

public interface OrderRepo extends JpaRepository<Order, Integer> {
	List<Order> findByUser_Id(int userId);
}
