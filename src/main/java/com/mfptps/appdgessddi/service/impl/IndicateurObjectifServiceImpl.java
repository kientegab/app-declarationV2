package com.mfptps.appdgessddi.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mfptps.appdgessddi.entities.Activites;
import com.mfptps.appdgessddi.entities.IndicateurObjectif;
import com.mfptps.appdgessddi.repositories.ActivitesRepository;
import com.mfptps.appdgessddi.repositories.IndicateurObjectifRepository;
import com.mfptps.appdgessddi.service.IndicateurObjectifService;
import com.mfptps.appdgessddi.service.dto.IndicateurObjectifDTO;
import com.mfptps.appdgessddi.service.mapper.ActivitesMapper;
import com.mfptps.appdgessddi.service.mapper.IndicateurObjectifMapper;

@Service
@Transactional
public class IndicateurObjectifServiceImpl implements IndicateurObjectifService {
	
	private final IndicateurObjectifRepository indicateurObjectifRepository;
	private IndicateurObjectifMapper indicateurObjectifMapper;
	
	
	public IndicateurObjectifServiceImpl(IndicateurObjectifRepository indicateurObjectifRepository,
			IndicateurObjectifMapper indicateurObjectifMapper) {
		this.indicateurObjectifRepository = indicateurObjectifRepository;
		this.indicateurObjectifMapper = indicateurObjectifMapper;
	}


	@Override
	public IndicateurObjectif create(IndicateurObjectifDTO indicateurObjectifDTO) {
		IndicateurObjectif indicateur = indicateurObjectifMapper.toEntity(indicateurObjectifDTO);
        return indicateurObjectifRepository.save(indicateur);
	}


	@Override
	public IndicateurObjectif update(IndicateurObjectif indicateurObjectif) {

		 return indicateurObjectifRepository.save(indicateurObjectif);
	}


	@Override
    @Transactional(readOnly = true)
	public Optional<IndicateurObjectif> get(Long id) {
		 return indicateurObjectifRepository.findById(id);
	}


	@Override
	public Page<IndicateurObjectif> findAll(Pageable pageable) {
		return indicateurObjectifRepository.findAll(pageable);
	}


	@Override
	public void delete(Long id) {
		 indicateurObjectifRepository.deleteById(id);
		
	}
	
	
	

}
