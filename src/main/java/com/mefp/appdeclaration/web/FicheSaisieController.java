package com.mefp.appdeclaration.web;

import com.mefp.appdeclaration.config.utils;
import com.mefp.appdeclaration.entities.FicheSaisie;
import com.mefp.appdeclaration.entities.Structure;
import com.mefp.appdeclaration.entities.dto.EtatSaisie;
import com.mefp.appdeclaration.entities.dto.PeriodeExport;
import com.mefp.appdeclaration.service.AgentService;
import com.mefp.appdeclaration.service.FicheSaisieService;
import com.mefp.appdeclaration.service.dto.FicheSaisieCompDTO;
import com.mefp.appdeclaration.service.dto.FicheSaisieDTO;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
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
    @PostMapping("/fiches/{matricule}")
    public ResponseEntity<FicheSaisieDTO> create(@RequestBody FicheSaisieDTO ficheSaisieDTO,@PathVariable("matricule") String matricule){
        FicheSaisieDTO ficheSaisieDTO1=ficheSaisieService.create(ficheSaisieDTO, matricule);
         return ResponseEntity.ok(ficheSaisieDTO1);
    }

    @PostMapping("/fiches/comp")
    public ResponseEntity<FicheSaisieCompDTO> create(@RequestBody FicheSaisieCompDTO ficheSaisieCompDTO){
        FicheSaisieCompDTO ficheSaisieCompDTO1=ficheSaisieService.ajouter(ficheSaisieCompDTO);
        return ResponseEntity.ok(ficheSaisieCompDTO1);
    }

    @GetMapping("/fiches")
    public ResponseEntity<List<FicheSaisie>> findAll(){
        List<FicheSaisie> laliste= ficheSaisieService.findAll();
        return  ResponseEntity.ok(laliste);
    }
    @GetMapping("/fiches/struct/{id}")
    public ResponseEntity<List<EtatSaisie>> findPersonalise(@PathVariable("id") Long id ){
        List<EtatSaisie> laliste= ficheSaisieService.findPersonalise(id);
        return  ResponseEntity.ok(laliste);
    }

    @PutMapping("/fiches/{id}")
    public ResponseEntity<Optional<FicheSaisieDTO>> update(@RequestBody FicheSaisieDTO ficheSaisieDTO,@PathVariable("id") Long id ){
       Optional<FicheSaisieDTO>  ficheSaisie= ficheSaisieService.update(ficheSaisieDTO, id);
       return  ResponseEntity.ok(ficheSaisie);

    }

    @GetMapping("/fiches/{id}")
    public ResponseEntity<Optional<FicheSaisie>> findByFicheSaisieId(@PathVariable("id") Long id){
        Optional<FicheSaisie> laFiche= ficheSaisieService.findFicheSaisieById(id);
        return ResponseEntity.ok(laFiche);
    }

    @PostMapping(value = "/fiches/export")
    public void exportDeclaration(HttpServletResponse reponse)
            throws IOException, JRException {
        OutputStream outputStream = reponse.getOutputStream();
        reponse.setContentType("application/pdf");
        reponse.setHeader("Content-Disposition", String.format("attachment; filename=\"RAPPORT_SAISIES_"  + ".pdf" + "\""));
        ficheSaisieService.exportDeclaration(outputStream);
    }
}
