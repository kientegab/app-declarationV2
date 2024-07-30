package com.mefp.appdeclaration.repositories;

import com.mefp.appdeclaration.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Long>, JpaSpecificationExecutor <Document> {
        Optional<Document> findByReference(String reference);
        }
