package bf.mefp.appDeclaration.appdgddeclaration.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Declarationdto {
    private InputStream logo;
    private String region;
    private String structure;
    private List<ListDeclaration> listDeclarations;


}
