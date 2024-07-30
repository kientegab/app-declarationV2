package com.mefp.appdeclaration.repositories;
import com.mefp.appdeclaration.entities.Devise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviseRepository extends JpaRepository<Devise, Long> {
}
