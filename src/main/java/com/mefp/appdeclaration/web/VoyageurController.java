package com.mefp.appdeclaration.web;


//import jakarta.servlet.http.HttpServletResponse;
import com.mefp.appdeclaration.entities.Voyageur;
import com.mefp.appdeclaration.service.VoyageurService;
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
public class VoyageurController {
    @Autowired
    private  final VoyageurService  voyageurService;

    public VoyageurController(VoyageurService voyageurService) {
        this.voyageurService = voyageurService;
    }

    @PostMapping("/voyageur")
    public ResponseEntity<Voyageur> createVoyageur(@RequestBody Voyageur voyageur){
        Voyageur  voyageur1= voyageurService.createVoayageur((voyageur));
        return  ResponseEntity.ok(voyageur1);
    }

    @GetMapping("/voyageur/{id}")
    public ResponseEntity <Voyageur> findById(@PathVariable("id") Long id) {
        Optional<Voyageur> optionalVoyageur = voyageurService.findById(id);
        return optionalVoyageur.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/voyageur/count")
    public ResponseEntity<Long> getVoyageurCount() {
        Long count = voyageurService.getVoyageurCount();
        return ResponseEntity.ok(count);
    }
    @GetMapping("/voyageur/refDocument/{refDocument}")
    public ResponseEntity <Voyageur> findByRefDocument(@PathVariable("refDocument") String refDocument) {
        Optional<Voyageur> optionalVoyageur = voyageurService.findByRefDocument(refDocument);
        return optionalVoyageur.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
  @GetMapping("/voyageur")
    public ResponseEntity<List<Voyageur>> findAllList(){
        List<Voyageur> listeVoyageur= voyageurService.findAllVoyageur();
        return ResponseEntity.ok(listeVoyageur);
  }

    @PutMapping("/voyageur/{id}")
    public ResponseEntity<Optional<Voyageur>> updateVoyageur(@PathVariable("id") Long id, @RequestBody Voyageur updatedVoyageur) {
        Optional<Voyageur> voyageur = voyageurService.updateVoyageur(id, updatedVoyageur);
        return ResponseEntity.ok(voyageur);
    }

    @DeleteMapping("/voyageur/{id}")
    public ResponseEntity<Void> deleteVoyageur(@PathVariable("id") Long id) {
        voyageurService.deleteVoyageur(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping(value = "/voyageur/export")
    public void exportVoyageur(HttpServletResponse  reponse)
            throws IOException, JRException {
        OutputStream outputStream = reponse.getOutputStream();
        reponse.setContentType("application/pdf");
        reponse.setHeader("Content-Disposition", String.format("attachment; filename=\"LIST_VOYAGEUR"  + ".pdf" + "\""));
       voyageurService.exportVoyageur(outputStream);
    }
}
