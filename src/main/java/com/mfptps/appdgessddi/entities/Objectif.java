/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.entities;

import com.mfptps.appdgessddi.enums.TypeObjectif;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Entity
@Table(name = "objectif")
@SQLDelete(sql = "UPDATE objectif SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@FilterDef(
        name = "deletedFilter",
        parameters = @ParamDef(name = "isDeleted", type = "boolean")
)
@Filter(
        name = "deletedFilter",
        condition = "deleted = :isDeleted"
)
public class Objectif extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 10)
    private String code;

    @Column(nullable = false)
    private String libelle;

    private String description;

    private TypeObjectif type;

//    @ManyToOne
//    @JoinColumn(name = "objectif_id")
//    private Objectif parent;//to manage SousObjectif notion
//
//    @ManyToOne
//    private Action action;//ObjectifOperationnel to Action
//
//    @OneToMany
//    private Set<Action> actions;//this relationship can be move to entity Action
}
