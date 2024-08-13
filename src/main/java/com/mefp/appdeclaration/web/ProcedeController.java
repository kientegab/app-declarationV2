package com.mefp.appdeclaration.web;

import com.mefp.appdeclaration.entities.Nature;
import com.mefp.appdeclaration.entities.NatureSaisie;
import com.mefp.appdeclaration.entities.Procede;
import com.mefp.appdeclaration.entities.ProcedeSaisie;
import com.mefp.appdeclaration.service.NatureService;
import com.mefp.appdeclaration.service.ProcedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RequestMapping("/api/dgd")
@RestController
public class ProcedeController {

    @Autowired
    private  final ProcedeService procedeService;

    public ProcedeController(ProcedeService procedeService) {
        this.procedeService = procedeService;
    }

    @PostMapping("/procede")
    public ResponseEntity<Procede> createProcede(@RequestBody Procede procede){
        Procede procede1= procedeService.create(procede);
        return ResponseEntity.ok(procede1);
    }
    @GetMapping("/procede")
    public ResponseEntity<List<Procede>> findAllProcede(){
        List<Procede>  laliste= procedeService.findAll();
        return ResponseEntity.ok(laliste);
    }
    @PutMapping("/procede/{id}")
    public ResponseEntity<Optional<Procede>> updateProcede(@PathVariable("id") Long id, @RequestBody Procede procedeUpdated) {
        Optional<Procede> leProcede=procedeService.update(id,procedeUpdated);
        return ResponseEntity.ok(leProcede);
    }

    @DeleteMapping("/procede/{id}")
    public ResponseEntity<Void> deleteProcede(@PathVariable("id") Long id){
        Optional<Procede> leProcede= procedeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("procede/fiche/{id}")
    public ResponseEntity<List<ProcedeSaisie>> findProcedeByFicheSaisieId(@PathVariable("id") Long id){
        List<ProcedeSaisie> leProcede= procedeService.findProcedeByFicheSaisieId(id);
        return ResponseEntity.ok(leProcede);
    }
}

