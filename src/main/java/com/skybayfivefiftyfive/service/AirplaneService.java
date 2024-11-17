package com.skybayfivefiftyfive.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybayfivefiftyfive.domain.Airplane;
import com.skybayfivefiftyfive.dto.AirplaneDTO;
import com.skybayfivefiftyfive.dto.AirplaneSearchDTO;
import com.skybayfivefiftyfive.dto.AirplanePageDTO;
import com.skybayfivefiftyfive.dto.AirplaneConvertCriteriaDTO;
import com.skybayfivefiftyfive.service.GenericService;
import com.skybayfivefiftyfive.dto.common.RequestDTO;
import com.skybayfivefiftyfive.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AirplaneService extends GenericService<Airplane, Integer> {

	List<Airplane> findAll();

	ResultDTO addAirplane(AirplaneDTO airplaneDTO, RequestDTO requestDTO);

	ResultDTO updateAirplane(AirplaneDTO airplaneDTO, RequestDTO requestDTO);

    Page<Airplane> getAllAirplanes(Pageable pageable);

    Page<Airplane> getAllAirplanes(Specification<Airplane> spec, Pageable pageable);

	ResponseEntity<AirplanePageDTO> getAirplanes(AirplaneSearchDTO airplaneSearchDTO);
	
	List<AirplaneDTO> convertAirplanesToAirplaneDTOs(List<Airplane> airplanes, AirplaneConvertCriteriaDTO convertCriteria);

	AirplaneDTO getAirplaneDTOById(Integer airplaneId);







}





