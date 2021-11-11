package com.mfptps.appdgessddi.web;

import java.util.List;

import com.mfptps.appdgessddi.entities.Ministere;
import com.mfptps.appdgessddi.service.MinistereService;
import com.mfptps.appdgessddi.service.dto.MinistereDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/ministeres")
public class MinistereController {
    
    private final MinistereService ministereService;

    public MinistereController(MinistereService ministereService) {
        this.ministereService = ministereService;
    }

    @PostMapping
    public ResponseEntity<Ministere> create(@RequestBody MinistereDTO ministere) {
        Ministere min = ministereService.create(ministere);
        return ResponseEntity.ok().body(min);
    }

    @GetMapping(path = "/{code}")
    public ResponseEntity<Ministere> getMinistere(@PathVariable(name = "code") String code) {
return ResponseEntity.ok().body(ministereService.get(code));
    }

    @GetMapping
    public ResponseEntity<List<Ministere>> findAllMinisteres(Pageable pageable) {
        Page<Ministere> minsiteres = ministereService.findAll(pageable);
        return ResponseEntity.ok().body(minsiteres.getContent());
    }
    
}
