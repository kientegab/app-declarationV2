package com.mfptps.appdgessddi.service.impl;

import com.mfptps.appdgessddi.entities.Exercice;
import com.mfptps.appdgessddi.repositories.ExerciceRepository;
import com.mfptps.appdgessddi.service.ExerciceService;
import com.mfptps.appdgessddi.service.dto.ExerciceDTO;
import com.mfptps.appdgessddi.service.mapper.ExerciceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
public class ExerciceServiceImpl implements ExerciceService {

private final ExerciceRepository exerciceRepository;
private final ExerciceMapper exerciceMapper ;

    public ExerciceServiceImpl(ExerciceRepository exerciceRepository, ExerciceMapper exerciceMapper) {
        this.exerciceRepository = exerciceRepository;
        this.exerciceMapper = exerciceMapper;
    }


    @Override
    public Exercice create(ExerciceDTO exerciceDTO) {

        Exercice exercice=exerciceMapper.toEntity(exerciceDTO);
        return exerciceRepository.save(exercice);
    }


    @Override
    public Exercice update(Exercice exercice) {
        return exerciceRepository.save(exercice);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Exercice> get(Long id) {
      return exerciceRepository.findById(id);
    }

    @Override
    public Page<Exercice> findAll(Pageable pageable) {
        return exerciceRepository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
    exerciceRepository.deleteById(id);
    }
}
