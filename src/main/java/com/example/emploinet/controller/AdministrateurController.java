package com.example.emploinet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.emploinet.model.Administrateur;
import com.example.emploinet.service.AdministrateurService;

@RestController
@RequestMapping("/api/admin")  
public class AdministrateurController {
  @Autowired
  private AdministrateurService administrateurService;


  @PostMapping("/create")
  public ResponseEntity<Administrateur> createAdministrateur(@RequestBody Administrateur administrateur) {
    Administrateur createdAdmin = administrateurService.createAdministrateur(administrateur);
    return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
  }
}
