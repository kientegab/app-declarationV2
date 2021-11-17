/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.enums;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public enum TypeObjectif {
    STRATEGIQUE("STRATEGIQUE"),
    OPERATIONNEL("OPERATIONNEL");

    String label;

    TypeObjectif(String _label) {
        this.label = _label;
    }
}
