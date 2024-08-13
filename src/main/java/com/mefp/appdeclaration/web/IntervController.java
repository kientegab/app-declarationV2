package com.mefp.appdeclaration.web;

import com.mefp.appdeclaration.entities.IntervenantSaisie;
import com.mefp.appdeclaration.entities.NatureSaisie;
import com.mefp.appdeclaration.service.IntervService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/dgd")
public class IntervController {
    @Autowired
    private final IntervService intervService;

    public IntervController(IntervService intervService) {
        this.intervService = intervService;
    }

    @GetMapping("intervenant/fiche/{id}")
    public ResponseEntity<List<IntervenantSaisie>> findIntervByFicheSaisieId(@PathVariable("id") Long id){
        List<IntervenantSaisie> laFiche= intervService.findIntervByFicheSaisieId(id);
        return ResponseEntity.ok(laFiche);
    }
}
