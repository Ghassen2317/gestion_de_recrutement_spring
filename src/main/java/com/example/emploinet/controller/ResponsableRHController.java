package com.example.emploinet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.emploinet.model.ResponsableRH;
import com.example.emploinet.service.ResponsableRHService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/responsablesRH")
public class ResponsableRHController {

    @Autowired
    private ResponsableRHService responsableRHService;

    @GetMapping("/byEntreprise/{nomEntreprise}")

    public List<ResponsableRH> getResponsablesByNomEntreprise(@PathVariable String nomEntreprise) {
        return responsableRHService.getRhByNomEntreprise(nomEntreprise);

}

    @PostMapping("/createRH")
    public ResponseEntity<ResponsableRH> createResponsableRH(@RequestBody ResponsableRH responsableRH) {
        ResponsableRH newResponsableRH = responsableRHService.createResponsableRH(responsableRH);
        
        return new ResponseEntity<>(newResponsableRH, HttpStatus.CREATED);
    }
    
}
