package com.example.emploinet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.emploinet.model.OffreEmploi;
import com.example.emploinet.repository.ResponsableRHRepository;

@Service
public class ResponsableRHService {
  @Autowired
  private ResponsableRHRepository responsableRHRepository;

}