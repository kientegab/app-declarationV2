/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service.impl;

import com.mfptps.appdgessddi.repositories.ActionRepository;
import com.mfptps.appdgessddi.repositories.ExerciceRepository;
import com.mfptps.appdgessddi.repositories.MinistereStructureRepository;
import com.mfptps.appdgessddi.repositories.ObjectifRepository;
import com.mfptps.appdgessddi.repositories.ProgrammationRepository;
import com.mfptps.appdgessddi.repositories.ProgrammeRepository;
import com.mfptps.appdgessddi.repositories.QueryManagerRepository;
import com.mfptps.appdgessddi.repositories.StructureRepository;
import com.mfptps.appdgessddi.repositories.TacheRepository;
import com.mfptps.appdgessddi.service.CommentaireService;
import com.mfptps.appdgessddi.service.EvaluationService;
import com.mfptps.appdgessddi.service.StatisticParameterService;
import com.mfptps.appdgessddi.service.dto.statisticresponses.CountStructureGroupByType;
import com.mfptps.appdgessddi.service.mapper.ProgrammationMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Slf4j
@Service
public class StatisticParameterServiceImpl implements StatisticParameterService {

    private final MinistereStructureRepository ministereStructureRepository;

    private final ProgrammationRepository programmationRepository;
    private final TacheRepository tacheRepository;
    private final ExerciceRepository exerciceRepository;
    private final ObjectifRepository objectifRepository;
    private final StructureRepository structureRepository;
    private final EvaluationService evaluationService;
    private final CommentaireService commentaireService;
    private final ActionRepository actionRepository;
    private final ProgrammeRepository programmeRepository;

    private final ProgrammationMapper programmationMapper;

    private final QueryManagerRepository query;

    public StatisticParameterServiceImpl(ProgrammationRepository programmationRepository,
            ExerciceRepository exerciceRepository,
            TacheRepository tacheRepository,
            ObjectifRepository objectifRepository,
            MinistereStructureRepository ministereStructureRepository,
            StructureRepository structureRepository,
            EvaluationService evaluationService,
            CommentaireService commentaireService,
            ActionRepository actionRepository,
            ProgrammeRepository programmeRepository,
            QueryManagerRepository query,
            ProgrammationMapper programmationMapper) {

        this.programmationRepository = programmationRepository;
        this.tacheRepository = tacheRepository;
        this.exerciceRepository = exerciceRepository;
        this.objectifRepository = objectifRepository;
        this.ministereStructureRepository = ministereStructureRepository;
        this.structureRepository = structureRepository;
        this.evaluationService = evaluationService;
        this.commentaireService = commentaireService;
        this.programmationMapper = programmationMapper;
        this.actionRepository = actionRepository;
        this.programmeRepository = programmeRepository;
        this.query = query;
    }

    @Override
    public long nbStructuresByMinistere(long ministereId) {
        return ministereStructureRepository.countStructureByMinistere(ministereId);
    }

    @Override
    public List<CountStructureGroupByType> nbStructuresByGroupType(long ministereId) {
        return ministereStructureRepository.countStructureByMinistereAndByGroupType(ministereId);
    }

}
