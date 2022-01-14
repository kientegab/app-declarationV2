/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service.reportentities;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ObjectifOperationnelRE implements Serializable {

    /**
     * OBJECTIF OPERATIONNEL LEVEL
     */
    private String codeObjectifOp;

    private String libelleObjectifOp;

    private String structureObjectifOp;

    private List<ActiviteRE> activiteREs;

}
