package com.mefp.appdeclaration.repositories;

import com.mefp.appdeclaration.entities.DeviseMontantdto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviseMontantRepository extends JpaRepository<DeviseMontantdto, Long> {
}
