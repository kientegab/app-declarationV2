/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.repositories;

import com.mfptps.appdgessddi.entities.TacheEvaluer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface TacheEvaluerRepository extends JpaRepository<TacheEvaluer, Long> {

    @Query("SELECT te FROM TacheEvaluer te "
            + "WHERE te.tacheId = :id "
            + "AND te.cumuleeActive = true")
    Optional<TacheEvaluer> findByIdAndActive(Long id);

    Optional<TacheEvaluer> findByCumuleeActiveIsTrue();
}
