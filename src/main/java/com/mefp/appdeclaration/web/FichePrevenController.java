package com.mefp.appdeclaration.web;

import com.mefp.appdeclaration.entities.FichePrevention;
import com.mefp.appdeclaration.entities.FicheSaisie;
import com.mefp.appdeclaration.service.FichePreventionService;
import com.mefp.appdeclaration.service.FicheSaisieService;
import com.mefp.appdeclaration.service.dto.FicheSaisieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/dgd")
public class FichePrevenController {

    @Autowired
    private final FichePreventionService fichePreventionService;

    public FichePrevenController(FichePreventionService fichePreventionService) {
        this.fichePreventionService = fichePreventionService;
    }

    @PostMapping("/fichep")
    public ResponseEntity<FichePrevention> createPrevention(FichePrevention fichePrevention){
        FichePrevention fichePrevention1=fichePreventionService.createPrevention(fichePrevention);
        return ResponseEntity.ok(fichePrevention1);
    }

    @GetMapping("/fichep")
    public ResponseEntity<List<FichePrevention>> findAllPrevention(){
        List<FichePrevention> laliste= fichePreventionService.findAllPrevention();
        return  ResponseEntity.ok(laliste);
    }

    @PutMapping("/fichep/{id}")
    public ResponseEntity<Optional> updatePrevention(@RequestBody FichePrevention fichePrevention, @PathVariable("id") Long id ){
        Optional<FichePrevention>  fichePrevention1= fichePreventionService.updatePrevention(fichePrevention, id);
        return  ResponseEntity.ok(fichePrevention1);

    }
}
