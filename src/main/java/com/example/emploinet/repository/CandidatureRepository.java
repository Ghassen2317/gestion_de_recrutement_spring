package com.example.emploinet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.emploinet.enums.Diplome;
import com.example.emploinet.enums.Sexe;
import com.example.emploinet.model.Candidature;

public interface CandidatureRepository extends MongoRepository<Candidature, String> {
    List<Candidature> findByNomOffreEmploi(String nomOffreEmploi);

    Optional<Candidature> findById(String id);

    Candidature findBySexe(Sexe sexe);

    Candidature findByDiplome(Diplome diplome);

    Optional<Candidature> findByEmail(String email);

}
