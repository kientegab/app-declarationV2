package bf.mefp.appDeclaration.appdgddeclaration.controller;

import bf.mefp.appDeclaration.appdgddeclaration.entity.Pays;
import bf.mefp.appDeclaration.appdgddeclaration.entity.Ville;
import bf.mefp.appDeclaration.appdgddeclaration.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/dgd")
public class VilleController {
    @Autowired
    private final VilleService villeService;

    public VilleController(VilleService villeService) {
        this.villeService = villeService;
    }

    @PostMapping("/ville")
    public ResponseEntity<Ville> createPays(@RequestBody Ville ville){
        Ville ville1= villeService.createVille(ville);
        return  ResponseEntity.ok(ville1);
    }

    @GetMapping("/ville/{id}")
    public ResponseEntity <Ville> findById(@PathVariable("id") Long id) {
        Optional<Ville> optionalVille = villeService.findById(id);
        return optionalVille.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/ville")
    public ResponseEntity<List<Ville>> findAllList(){
        List<Ville> listeVille= villeService.findAllVille();
        return ResponseEntity.ok(listeVille);
    }

    @GetMapping("/ville/pays/{paysId}")
    public  ResponseEntity<List<Ville>> findVilleByPays( @PathVariable("paysId") Long paysId){
        List<Ville> laVille= villeService.findByPaysId(paysId);
        return  ResponseEntity.ok(laVille);
    }

    @PutMapping("/ville/{id}")
    public ResponseEntity<Optional<Ville>> updateVille(@PathVariable("id") Long id, @RequestBody Ville updatedVille) {
        Optional<Ville> ville = villeService.updateVille(id, updatedVille);
        return ResponseEntity.ok(ville);
    }

    @DeleteMapping("/ville/{id}")
    public ResponseEntity<Void> deleteVille(@PathVariable("id") Long id) {
        villeService.deleteVille(id);
        return ResponseEntity.noContent().build();
    }
}
