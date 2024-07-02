package com.example.emploinet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.emploinet.dto.UpdatePasswordRequestDTO;
import com.example.emploinet.exception.ResourceNotFoundException;
import com.example.emploinet.model.Entreprise;
import com.example.emploinet.service.EntrepriseService;

@RestController
@RequestMapping("/api/entreprises")
public class EntrepriseController {

  @Autowired
  private EntrepriseService entrepriseService;

  @GetMapping("/all")
  public ResponseEntity<List<Entreprise>> getAllEntreprise() {
    List<Entreprise> response = entrepriseService.getAllEntreprise();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("/create")
  public ResponseEntity<Entreprise> createEntreprise(@RequestBody Entreprise entreprise) {
    Entreprise createdEntreprise = entrepriseService.createEntreprise(entreprise);
    return new ResponseEntity<>(createdEntreprise, HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEntreprise(@PathVariable String id) {
    entrepriseService.deleteEntreprise(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Entreprise> getEntrepriseById(@PathVariable String id) {
    Optional<Entreprise> entreprise = entrepriseService.getEntrepriseById(id);
    if (entreprise.isPresent()) {
      return new ResponseEntity<>(entreprise.get(), HttpStatus.OK);
    } else {
      throw new ResourceNotFoundException("Entreprise not found");
    }
  }

  @PostMapping("/update-forgot-password")
  public ResponseEntity<?> updateForgotPassword(@RequestBody UpdatePasswordRequestDTO request) {
    try {
      Entreprise updatedEntreprise = entrepriseService.updateForgotPassword(request.getEmail(), request.getPassword());
      return ResponseEntity.ok().body(updatedEntreprise);
    } catch (ResourceNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }
}