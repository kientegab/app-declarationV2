
package com.mefp.appdeclaration.entities;
import lombok.Data;
import javax.persistence.*;

/**
 *
 * @author Brigitte <brikientega@gmail.com>
 */
@Entity
@Data
@Table(name = "region")
// @SQLDelete(sql = "UPDATE region SET deleted = true WHERE id=?")
// @Where(clause = "deleted = false")
public class Region extends CommonEntity {

    private static final long serialVersionUID = 5686144131119283929L;

	@Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_region")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name="region_id",nullable = false)
    @Column(unique = true, nullable = false, length = 10)
    private Long idRegion;

    @Column(unique = true, nullable = false, length = 90)
    private String code;

    private String libelle;

    //Relationsships


    //========== CONSTRUCTOR && SETTERS


    public Region() {
    }


    public Long getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Long idRegion) {
        this.idRegion = idRegion;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

}
