package bf.mefp.appDeclaration.appdgddeclaration.repository;

import bf.mefp.appDeclaration.appdgddeclaration.entity.Declaration;
import bf.mefp.appDeclaration.appdgddeclaration.entity.Voyageur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VoyageurRepository extends JpaRepository<Voyageur, Long> {
    Optional<Voyageur> findByRefDocument(String refDocument);
//    @Query("SELECT a FROM Voyageur a order by a.id")
    List<Voyageur> findAll();
}
