package bf.mefp.appDeclaration.appdgddeclaration.service;

import bf.mefp.appDeclaration.appdgddeclaration.entity.Ville;
import bf.mefp.appDeclaration.appdgddeclaration.repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VilleService {
    @Autowired
    private final VilleRepository villeRepository;

    public VilleService(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }

    public Ville createVille(Ville ville){
        Ville laVille=villeRepository.save(ville);
        return laVille;
    }

    public Optional<Ville> findById(Long id){
       return  villeRepository.findById(id);

    }

    public List<Ville> findByPaysId(Long paysId){
        return villeRepository.findByPaysId(paysId);
    }
    public List<Ville> findAllVille(){
        return villeRepository.findAll();
    }

    public void deleteVille(Long id){
        Optional<Ville> laVille= villeRepository.findById(id);
        if(laVille.isPresent()){
             villeRepository.deleteById(id);
        } else {
            throw new RuntimeException("ville not found");
        }
    }

    public Optional<Ville> updateVille(Long id, Ville ville){
        Optional<Ville> optionalVille= villeRepository.findById(id);
        if(optionalVille.isPresent()){
            Ville laVille= optionalVille.get();
            laVille.setLibelle(ville.getLibelle());
            laVille.setPays(ville.getPays());
            return Optional.of(villeRepository.save((laVille)));
        } else {
            return Optional.empty();
        }
    }
}

