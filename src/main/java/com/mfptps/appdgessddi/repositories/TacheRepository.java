/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.repositories;

import com.mfptps.appdgessddi.entities.Tache;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface TacheRepository extends JpaRepository<Tache, Long> {

}
