package com.example.emploinet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.emploinet.model.Candidature;
import com.example.emploinet.model.OffreEmploi;

public interface CandidatureRepository extends MongoRepository<Candidature, String> {
    List<Candidature> findByNomOffreEmploi(String nomOffreEmploi);
    Optional<Candidature> findById(String id);
    
}
