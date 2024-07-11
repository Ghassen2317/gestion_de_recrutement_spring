package com.example.emploinet.repository;

import java.util.List;

import com.example.emploinet.model.OffreEmploi;

public interface CustomOffreEmploiRepository {

  List<OffreEmploi> findByFilters(String typeContrat, String region, Integer nbExperience, String diplome);

}
