package com.example.emploinet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.emploinet.model.ResponsableRH;
import com.example.emploinet.service.ResponsableRHService;

@RestController
@RequestMapping("/api/responsablesRH")
public class ResponsableRHController {

    @Autowired
    private ResponsableRHService responsableRHService;

    @GetMapping("/byEntreprise/{entrepriseId}")
    //@PreAuthorize("hasRole('ROLE_ENTREPRISE')")
    @Secured({"ROLE_ENTERPRISE"})
    public ResponseEntity<List<ResponsableRH>> getRhByEntrepriseId(@PathVariable String entrepriseId) {
        List<ResponsableRH> responsables = responsableRHService.getRhByEntrepriseId(entrepriseId);
        return new ResponseEntity<>(responsables, HttpStatus.OK);
    }

    @PostMapping("/create/{entrepriseId}")
    public ResponseEntity<ResponsableRH> createResponsableRH(@RequestBody ResponsableRH responsableRH,
            @PathVariable String entrepriseId) {
        ResponsableRH newResponsableRH = responsableRHService.createResponsableRH(responsableRH, entrepriseId);
        return new ResponseEntity<>(newResponsableRH, HttpStatus.CREATED);
    }

    // @PostMapping("/create/{entrepriseId}")
    // public ResponseEntity<ResponsableRH> createResponsableRH(@RequestBody
    // ResponsableRH responsableRH,
    // @PathVariable String entrepriseId) {
    // ResponsableRH newResponsableRH =
    // responsableRHService.createResponsableRH(responsableRH, entrepriseId);

    // return new ResponseEntity<>(newResponsableRH, HttpStatus.CREATED);
    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponsableRH(@PathVariable String id) {
        responsableRHService.deleteResponsableRH(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
