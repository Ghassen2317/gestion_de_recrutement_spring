package com.example.emploinet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.emploinet.repository.CandidatureRepository;

@Service
public class CandidatureService {
  @Autowired
  private CandidatureRepository candidatureRepository;
}
