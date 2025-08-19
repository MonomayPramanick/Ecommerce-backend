package com.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.model.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer>{

}
