package bf.mefp.appDeclaration.appdgddeclaration.repository;

import bf.mefp.appDeclaration.appdgddeclaration.entity.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VilleRepository extends JpaRepository<Ville,Long> {
    @Query("SELECT a FROM Ville a WHERE a.pays.id = :paysId")
    List<Ville> findByPaysId(Long paysId);
}
