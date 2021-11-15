/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.repositories;

import java.util.stream.Stream;

import com.mfptps.appdgessddi.entities.Permission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author bieve
 */
public interface PermissionRepository extends JpaRepository<Permission, Long>{
    
    @Query("SELECT u FROM Permission u")
    Stream<Permission> streamAll();
    
}
