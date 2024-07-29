package bf.mefp.appDeclaration.appdgddeclaration.controller;

import bf.mefp.appDeclaration.appdgddeclaration.entity.Pays;
import bf.mefp.appDeclaration.appdgddeclaration.entity.Region;
import bf.mefp.appDeclaration.appdgddeclaration.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("api/dgd")
public class RegionController {
    @Autowired

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }
    @PostMapping("/region")
    public ResponseEntity<Region> createRegion(@RequestBody Region region){
        Region laRegion= regionService.createRegion(region);
         return  ResponseEntity.ok(laRegion);
    }

    @GetMapping("/region")
    public ResponseEntity<List<Region>> findAllRegion(){
        List<Region> laListe= regionService.findAllRegion();
        return ResponseEntity.ok(laListe);
    }

    @GetMapping("/region/count")
    public ResponseEntity<Long> getVoyageurCount() {
        Long count = regionService.getRegionCount();
        return ResponseEntity.ok(count);
    }
    @PutMapping("/region/{id}")
    public ResponseEntity<Optional<Region>> updateRegion(@PathVariable("id") Long id, @RequestBody Region updatedRegion) {
        Optional<Region> region = regionService.updateRegion(id, updatedRegion);
        return ResponseEntity.ok(region);
    }

    @DeleteMapping("/region/{id}")
    public ResponseEntity<Void> deleteRegion(@PathVariable("id") Long id) {
        regionService.deleteRegion(id);
        return ResponseEntity.noContent().build();
    }
}
