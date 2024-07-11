
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/offresEmploi")

public class offreEmploiController {
  @Autowired
  private OffreEmploiService offreEmploiService;

  // localhost:8080/api/offresEmploi?nomOffreEmploi=java


  // Entreprise + RH
  @PostMapping("/create")
  public ResponseEntity<OffreEmploi> createOffreEmploi(@RequestBody OffreEmploi offreEmploi) {
    OffreEmploi createdOffreEmploi = offreEmploiService.createOffreEmploi(offreEmploi);
    return new ResponseEntity<>(createdOffreEmploi, HttpStatus.CREATED);
  }


  // All users
  @GetMapping("/all")
  public ResponseEntity<List<OffreEmploi>> getAllOffreEmploi() {
    List<OffreEmploi> offreEmplois = offreEmploiService.getAllOffreEmploi();
    return new ResponseEntity<>(offreEmplois, HttpStatus.OK);
  }


  // Entreprise + RH
  @GetMapping("/byId/{id}")
  public ResponseEntity<Optional<OffreEmploi>> getOffreEmploiById(@PathVariable String id) {
    Optional<OffreEmploi> offreEmploi = offreEmploiService.getOffreEmploiById(id);
    return new ResponseEntity<>(offreEmploi, HttpStatus.OK);
  }

  // All users
  @GetMapping("/byNomEntreprise/{nomEntreprise}")
  public ResponseEntity<List<OffreEmploi>> getNomEntreprise(@PathVariable String nomEntreprise) {
    List<OffreEmploi> offreEmplois = offreEmploiService.getNomEntreprise(nomEntreprise);
    return new ResponseEntity<>(offreEmplois, HttpStatus.OK);
  }

  // All users
  @GetMapping("/byTypeContrat/{typeContrat}")
  public ResponseEntity<List<OffreEmploi>> getTypeContrat(@PathVariable TypeContrat typeContrat) {
    List<OffreEmploi> offreEmplois = offreEmploiService.getTypeContrat(typeContrat);
    return new ResponseEntity<>(offreEmplois, HttpStatus.OK);
  }


  // All users
  @GetMapping("/byNomOffreEmploi/{nomOffreEmploi}")
  public ResponseEntity<List<OffreEmploi>> getNomOffreEmploi(@PathVariable String nomOffreEmploi) {
    List<OffreEmploi> offreEmplois = offreEmploiService.getNomOffreEmploi(nomOffreEmploi);
    return new ResponseEntity<>(offreEmplois, HttpStatus.OK);
  }

  // ENtreprtise + RH
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

  // ENreprise + RH
  @DeleteMapping("/byId/{id}")
  public ResponseEntity<Void> deleteOffreEmploi(@PathVariable String id) {
    offreEmploiService.deleteOffreEmploi(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  // @GetMapping("")
  // public List<OffreEmploi> getDateExpiration(@RequestParam Date dateExpiration)
  // {
  // return offreEmploiService.getDateExpiration(dateExpiration);
  // }


  // All users
  @GetMapping("/byRegion/{region}")
  public ResponseEntity<List<OffreEmploi>> getRegion(@PathVariable String region) {
  List<OffreEmploi> offreEmplois = offreEmploiService.getRegion(region);
  return new ResponseEntity<>(offreEmplois, HttpStatus.OK);
  }


  // All users
  @GetMapping("/byNbExperienceOffre/{nbExperienceOffre}")
  public ResponseEntity<List<OffreEmploi>> getNbExperienceOffre(@PathVariable Integer
  nbExperienceOffre) {
  List<OffreEmploi> offreEmplois = offreEmploiService.getNbExperienceOffre(nbExperienceOffre);
  return new ResponseEntity<>(offreEmplois, HttpStatus.OK);
  } 

  @GetMapping("/filtered")
    public ResponseEntity<List<OffreEmploi>> getFilteredOffres(
            @RequestParam(required = false) String typeContrat,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) Integer nbExperience,
            @RequestParam(required = false) String diplome) {

        List<OffreEmploi> filteredOffers = offreEmploiService.findOffers(typeContrat, region, nbExperience, diplome);
        return new ResponseEntity<>(filteredOffers, HttpStatus.OK);
    }
}
