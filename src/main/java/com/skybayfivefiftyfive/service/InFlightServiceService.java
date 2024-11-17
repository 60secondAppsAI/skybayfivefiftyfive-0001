package com.skybayfivefiftyfive.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybayfivefiftyfive.domain.InFlightService;
import com.skybayfivefiftyfive.dto.InFlightServiceDTO;
import com.skybayfivefiftyfive.dto.InFlightServiceSearchDTO;
import com.skybayfivefiftyfive.dto.InFlightServicePageDTO;
import com.skybayfivefiftyfive.dto.InFlightServiceConvertCriteriaDTO;
import com.skybayfivefiftyfive.service.GenericService;
import com.skybayfivefiftyfive.dto.common.RequestDTO;
import com.skybayfivefiftyfive.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface InFlightServiceService extends GenericService<InFlightService, Integer> {

	List<InFlightService> findAll();

	ResultDTO addInFlightService(InFlightServiceDTO inFlightServiceDTO, RequestDTO requestDTO);

	ResultDTO updateInFlightService(InFlightServiceDTO inFlightServiceDTO, RequestDTO requestDTO);

    Page<InFlightService> getAllInFlightServices(Pageable pageable);

    Page<InFlightService> getAllInFlightServices(Specification<InFlightService> spec, Pageable pageable);

	ResponseEntity<InFlightServicePageDTO> getInFlightServices(InFlightServiceSearchDTO inFlightServiceSearchDTO);
	
	List<InFlightServiceDTO> convertInFlightServicesToInFlightServiceDTOs(List<InFlightService> inFlightServices, InFlightServiceConvertCriteriaDTO convertCriteria);

	InFlightServiceDTO getInFlightServiceDTOById(Integer inFlightServiceId);







}





