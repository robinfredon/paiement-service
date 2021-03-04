package com.microservice.paiementservice.dao;

import com.microservice.paiementservice.model.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaiementDAO extends JpaRepository<Paiement, Integer>  {
	public List<Paiement> findAll();
	public Paiement findById(int id);
	public Paiement save(Paiement Paiement);
}
