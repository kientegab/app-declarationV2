/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service.impl;

import com.mfptps.appdgessddi.entities.Programmation;
import com.mfptps.appdgessddi.entities.Tache;
import com.mfptps.appdgessddi.entities.TacheEvaluer;
import com.mfptps.appdgessddi.repositories.ProgrammationRepository;
import com.mfptps.appdgessddi.repositories.TacheEvaluerRepository;
import com.mfptps.appdgessddi.repositories.TacheRepository;
import com.mfptps.appdgessddi.service.CustomException;
import com.mfptps.appdgessddi.service.TacheService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Slf4j
@Service
@Transactional
public class TacheServiceImpl implements TacheService {

    private final TacheRepository tacheRepository;
    private final TacheEvaluerRepository tacheEvaluerRepository;
    private final ProgrammationRepository programmationRepository;
    private boolean tacheAValeurCible, tacheSansValeurCible;

    public TacheServiceImpl(TacheRepository tacheRepository,
            TacheEvaluerRepository tacheEvaluerRepository,
            ProgrammationRepository programmationRepository) {
        this.tacheRepository = tacheRepository;
        this.tacheEvaluerRepository = tacheEvaluerRepository;
        this.programmationRepository = programmationRepository;
    }

    @Override
    public Tache update(Tache tache) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * acts as deletion and update. Do at frontEnd : Modify fields and delete
     * tasks (checkBox for example). CAREFULNESS : run exclusively before task
     * evaluation
     *
     * @param taches : list of taches (deleted or no, updated or no)
     * @return
     */
    @Override
    public List<Tache> update(List<Tache> taches) {
        Programmation programmation = programmationRepository.findById(taches.get(0).getProgrammation().getId()).orElseThrow(() -> new CustomException("Programmation inexistante."));
        log.debug("Sum of Ponderations = {} %", programmation.checkPonderation());
        double ponderation = 0d;
        for (Tache t : taches) {
            if (!t.isDeleted()) {
                ponderation = ponderation + t.getPonderation();
            }
        }

        if (ponderation != 100.0) {
            throw new CustomException("L'ensemble des ponderations de vos taches n'atteint pas 100%.");
        }

        return tacheRepository.saveAll(taches);
    }

    /**
     *
     * @param libelle
     * @param programmationId
     * @param pageable
     * @return
     */
    @Override
    public Page<Tache> get(String libelle, Long programmationId, Pageable pageable) {
        return tacheRepository.findByAndLibelleAndProgrammationId(libelle, programmationId, pageable);
    }

    /**
     *
     * @param programmationId
     * @param pageable
     * @return
     */
    @Override
    public Page<Tache> get(Long programmationId, Pageable pageable) {
        return tacheRepository.findByDeletedFalseAndProgrammationId(programmationId, pageable);
    }

    @Override
    public List<Tache> get(Long programmationId) {
        return tacheRepository.findByDeletedFalseAndProgrammationId(programmationId);
    }

    /**
     *
     * @param taches
     * @return
     */
    @Override
    @Transactional(rollbackFor = CustomException.class)
    public List<Tache> evaluer(List<Tache> taches) {
        Programmation programmation = programmationRepository.findById(taches.get(0).getProgrammation().getId())
                .orElseThrow(() -> new CustomException("Programmation inexistante."));
        List<Tache> tachesdb = this.get(programmation.getId());

        for (Tache t : taches) {
            for (Tache tdb : tachesdb) {
                if (t.getId().equals(tdb.getId()) && (tdb.getValeur() != 1D) && !tdb.isExecute()) {//tache with valeur cible and not yet executee
                    //A PRENDRE EN COMPTE : si fourchette de periodeProgrammation en vigeur
                    TacheEvaluer tacheEvaluer = new TacheEvaluer();
                    TacheEvaluer tacheEvaluerPrecedent = new TacheEvaluer();
                    tacheEvaluerPrecedent = tacheEvaluerRepository.findByIdAndActive(tdb.getId()).orElse(null);
                    this.checkValeurCumulee(tacheEvaluerPrecedent, t, tdb);
                    this.evaluerTacheAValeurCible(tacheEvaluer, tacheEvaluerPrecedent, t, tdb);
                } else if (t.getId().equals(tdb.getId()) && (tdb.getValeur() == 1D) && !tdb.isExecute()) {//tache without valeur cible and not yet executee
                    tdb.setExecute(t.isExecute());
                    tacheRepository.save(tdb);
                }
            }
        }
        return null;//REVIEW
    }

    /**
     *
     * @param aEvaluer : new object tacheEvaluer to insert in database
     * @param precedent : last record of tacheEvaluer for some TacheId
     * @param t : tache in list come from frontEnd for updating TacheEvaluer
     * table
     * @param tdb : tache in list come from database for updating TacheEvaluer
     * table
     */
    void evaluerTacheAValeurCible(TacheEvaluer aEvaluer, TacheEvaluer precedent, Tache t, Tache tdb) {
        if (precedent == null) {//the first insert of tacheEvaluer
            aEvaluer.setTacheId(tdb.getId());
            aEvaluer.setValeurAtteinte(t.getValeur());
            aEvaluer.setValeurCumulee(t.getValeur());
            aEvaluer.setCumuleeActive(true);
            tacheEvaluerRepository.save(aEvaluer);
        } else {
            if (t.getValeur() != 0D) {
                aEvaluer.setTacheId(tdb.getId());
                aEvaluer.setValeurAtteinte(t.getValeur());
                aEvaluer.setValeurCumulee(precedent.getValeurCumulee() + t.getValeur());
                aEvaluer.setCumuleeActive(true);
                precedent.setCumuleeActive(false);
                tacheEvaluerRepository.save(precedent);
                tacheEvaluerRepository.save(aEvaluer);
            } else if ((t.getValeur() == 0D) && !tdb.isExecute()) {//Cumul acquire but tache.execute != true
                tdb.setExecute(t.isExecute());
                tacheRepository.save(tdb);
            }
        }
    }

    /**
     *
     * @param precedent : last record of tacheEvaluer for some TacheId
     * @param t :tache in list come from frontEnd for evaluation
     * @param tdb : tache in list come from database for comparison
     */
    void checkValeurCumulee(TacheEvaluer precedent, Tache t, Tache tdb) {
        if ((t.getId().equals(tdb.getId()) && (tdb.getValeur() != 1D) && !tdb.isExecute())) {//aValeur
            if (precedent == null) {//toute premiere evaluation tache
                if (tdb.getValeur() < t.getValeur()) {
                    throw new CustomException("Le cumul (" + t.getValeur() + ") des valeurs cibles atteintes ne doit pas excéder la valeur cible (" + tdb.getValeur() + ") de la tache.");
                }
            } else if ((tdb.getValeur() < precedent.getValeurCumulee() + t.getValeur())) {
                throw new CustomException("Le cumul (" + precedent.getValeurCumulee() + " + " + t.getValeur() + ") des valeurs cibles atteintes ne doit pas excéder la valeur cible (" + tdb.getValeur() + ") de la tache.");
            }
        }
    }

}
