package com.mefp.appdeclaration.service;
import com.mefp.appdeclaration.entities.Declaration;
import com.mefp.appdeclaration.entities.dto.Declarationdto;
import com.mefp.appdeclaration.service.dto.DeclarationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service

public interface DocService {


@Autowired
DeclarationDTO upload(DeclarationDTO declaration, MultipartFile[] documentFile) throws Exception;
}
