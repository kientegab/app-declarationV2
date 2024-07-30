/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mefp.appdeclaration.service.dto.statisticresponses;

import com.mefp.appdeclaration.enums.TypeStructure;

/**
 * POJO class just used to return a customized response
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public class CountStructureGroupByType {

    private TypeStructure type;

    private long nombre;

    //================================
    public CountStructureGroupByType(TypeStructure type, long nombre) {
        this.type = type;
        this.nombre = nombre;
    }

    public TypeStructure getType() {
        return type;
    }

    public void setType(TypeStructure type) {
        this.type = type;
    }

    public long getNombre() {
        return nombre;
    }

    public void setNombre(long nombre) {
        this.nombre = nombre;
    }

}
