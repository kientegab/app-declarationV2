package com.mefp.appdeclaration.web;

import com.mefp.appdeclaration.entities.FicheSaisie;
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
public class FicheSaisieController {
    @Autowired
    private final FicheSaisieService ficheSaisieService;

    public FicheSaisieController(FicheSaisieService ficheSaisieService) {
        this.ficheSaisieService = ficheSaisieService;
    }

    @PostMapping("/fiches")
    public ResponseEntity<FicheSaisieDTO> create(FicheSaisieDTO ficheSaisieDTO){
        FicheSaisieDTO ficheSaisieDTO1=ficheSaisieService.create(ficheSaisieDTO);
         return ResponseEntity.ok(ficheSaisieDTO1);
    }

    @GetMapping("/fiches")
    public ResponseEntity<List<FicheSaisie>> findAll(){
        List<FicheSaisie> laliste= ficheSaisieService.findAll();
        return  ResponseEntity.ok(laliste);
    }

    @PutMapping("/fiches/{id}")
    public ResponseEntity<Optional> update(@RequestBody FicheSaisieDTO ficheSaisieDTO,@PathVariable("id") Long id ){
       Optional<FicheSaisieDTO>  ficheSaisie= ficheSaisieService.update(ficheSaisieDTO, id);
       return  ResponseEntity.ok(ficheSaisie);

    }
}
