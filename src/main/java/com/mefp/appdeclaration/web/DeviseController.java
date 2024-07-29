package bf.mefp.appDeclaration.appdgddeclaration.controller;

import bf.mefp.appDeclaration.appdgddeclaration.entity.Devise;
import bf.mefp.appDeclaration.appdgddeclaration.entity.Region;
import bf.mefp.appDeclaration.appdgddeclaration.service.DeviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("api/dgd")

public class DeviseController {

    @Autowired
    private final DeviseService deviseService;

    public DeviseController(DeviseService deviseService) {
        this.deviseService = deviseService;
    }

    @PostMapping("/devise")
    public ResponseEntity<Devise> createDevise(@RequestBody Devise devise){
        Devise uneDevise= deviseService.createDevise(devise);
        return ResponseEntity.ok(uneDevise);
    }

    @GetMapping("/devise")

    public  ResponseEntity<List<Devise>> findAllDevise(){
        List<Devise> listeDevise= deviseService.findAllDevise();
        return  ResponseEntity.ok(listeDevise);
    }

    @PutMapping("/devise/{id}")
    public ResponseEntity<Optional<Devise>> updateDevise(@PathVariable("id") Long id, @RequestBody Devise updatedDevise) {
        Optional<Devise> devise = deviseService.updateDevise(id, updatedDevise);
        return ResponseEntity.ok(devise);
    }

    @DeleteMapping("/devise/{id}")
    public ResponseEntity<Void> deleteDevise(@PathVariable("id") Long id) {
       deviseService.deleteDevise(id);
        return ResponseEntity.noContent().build();
    }
}
