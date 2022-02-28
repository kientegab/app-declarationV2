/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mfptps.appdgessddi.service.dto.statisticresponses;

/**
 *
 * @author aboubacary
 */
public class EvolutionParam {
    
    private Long ministreId;
    
    private Long structureId;
    
    private int couverture; // nombre d'exercice à couvrir par les recherches

    public Long getMinistreId() {
        return ministreId;
    }

    public void setMinistreId(Long ministreId) {
        this.ministreId = ministreId;
    }

    public Long getStructureId() {
        return structureId;
    }

    public void setStructureId(Long structureId) {
        this.structureId = structureId;
    }

    public int getCouverture() {
        return couverture;
    }

    public void setCouverture(int couverture) {
        this.couverture = couverture;
    }
    
    
}
