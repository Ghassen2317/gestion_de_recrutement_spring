package com.example.emploinet.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.emploinet.model.OffreEmploi;

public interface OffreEmploiRepository extends MongoRepository<OffreEmploi, String> {
  List<OffreEmploi> findByNomEntreprise(String nomEntreprise); 
}

