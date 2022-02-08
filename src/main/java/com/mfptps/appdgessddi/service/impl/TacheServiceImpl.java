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
import com.mfptps.appdgessddi.service.EvaluationService;
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
    private final EvaluationService evaluationService;

    public TacheServiceImpl(TacheRepository tacheRepository,
            TacheEvaluerRepository tacheEvaluerRepository,
            ProgrammationRepository programmationRepository,
            EvaluationService evaluationService) {
        this.tacheRepository = tacheRepository;
        this.tacheEvaluerRepository = tacheEvaluerRepository;
        this.programmationRepository = programmationRepository;
        this.evaluationService = evaluationService;
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
        //on recupere la programmation concernee a partir de la liste de taches recues en parametre
        Programmation programmation = taches.get(0).getProgrammation();

        //on recupere les taches(enregistrees lors de la programmation) depuis la bd
        List<Tache> tachesdb = this.get(programmation.getId());

        //on s'assure que la date du jour est dans l'intervalle de periode de la programmation
        //une exception sera levee au cas ou on voudra evaluer une programmation en dehors de sa periode
        long periodeId = evaluationService.checkPeriodeEvaluation(programmation.getId());

        //parcourons simultanement la liste des taches a evaluer et celle recupere depuis la bd(pour comparaison)
        for (Tache t : taches) {
            for (Tache tdb : tachesdb) {
                //instructions pour taches a valeur cible
                //si tacheAEvaluer correspond a tacheFromDB
                //si tacheFromDB est non encore executee et possede valeur cible
                if (t.getId().equals(tdb.getId()) && (tdb.getValeur() != 1D) && !tdb.isExecute()) {
                    TacheEvaluer tacheEvaluerPrecedent = new TacheEvaluer();
                    //on recupere l'evaluation precedente de la ieme tache 
                    tacheEvaluerPrecedent = tacheEvaluerRepository.findByIdAndActive(tdb.getId()).orElse(null); //===============
//                    this.checkValeurCumulee(tacheEvaluerPrecedent, t, tdb);
                    //appel de la sous methode d'evaluation
                    this.evaluerTacheAValeurCible(tacheEvaluerPrecedent, t, tdb, periodeId);
                } //instructions pour tache sans valeur cible et non encore executee
                //dans ce cas, il n'y a pas de creation de ligne TacheEvaluer. On met a jour la ligne Tache uniquement
                else if (t.getId().equals(tdb.getId()) && (tdb.getValeur() == 1D) && !tdb.isExecute()) {
                    tdb.setExecute(t.isExecute());
                    tacheRepository.save(tdb);
                }
            }
        }
        //log.info("_________________________valeurActuelle = {} ", tacheEvaluerRepository.sumOfCumuleesTachesByProgrammation(programmation.getId()));
        return null;
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
    void evaluerTacheAValeurCible(/*TacheEvaluer aEvaluer, */TacheEvaluer precedent, Tache t, Tache tdb, long periodeId) {
        TacheEvaluer aEvaluer = new TacheEvaluer();

        //execute=true si la valeurCible renseignee à l'evaluation excede celle renseignee lors de la programmation
        boolean execute = (t.getValeur() >= tdb.getValeur());

        //si c'est la toute premiere evaluation de la tache
        if (precedent == null) {//the first insert of tacheEvaluer
            this.firstEvaluationOfSomeTache(aEvaluer, execute, t, tdb, periodeId);
        } else {//si c'est la nieme evaluation de la tache
            //execute=true si la somme de valeurCibles precedente et encours renseignee à l'evaluation excede celle renseignee lors de la programmation
            execute = ((precedent.getValeurCumulee() + t.getValeur()) >= tdb.getValeur());

            if (t.getValeur() != 0D) {
                this.newOrUpdateEvaluationOfSomeTache(aEvaluer, precedent, execute, t, tdb, periodeId);
            } //si l'evaluation contient juste l'info execute=true et que la tacheFromDB a execute=false (pas encore marquee execute)
            else if ((t.getValeur() == 0D) && !tdb.isExecute()) {//Cumul acquire but tache.execute != true
                tdb.setExecute(t.isExecute());
                tacheRepository.save(tdb);
            }
        }
    }

    /**
     *
     * @param aEvaluer
     * @param execute
     * @param t
     * @param tdb
     * @param periodeId
     */
    void firstEvaluationOfSomeTache(TacheEvaluer aEvaluer, boolean execute, Tache t, Tache tdb, long periodeId) {
        aEvaluer.setTache(tdb);
        aEvaluer.setValeurAtteinte(t.getValeur());
        aEvaluer.setValeurCumulee(t.getValeur());
        aEvaluer.setCumuleeActive(true);
        aEvaluer.setPeriodeId(periodeId);
        tacheEvaluerRepository.save(aEvaluer);

        //si la valeur de la toute premiere evaluation excede deja la valeur cible programmee,
        //on indique systematiquement que la tache est executee
        if (execute) {
            tdb.setExecute(execute);
            tacheRepository.save(tdb);
        }
    }

    /**
     *
     * @param aEvaluer
     * @param execute
     * @param t
     * @param tdb
     * @param periodeId
     */
    void newOrUpdateEvaluationOfSomeTache(TacheEvaluer aEvaluer, TacheEvaluer precedent, boolean execute, Tache t, Tache tdb, long periodeId) {
        if (precedent.getPeriodeId() == periodeId) {//Mise à jour car nous sommes toujours dans la meme periode
            precedent.setValeurAtteinte(t.getValeur());
            precedent.setValeurCumulee(precedent.getValeurAtteinte());
            tacheEvaluerRepository.save(precedent);

            if (execute) {//on marque systematiquement que la tache est executee
                tdb.setExecute(execute);
                tacheRepository.save(tdb);
            }
        } else {//nouvelle evaluation de la periode suivante
            aEvaluer.setTache(tdb);
            aEvaluer.setValeurAtteinte(t.getValeur());
            aEvaluer.setValeurCumulee(precedent.getValeurCumulee() + t.getValeur());
            aEvaluer.setCumuleeActive(true);
            aEvaluer.setPeriodeId(periodeId);
            precedent.setCumuleeActive(false);
            tacheEvaluerRepository.save(precedent);
            tacheEvaluerRepository.save(aEvaluer);

            if (execute) {//on marque systematiquement que la tache est executee
                tdb.setExecute(execute);
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
