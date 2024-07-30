package com.mefp.appdeclaration.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mefp.appdeclaration.entities.Ministere;

import com.mefp.appdeclaration.entities.Structure;
import com.mefp.appdeclaration.repositories.MinistereRepository;
import com.mefp.appdeclaration.repositories.StructureRepository;
import com.mefp.appdeclaration.security.SecurityUtils;
import com.mefp.appdeclaration.service.MinistereService;
import com.mefp.appdeclaration.service.dto.MinistereDTO;
import com.mefp.appdeclaration.service.dto.statisticresponses.MinistereBundleData;
import com.mefp.appdeclaration.service.mapper.MinistereMapper;
import com.mefp.appdeclaration.utils.AppUtil;
import com.mefp.appdeclaration.web.exceptions.CustomException;

@Service
@Transactional
public class MinistereServiceImpl implements MinistereService {

    private final MinistereRepository ministereRepository;
    private final MinistereMapper ministereMapper;
    private final StructureRepository structureRepository;



    public MinistereServiceImpl(MinistereRepository ministereRepository, MinistereMapper ministereMapper,
            StructureRepository structureRepository) {
        this.ministereRepository = ministereRepository;
        this.ministereMapper = ministereMapper;
        this.structureRepository = structureRepository;

    }

    @Override
    public Ministere create(MinistereDTO ministereDTO) {
        Ministere ministere = ministereMapper.toEntity(ministereDTO);
        return ministereRepository.save(ministere);
    }

    @Override
    public Ministere update(Ministere ministere) {
        return ministereRepository.save(ministere);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Ministere> get(String code) {
        return ministereRepository.findByCode(code);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Ministere> findAll(Pageable pageable) {
        if (SecurityUtils.isCurrentUserInRole(AppUtil.ADMIN)) {
            return ministereRepository.findAll(pageable);
        } else {
            //Ne pas retourner le MinistereTest au cas où le client n'est pas ADMIN
            return ministereRepository.findAll(AppUtil.BASIC_MINISTERE_CODE, pageable);
        }
    }

    @Override
    public void delete(Long code) {
        ministereRepository.deleteById(code);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Ministere> get(Long id) {
        return ministereRepository.findById(id);
    }

    /**
     * Construit une entité MinistereBundleData à partir de l'ID de la structure
     * fournie en paramètre
     *
     * @param structureId
     * @return
     */
   // @Override
//    public Optional<MinistereBundleData> getBundledData(Long structureId) {
//
//        // Date du jour du serveur
//        Date date = new Date();
//
//        // Instance de l'entitée à retourner
//        MinistereBundleData data = new MinistereBundleData();
//
//        // Recherche de la structure
//        Structure structure = structureRepository.getById(structureId);
//
//        // Recherche du ministère et de la Structure
//        Ministere ministere = ministereRepository.findMinistereStructureCode(structureId).orElse(null);
//
//        // Aucun ministère trouvé
//        if (ministere == null) {
//            throw new CustomException("Aucune donnée trouvée");
//        }
//
//        // Renseigner les informations du ministère
//        data.setMinisterId(ministere.getId());
//        data.setMinistereCode(ministere.getSigle());
//        data.setMinistereLibelle(ministere.getLibelle());
//
//        // Renseigner les informations de la structure
//        data.setStructureId(structure.getId());
//        data.setStructureCode(structure.getSigle());
//        data.setStructureLibelle(structure.getLibelle());
//        try {
//
//            // Recherche de la période
//            // List<Periode> periodes = periodeRepository.findByPeriodiciteActif();
//
//            // Periode periode = AppUtil.checkExactPeriode(periodes, date);
//
//            // Renseigner les informations de la période et de la périodicité
//            // data.setPeriode(periode.getLibelle());
//            // data.setPeriodicite(periode.getPeriodicite().getLibelle());
//
//            // Renseigner la date du jour en la formattant en string
//           // data.setDateJour(AppUtil.convertToShortDate(date));
//        } catch (Exception e) {
//            throw new CustomException("Veuillez paramétrer la périodicité et les périodes svp.");
//        }
//
//        return Optional.of(data);
//    }

}
