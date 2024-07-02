package com.example.emploinet.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.emploinet.exception.ResourceNotFoundException;
import com.example.emploinet.model.Entreprise;
import com.example.emploinet.model.ResponsableRH;
import com.example.emploinet.model.Role;
import com.example.emploinet.repository.EntrepriseRepository;
import com.example.emploinet.repository.ResponsableRHRepository;
import com.example.emploinet.repository.RoleRepository;

@Service
public class ResponsableRHService {
  @Autowired
  private ResponsableRHRepository responsableRHRepository;

  @Autowired
  private EntrepriseRepository entrepriseRepository;

  @Autowired
  private RoleRepository roleRepository;


  public ResponsableRH createResponsableRH(ResponsableRH responsableRH, String entrepriseId) {
    Entreprise entreprise = entrepriseRepository.findById(entrepriseId)
        .orElseThrow(() -> new ResourceNotFoundException("Entreprise not found"));
    responsableRH.setEntrepriseId(entreprise.getId());
    return responsableRHRepository.save(responsableRH);
  }

  // public ResponsableRH createResponsableRH(ResponsableRH responsableRH, String entrepriseId) {
  //       // Check if the entreprise exists
  //       Entreprise entreprise = entrepriseRepository.findById(entrepriseId)
  //               .orElseThrow(() -> new ResourceNotFoundException("Entreprise not found with id: " + entrepriseId));

  //       // Encode the matricule before saving to database
  //       responsableRH.setMatricule(passwordEncoder.encode(responsableRH.getMatricule()));

  //       // Assign the role RESPONSABLERH
  //       Role role = roleRepository.findByName("ROLE_RESPONSABLERH")
  //               .orElseGet(() -> roleRepository.save(new Role(null, "ROLE_RESPONSABLERH")));

  //       Set<Role> roles = new HashSet<>();
  //       roles.add(role);
  //       responsableRH.setRoles(roles);

  //       // Set the entrepriseId
  //       responsableRH.setEntrepriseId(entreprise.getId());

  //       // Save the ResponsableRH
  //       return responsableRHRepository.save(responsableRH);
  //   }

  public List<ResponsableRH> getRhByEntrepriseId(String entrepriseId) {
    return responsableRHRepository.findByEntrepriseId(entrepriseId);
  }



  public void deleteResponsableRH(String id) {
    if (!responsableRHRepository.existsById(id)) {
      throw new ResourceNotFoundException("Responsable RH not found");
    }
    responsableRHRepository.deleteById(id);
  }

}
