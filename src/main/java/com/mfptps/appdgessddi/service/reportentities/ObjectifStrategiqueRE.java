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
public class ObjectifStrategiqueRE implements Serializable {

    /**
     * OBJECTIF STRATEGIQUE LEVEL
     */
    private String codeObjectifStra;

    private String libelleObjectifStra;

    private String indicateurObjectifStra;

    private String structureObjectifStra;

    private List<ActionRE> actionREs;
}
