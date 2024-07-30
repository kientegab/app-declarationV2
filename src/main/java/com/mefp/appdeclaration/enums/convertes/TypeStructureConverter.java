/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mefp.appdeclaration.enums.convertes;

import javax.persistence.AttributeConverter;

import com.mefp.appdeclaration.enums.TypeStructure;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public class TypeStructureConverter implements AttributeConverter<TypeStructure, Character> {
    
    @Override
    public Character convertToDatabaseColumn(TypeStructure attribute) {
        return attribute.getValueStatus();
    }
    
    @Override
    public TypeStructure convertToEntityAttribute(Character dbData) {
        return TypeStructure.getByValue(dbData);
    }
    
}
