package com.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.Cart;

public interface CartRepo extends JpaRepository<Cart,Integer>{
	Optional<Cart> findByUser_Id(int userId);
}
