package com.example.emploinet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.emploinet.model.Entreprise;
import com.example.emploinet.model.OffreEmploi;
import com.example.emploinet.model.ResponsableRH;
import com.example.emploinet.repository.EntrepriseRepository;
import com.example.emploinet.repository.ResponsableRHRepository;

@Service
public class ResponsableRHService {
  @Autowired
  private ResponsableRHRepository responsableRHRepository;

  

  public List<ResponsableRH> getRhByNomEntreprise(String nomEntreprise) {

    return responsableRHRepository.findRhByNomEntreprise(nomEntreprise);
  }

  public ResponsableRH createResponsableRH(ResponsableRH responsableRH){
    return responsableRHRepository.save(responsableRH);
  }

}

