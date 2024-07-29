package bf.mefp.appDeclaration.appdgddeclaration.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "declaration")
public class Declaration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_decl")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, length = 10)
    @JoinColumn(name="declaration_id",nullable = false)
    private Long id;

    private Date dateDeclaration;

    private String motifVoyage;
    @ManyToOne(targetEntity = Devise.class)
    @JoinColumn(name="devise_id")

    private Devise devise;

    private Long montant;

    private Long montantCFA;

    private Boolean estDeclare;

    private String nationalite;

    @Column(nullable = true)
    private String justification;

    private String commentaire;

    @ManyToOne(targetEntity = Voyageur.class)
    @JoinColumn(name="voyageur_id")
    private Voyageur voyageur;
    @OneToMany(mappedBy = "declaration", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"declaration"}, allowSetters = true)
    private Set<Document> documents = new HashSet<>() ;
    public Declaration(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getMontantCFA() {
        return montantCFA;
    }

    public void setMontantCFA(Long montantCFA) {
        this.montantCFA = montantCFA;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateDeclaration() {
        return dateDeclaration;
    }

    public void setDateDeclaration(Date dateDeclaration) {
        this.dateDeclaration = dateDeclaration;
    }

    public String getMotifVoyage() {
        return motifVoyage;
    }

    public void setMotifVoyage(String motifVoyage) {
        this.motifVoyage = motifVoyage;
    }

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }

    public Long getMontant() {
        return montant;
    }

    public void setMontant(Long montant) {
        this.montant = montant;
    }


    public Boolean getEstDeclare() {
        return estDeclare;
    }

    public void setEstDeclare(Boolean estDeclare) {
        this.estDeclare = estDeclare;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }


    public Voyageur getVoyageur() {
        return voyageur;
    }

    public void setVoyageur(Voyageur voyageur) {
        this.voyageur = voyageur;
    }

    public Declaration() {
    }
}
