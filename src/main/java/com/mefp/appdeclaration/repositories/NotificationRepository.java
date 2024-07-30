/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mefp.appdeclaration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mefp.appdeclaration.entities.Notification;

/**
 *
 * @author Fatogoma HEBIE <fat19ebie@gmail.com>
 */
public interface NotificationRepository extends JpaRepository<Notification, Long>{
    
    boolean existsByObjet(String objet);
}
