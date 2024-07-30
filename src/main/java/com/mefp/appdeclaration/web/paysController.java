package com.mefp.appdeclaration.web;


import com.mefp.appdeclaration.entities.Pays;
import com.mefp.appdeclaration.service.PaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@CrossOrigin("http://localhost:4200")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/dgd")

public class paysController {
    @Autowired
    private  final PaysService  paysService;

    public paysController(PaysService paysService) {
        this.paysService = paysService;
    }

    @PostMapping("/pays")
    public ResponseEntity<Pays> createPays(@RequestBody Pays pays){
        Pays pays1= paysService.createPays(pays);
        return  ResponseEntity.ok(pays1);
    }

    @GetMapping("/pays/{id}")
    public ResponseEntity <Pays> findById(@PathVariable("id") Long id) {
        Optional<Pays> optionalPays = paysService.findById(id);
        return optionalPays.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/pays")
    public ResponseEntity<List<Pays>> findAllList(){
        List<Pays> listePays= paysService.findAllPays();
        return ResponseEntity.ok(listePays);
    }

    @PutMapping("/pays/{id}")
    public ResponseEntity<Optional<Pays>> updatePays(@PathVariable("id") Long id, @RequestBody Pays updatedPays) {
        Optional<Pays> pays = paysService.updatePays(id, updatedPays);
        return ResponseEntity.ok(pays);
    }

    @DeleteMapping("/pays/{id}")
    public ResponseEntity<Void> deletePays(@PathVariable("id") Long id) {
        paysService.deletePays(id);
        return ResponseEntity.noContent().build();
    }
}
