
package com.example.emploinet.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.emploinet.enums.TypeContrat;
import com.example.emploinet.model.OffreEmploi;
import com.example.emploinet.service.OffreEmploiService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/offresEmploi")

public class OffreEmploiController {
  @Autowired
  private OffreEmploiService offreEmploiService;

  // localhost:8080/api/offresEmploi?nomOffreEmploi=java

  @PostMapping("/create")
  public ResponseEntity<OffreEmploi> createOffreEmploi(@RequestBody OffreEmploi offreEmploi) {
    OffreEmploi createdOffreEmploi = offreEmploiService.createOffreEmploi(offreEmploi);
    return new ResponseEntity<>(createdOffreEmploi, HttpStatus.CREATED);
  }

  @GetMapping("/all")
  public List<OffreEmploi> getAllOffreEmploi() {
    return offreEmploiService.getAllOffreEmploi();
  }

  @GetMapping("/byId/{id}")
  public Optional<OffreEmploi> getOffreEmploiById(@PathVariable String id) {
    return offreEmploiService.getOffreEmploiById(id);
  }

  @GetMapping("/byNomEntreprise/{nomEntreprise}")
  public List<OffreEmploi> getNomEntreprise(@PathVariable String nomEntreprise) {
    return offreEmploiService.getNomEntreprise(nomEntreprise);
  }

  @GetMapping("/byTypeContrat/{typeContrat}")
  public List<OffreEmploi> getTypeContrat(@PathVariable TypeContrat typeContrat) {
    return offreEmploiService.getTypeContrat(typeContrat);
  }

  @GetMapping("/byNomOffreEmploi/{nomOffreEmploi}")
  public List<OffreEmploi> getNomOffreEmploi(@PathVariable String nomOffreEmploi) {
    return offreEmploiService.getNomOffreEmploi(nomOffreEmploi);
  }

  @PutMapping("updateDateExpiration/{id}")
  public ResponseEntity<OffreEmploi> updateDateExpiration(@PathVariable String id,
      @RequestBody Map<String, String> request) {
    Date dateExpiration;
    try {
      dateExpiration = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX").parse(request.get("dateExpiration"));
    } catch (ParseException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    OffreEmploi updatedOffreEmploi = offreEmploiService.updateDateExpiration(id, dateExpiration);
    return new ResponseEntity<>(updatedOffreEmploi, HttpStatus.OK);
  }

  @DeleteMapping("/byId/{id}")
  public void deleteOffreEmploi(@PathVariable String id) {
    offreEmploiService.deleteOffreEmploi(id);
  }

  // @GetMapping("")
  // public List<OffreEmploi> getDateExpiration(@RequestParam Date dateExpiration)
  // {
  // return offreEmploiService.getDateExpiration(dateExpiration);
  // }

  @GetMapping("/byRegion/{region}")
  public List<OffreEmploi> getRegion(@PathVariable String region) {
  return offreEmploiService.getRegion(region);
  }

  @GetMapping("/byNbExperienceOffre/{nbExperienceOffre}")
  public List<OffreEmploi> getNbExperienceOffre(@PathVariable Integer
  nbExperienceOffre) {
  return offreEmploiService.getNbExperienceOffre(nbExperienceOffre);
  }
}
