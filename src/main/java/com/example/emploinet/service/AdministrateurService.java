package com.example.emploinet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.emploinet.repository.AdministrateurRepository;

@Service
public class AdministrateurService {
  @Autowired
  private AdministrateurRepository administrateurRepository;

  
}
