package com.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.Address;

public interface AddressRepo extends JpaRepository<Address,Integer> {
	List<Address> findByUserId(int userId);
}
