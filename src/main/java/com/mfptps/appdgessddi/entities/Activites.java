package com.mfptps.appdgessddi.entities;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;



@Entity
@Table(name = "activites")
@SQLDelete(sql="UPDATE activites SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@FilterDef(
        name = "deletedFilter",
        		  parameters = @ParamDef(name = "isDeleted", type = "boolean")
)
@Filter(
        name = "deletedFilter",
        condition = "deleted = :isDeleted"
)
public class Activites extends CommonEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String code;
	private String description;
    private String libelle;
  
    private String status;
    
    @ManyToOne
    private TypeActivites typeActivites;

	public Activites() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public TypeActivites getTypeActivites() {
		return typeActivites;
	}

	public void setTypeActivites(TypeActivites typeActivites) {
		this.typeActivites = typeActivites;
	}

	@Override
	public String toString() {
		return "Activites [code=" + code + ", libelle=" + libelle + ", status=" + status + ", typeActivites="
				+ typeActivites + "]";
	}
    
    

}