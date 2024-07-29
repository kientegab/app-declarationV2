package bf.mefp.appDeclaration.appdgddeclaration.entity;

import jakarta.persistence.*;
import lombok.Data;

//@author  <brikientega@gmail.com>
@Data
@Entity
@Table(name = "ville", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"libelle"}, name = "ux_libelle_in_ville")})
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_ville")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name="ville_id",nullable = false)
    @Column(nullable = false, unique = true, length = 10)
    private Long id;

    @Column(nullable = false, length = 90)
    private String code;

    private String libelle;

    @ManyToOne(targetEntity = Pays.class)
    @JoinColumn(name="pays_id",nullable = false)
    private Pays pays;

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Ville() {
    }



}
