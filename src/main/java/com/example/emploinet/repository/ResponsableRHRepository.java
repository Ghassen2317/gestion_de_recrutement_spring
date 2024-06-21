package com.example.emploinet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.emploinet.model.ResponsableRH;

public interface ResponsableRHRepository extends MongoRepository<ResponsableRH, String> {

  
  List<ResponsableRH> findByEntrepriseId(String entrepriseId);
  Optional<ResponsableRH> findByMatricule(String matricule);
}
