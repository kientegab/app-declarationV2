package com.mefp.appdeclaration.web;

import com.mefp.appdeclaration.entities.Nature;
import com.mefp.appdeclaration.entities.NatureSaisie;
import com.mefp.appdeclaration.entities.dto.Naturedto;
import com.mefp.appdeclaration.service.NatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin ("*")
@RestController
@RequestMapping ("/api/dgd")

public class NatureController {
    @Autowired
    private  final NatureService natureService;

    public NatureController(NatureService natureService) {
        this.natureService = natureService;
    }

    @PostMapping("/nature")
    public ResponseEntity<Nature> createNature(@RequestBody Nature nature){
        Nature nature1= natureService.create(nature);
        return ResponseEntity.ok(nature1);
    }
    @GetMapping("/nature")
    public ResponseEntity<List<Nature>> findAllNature(){
        List<Nature>  laliste= natureService.findAll();
        return ResponseEntity.ok(laliste);
    }
    @PutMapping("/nature/{id}")
    public ResponseEntity<Optional<Nature>> updateNature(@PathVariable("id") Long id, @RequestBody Nature natureUpdated) {
     Optional<Nature> laNature=natureService.update(id,natureUpdated);
     return ResponseEntity.ok(laNature);
    }

    @DeleteMapping("/nature/{id}")
    public ResponseEntity<Void> deleteNature(@PathVariable("id") Long id){
        Optional<Nature> laNature= natureService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("nature/fiche/{id}")
    public ResponseEntity<List<NatureSaisie>> findNatureByFicheSaisieId(@PathVariable("id") Long id){
        List<NatureSaisie> laFiche= natureService.findNatureByFicheSaisieId(id);
        return ResponseEntity.ok(laFiche);
    }

    @GetMapping("nature/total/{id}")
    public ResponseEntity<Naturedto> findByFicheSaisieId(@PathVariable("id") Long id){
        Naturedto laNature=natureService.findByFicheSaisieId(id);
        return  ResponseEntity.ok(laNature);
    }
}
