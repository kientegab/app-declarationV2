package com.mfptps.appdgessddi.entities;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "ministere_structure")
public class MinistereStructure extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String statut ;
    private Date dateDebut ;
    private Date dateFin ;
    /*

     */
    @ManyToOne
    private Structure structure;
    /*

     */
    @ManyToOne
    private  Ministere ministere ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public Ministere getMinistere() {
        return ministere;
    }

    public void setMinistere(Ministere ministere) {
        this.ministere = ministere;
    }
}
