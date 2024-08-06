package com.mefp.appdeclaration.web;

import com.mefp.appdeclaration.entities.Activite;
import com.mefp.appdeclaration.entities.Procede;
import com.mefp.appdeclaration.service.ActiviteService;
import com.mefp.appdeclaration.service.ProcedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/dgd")

public class ActiviteController {
    @Autowired
   private  final ActiviteService activiteService;

    public ActiviteController(ActiviteService activiteService) {
        this.activiteService = activiteService;
    }

    @PostMapping("/activite")
    public ResponseEntity<Activite> createActivite(@RequestBody Activite activite){
        Activite activite1= activiteService.create(activite);
        return ResponseEntity.ok(activite1);
    }
    @GetMapping("/activite")
    public ResponseEntity<List<Activite>> findAllActivite(){
        List<Activite>  laliste= activiteService.findAll();
        return ResponseEntity.ok(laliste);
    }
    @PutMapping("/activite/{id}")
    public ResponseEntity<Optional<Activite>> updateActivite(@PathVariable("id") Long id, @RequestBody Activite activeUpdated) {
        Optional<Activite> lactivite=activiteService.update(id,activeUpdated);
        return ResponseEntity.ok(lactivite);
    }

    @DeleteMapping("/activite/{id}")
    public ResponseEntity<Void> deleteActivite(@PathVariable("id") Long id){
        Optional<Activite> lactivite= activiteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
