package com.mefp.appdeclaration.web;

import com.mefp.appdeclaration.entities.Interpele;
import com.mefp.appdeclaration.service.InterpeleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("api/dgd")
public class InterpeleController {
    @Autowired
    private final InterpeleService interpeleService;

    public InterpeleController(InterpeleService interpeleService) {
        this.interpeleService = interpeleService;
    }

    @GetMapping("interpele/fiche/{id}")
    public ResponseEntity<Optional<Interpele>> findByFicheSaisieId(@PathVariable("id") Long id){
        Optional<Interpele> laFiche= interpeleService.findByFicheSaisieId(id);
        return ResponseEntity.ok(laFiche);
    }
}
