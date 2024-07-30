package com.mefp.appdeclaration.entities;

import javax.persistence.*;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "structure")
@SQLDelete(sql = "UPDATE structure SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@FilterDef(
        name = "deletedFilter",
        parameters = @ParamDef(name = "isDeleted", type = "boolean")
)
@Filter(
        name = "deletedFilter",
        condition = "deleted = :isDeleted"
)
public class Structure extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String libelle;

    @Column(nullable = false)
    private String sigle;

//    @Column(nullable = false)
//    private Integer niveau;
//
//    @Column(nullable = true, length = 1)
//    @Convert(converter = TypeStructureConverter.class)
//    private TypeStructure type;

    /**
     * STRUCTURE CAN BE 'ACTIVATED/ACTIVEE' OR 'DEACTIVATED/DESACTIVEE'
     */
//    @Column(nullable = false)
//    @Type(type = "yes_no")
//    private boolean active = true;//if Structure always exists or no (20112021); because Structure having type MISSION can disappear

//    private String telephone;
//
//    @Column(nullable = false)
//    private String emailResp;
//
//    @Column(nullable = false)
//    private String emailStruct;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    private Structure parent;
//    private String description;
    @ManyToOne(targetEntity = Region.class)
    @JoinColumn(name="region_id",nullable = false)
    private Region region;


    public Structure() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }



    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }



    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }








    @Override
    public String toString() {
        return "Structure{"
                + "id=" + id
                + ", libelle='" + libelle + '\''
                + ", sigle='" + sigle + '\''
                + ", Region=" + region
                + '}';
    }
}
