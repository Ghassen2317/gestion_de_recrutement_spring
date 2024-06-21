// package com.example.emploinet.controller;

// import java.util.List;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.example.emploinet.model.Entreprise;
// import com.example.emploinet.service.EntrepriseService;
// import org.springframework.web.bind.annotation.RequestParam;


// @RestController
// @RequestMapping("/api/entreprises")
// public class EntrepriseController {

//   @Autowired
//   private EntrepriseService entrepriseService;

//   @GetMapping("/all")
//   public List<Entreprise> getAllEntreprise() {
//     return entrepriseService.getAllEntreprise();
//   }

  @PostMapping("/create")
  public ResponseEntity<Entreprise> createEntreprise(@RequestBody Entreprise entreprise) {
    Entreprise createdEntreprise = entrepriseService.createEntreprise(entreprise);
    return new ResponseEntity<>(createdEntreprise, HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteEntreprise(@PathVariable String id) {
    entrepriseService.deleteEntreprise(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // @GetMapping("/{id}")
  // public ResponseEntity<Entreprise> getEntrepriseById(@PathVariable String id) {
  //     Optional<Entreprise> entreprise = entrepriseService.getEntrepriseById(id);
  //     if (entreprise.isPresent()) {
  //       return new ResponseEntity<>(entreprise, HttpStatus.OK);
  //     }
  //     else {
  //       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  //     }
  // }
  
// }
