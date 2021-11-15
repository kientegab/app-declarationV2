package com.mfptps.appdgessddi.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.*;
import java.util.*;

import com.mfptps.appdgessddi.entities.Profile;
import com.mfptps.appdgessddi.service.ProfileService;
import com.mfptps.appdgessddi.utils.*;
import com.mfptps.appdgessddi.web.exceptions.*;

import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping("/security-service")
public class ProfileController {

    private final Logger log = LoggerFactory.getLogger(ProfileController.class);

    private static final String ENTITY_NAME = "profile";

    @Value("${application.name}")
    private String applicationName;

    private final ProfileService roleService;

    public ProfileController(ProfileService roleService) {
        this.roleService = roleService;
    }

    /**
     * {@code POST  /roles} : Create a new role.
     *
     * @param role the role to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new role, or with status {@code 400 (Bad Request)} if the role has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PreAuthorize("hasAnyAuthority(\"ROLE_ADMIN\", \"ROLE_MANAGER\", \"ROLE_CREATE_ROLE\")")
    @PostMapping("/roles")
    public ResponseEntity<Profile> createProfile(@RequestBody Profile role) throws URISyntaxException {
        log.debug("REST request to save Profile : {}", role);
        if (role.getId() != null) {
            throw new BadRequestAlertException("A new role cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Profile result = roleService.save(role);
        return ResponseEntity.created(new URI("/security-service/roles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /roles} : Updates an existing role.
     *
     * @param role the role to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated role,
     * or with status {@code 400 (Bad Request)} if the role is not valid,
     * or with status {@code 500 (Internal Server Error)} if the role couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/roles")
    @PreAuthorize("hasAnyAuthority(\"ROLE_ADMIN\", \"ROLE_MANAGER\", \"ROLE_UPDATE_ROLE\")")
    public ResponseEntity<Profile> updateProfile(@RequestBody Profile role) throws URISyntaxException {
        log.debug("REST request to update Profile : {}", role);
        if (role.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Profile result = roleService.save(role);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, role.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /roles} : get all the roles.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of roles in body.
     */
    @GetMapping("/roles")
    @PreAuthorize("hasAnyAuthority(\"ROLE_ADMIN\", \"ROLE_MANAGER\", \"ROLE_GETALL_ROLE\")")
    public ResponseEntity<List<Profile>> getAllProfiles(Pageable pageable) {
        log.debug("REST request to get a page of Profiles");
        Page<Profile> page = roleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /roles/:name} : get the "name" role.
     *
     * @param name the name of the role to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the role, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/roles/{name}")
    public ResponseEntity<Profile> getProfile(@PathVariable String name) {
        log.debug("REST request to get Profile : {}", name);
        Optional<Profile> role = roleService.getProfilerWithActionsByName(name);
        return ResponseUtil.wrapOrNotFound(role);
    }

    /**
     * {@code DELETE  /roles/:id} : delete the "id" role.
     *
     * @param id the id of the role to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/roles/{id}")
    @PreAuthorize("hasAnyAuthority(\"ROLE_ADMIN\", \"ROLE_MANAGER\", \"ROLE_DELETE_ROLE\")")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        log.debug("REST request to delete Profile : {}", id);
        if (id < 3) {
             throw new RuntimeException("Ce profile ne peut être supprimé.");
        }
        roleService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
