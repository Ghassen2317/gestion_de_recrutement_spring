package com.example.emploinet.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.emploinet.model.Entreprise;

import java.util.Optional;




public interface EntrepriseRepository extends MongoRepository<Entreprise, String> {

  Entreprise findByNomEntreprise(String nomEntreprise);
  Optional<Entreprise> findByFirstNameAndLastName(String firstName, String lastName);
  Optional<Entreprise> findByEmail(String email);

}
