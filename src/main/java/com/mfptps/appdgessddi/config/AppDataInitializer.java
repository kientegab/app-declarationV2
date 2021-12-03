/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.config;

import com.mfptps.appdgessddi.entities.Exercice;
import com.mfptps.appdgessddi.enums.ExerciceStatus;
import com.mfptps.appdgessddi.repositories.ExerciceRepository;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Slf4j
@Configuration
public class AppDataInitializer {

    private final ExerciceRepository exerciceRepository;

    public AppDataInitializer(ExerciceRepository exerciceRepository) {
        this.exerciceRepository = exerciceRepository;
    }

    @PostConstruct
    public void initialization() {
        log.info("Application data initialization");
        if (0 == exerciceRepository.count()
                || !exerciceRepository.findByStatut(ExerciceStatus.EN_COURS).isPresent()
                || !exerciceRepository.findByStatut(ExerciceStatus.EN_ATTENTE).isPresent()) {
            LocalDate dateDebut = LocalDate.of(LocalDate.now().getYear(), Month.JANUARY, 1);
            LocalDate dateFin = LocalDate.of(LocalDate.now().getYear(), Month.DECEMBER, 31);
            Exercice exerciceCourant = new Exercice();
            Exercice exerciceAVenir = new Exercice();

            exerciceCourant.setDebut(dateDebut);
            exerciceCourant.setFin(dateFin);
            exerciceCourant.setStatut(ExerciceStatus.EN_COURS);

            exerciceAVenir.setDebut(dateDebut.plusYears(1));
            exerciceAVenir.setFin(dateFin.plusYears(1));
            exerciceAVenir.setStatut(ExerciceStatus.EN_ATTENTE);

            exerciceRepository.saveAll(Arrays.asList(exerciceCourant, exerciceAVenir));
        }

        log.info("End of data initialization");
    }

}
