/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.web.vm;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Getter
@Setter
public class ValidProgammationVM {
//////////////////////////////////////////////// 
//    INACHEVE: L'IDEE EST D'AVOIR UN SEUL ENDPOINT POUR LISTER LES PROGRAMMATIONS
//      /all/valide/{ids} ===> /all/validity-state   
/////////////////////////////////////////////////

    /**
     * A FOURNIR OBLIGATOIREMENT
     */
    private Long structureId;

    //Si TRUE, retourne la liste des programmations non encore validees par RESP_STRUCT
    private boolean noValidatedBySTRUCT;

    //POUR LISTER: Si TRUE, retourne la liste des programmations validees par RESP_STRUCT
    //POUR VALIDER: si TRUE, validation globale a faire par RESP_STRUCT
    private boolean validatedBySTRUCT;

    //Si TRUE, retourne la liste des programmations validees par RESP_DGESS
    private boolean validatedByDGESS;

    //Si TRUE, retourne la liste des programmations validees par CASEM
    private boolean validatedByCASEM;
}
