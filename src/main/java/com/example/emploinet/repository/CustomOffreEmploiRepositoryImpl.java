package com.example.emploinet.repository;

import com.example.emploinet.model.OffreEmploi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomOffreEmploiRepositoryImpl implements CustomOffreEmploiRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<OffreEmploi> findByFilters(String typeContrat, String region, Integer nbExperience, String diplome) {
        Query query = new Query();
        List<Criteria> criteriaList = new ArrayList<>();

        if (typeContrat != null) {
            criteriaList.add(Criteria.where("typeContrat").is(typeContrat));
        }

        if (region != null) {
            criteriaList.add(Criteria.where("region").is(region));
        }

        if (nbExperience != null) {
            criteriaList.add(Criteria.where("nbExperience").is(nbExperience));
        }

        if (diplome != null) {
            criteriaList.add(Criteria.where("diplome").is(diplome));
        }

        if (!criteriaList.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
        }

        return mongoTemplate.find(query, OffreEmploi.class);
    }
}
