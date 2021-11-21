/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.web;

import com.mfptps.appdgessddi.service.DocumentService;
import com.mfptps.appdgessddi.service.dto.DocumentDTO;
import com.mfptps.appdgessddi.utils.ResponseMessage;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Slf4j
@RestController
@RequestMapping(path = "/api/documents")
public class DocumentController {

    private static final String ENTITY_NAME = "document";

    @Value("${application.name}")
    private String applicationName;

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    /**
     * Join a file to a Tache
     *
     * @param documentDTO
     * @param documentFile : file joined
     * @return
     */
    @PostMapping
    public ResponseEntity<ResponseMessage> createDocument(@Valid @RequestPart DocumentDTO documentDTO,
            @RequestPart MultipartFile documentFile) {
        log.debug("Jointure d'un Document à une Tache : {}", documentDTO);

        ResponseMessage message = documentService.create(documentDTO, documentFile);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
