package bf.mefp.appDeclaration.appdgddeclaration.entity.dto;

import lombok.Data;

@Data
public class ListVoyageur {
    private String nom;
    private String prenom;
    private String typeIdentite;
    private  String refIdentite;
    private String profession;
    private String telephone;
    private String villeProvenance;
    private String villeDestination;
}
