package bf.mefp.appDeclaration.appdgddeclaration.service;

import bf.mefp.appDeclaration.appdgddeclaration.entity.Declaration;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service

public interface DocService {


@Autowired
    Declaration upload(Declaration declaration, MultipartFile[] documentFile) throws Exception;
}
