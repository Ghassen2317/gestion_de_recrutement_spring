package com.example.emploinet.repository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.example.emploinet.model.OffreEmploi;

@Repository
public class OffreEmploiDateExpirationImpl implements OffreEmploiDateExpirationUpdate {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public void updateDateExpiration(String id, Date dateExpiration) {
    Query query = new Query(Criteria.where("id").is(id));
    Update update = new Update().set("dateExpiration", dateExpiration);
    mongoTemplate.updateFirst(query, update, OffreEmploi.class);
  }

}
