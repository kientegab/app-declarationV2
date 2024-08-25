package com.mefp.appdeclaration.web;

import com.mefp.appdeclaration.entities.Destination;
import com.mefp.appdeclaration.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping("api/dgd")
public class DestinationController {
    @Autowired
    private final DestinationService destinationService;


    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping("destination/fiche/{id}")
    public ResponseEntity<Optional<Destination>> findByFicheSaisieId(@PathVariable("id") Long id){
        Optional<Destination> laFiche= destinationService.findByFicheSaisieId(id);
        return ResponseEntity.ok(laFiche);
    }

}
