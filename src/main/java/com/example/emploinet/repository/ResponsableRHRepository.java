package com.example.emploinet.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.emploinet.model.ResponsableRH;


public interface ResponsableRHRepository extends MongoRepository<ResponsableRH, String> {

    @Query("{'Entreprise.nomEntreprise' : ?0}")
    List<ResponsableRH> findRhByNomEntreprise(String nomEntreprise);
}
