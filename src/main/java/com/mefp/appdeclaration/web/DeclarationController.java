package com.mefp.appdeclaration.web;


//import jakarta.servlet.http.HttpServletResponse;
import com.mefp.appdeclaration.entities.Declaration;
import com.mefp.appdeclaration.entities.dto.PeriodeExport;
import com.mefp.appdeclaration.service.DeclarationService;
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
public class DeclarationController {

    @Autowired
    private  final DeclarationService  declarationService;

    public DeclarationController(DeclarationService declarationService) {
        this.declarationService = declarationService;

    }
    @PostMapping("/declaration")
    public ResponseEntity<Declaration> createDeclaration(@RequestBody Declaration declaration){
  //     Long montant= Long.valueOf("5000000");
//     declaration.setMontant(montant);
        declaration.setEstDeclare(true);
        Declaration declaration1= declarationService.createDeclaration(declaration);
        return  ResponseEntity.ok(declaration1);
    }

    @GetMapping("/declaration/count")
    public ResponseEntity<Long> getDeclarationCount() {
        Long count = declarationService.getDeclarationCount();
        return ResponseEntity.ok(count);
    }

   @GetMapping("/declaration/countbymois")
   public ResponseEntity<String> getDeclarationByMonth() {
       String count = declarationService.getDeclarationByMonth();
       return ResponseEntity.ok(count);
   }
    @GetMapping("/declaration/{id}")
    public ResponseEntity <Declaration> findById(@PathVariable("id") Long id) {
        Optional<Declaration> optionalDeclaration= declarationService.findById(id);
        return optionalDeclaration.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/declaration/voyageur/{id}")
    public ResponseEntity<List<Declaration>> findByVoyageurId(@PathVariable("id") Long id) {
        List<Declaration> listeVdeclaration= declarationService.findByVoyageurId(id);
        return ResponseEntity.ok(listeVdeclaration);
    }
    @GetMapping("/declaration")
    public ResponseEntity<List<Declaration>> findAllListD(){
        List<Declaration> listeDeclaration= declarationService.findAllDeclaration();
        return ResponseEntity.ok(listeDeclaration);
    }

    @PutMapping("/declaration/{id}")
    public ResponseEntity<Optional<Declaration>> updateDeclaration(@PathVariable("id") Long id, @RequestBody Declaration updatedDeclaration) {
        Optional<Declaration> declaration = declarationService.updateDeclaration(id, updatedDeclaration);
        return ResponseEntity.ok(declaration);
    }

    @PostMapping(value = "/declaration/export")
    public void exportDeclaration(HttpServletResponse  reponse, @RequestBody PeriodeExport  periodeExport)
            throws IOException, JRException {
        OutputStream  outputStream = reponse.getOutputStream();
        reponse.setContentType("application/pdf");
        reponse.setHeader("Content-Disposition", String.format("attachment; filename=\"RAPPORT_DECLARATION_" + periodeExport.getDateDebut() + ".pdf" + "\""));
        declarationService.exportDeclaration(periodeExport, outputStream);
    }

    @DeleteMapping("/declaration/{id}")
    public ResponseEntity<Void> deleteDeclaration(@PathVariable("id") Long id) {
        declarationService.deleteDeclaration(id);
        return ResponseEntity.noContent().build();
    }
}
