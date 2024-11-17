package com.skybayfivefiftyfive.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybayfivefiftyfive.domain.LoungeAccess;
import com.skybayfivefiftyfive.dto.LoungeAccessDTO;
import com.skybayfivefiftyfive.dto.LoungeAccessSearchDTO;
import com.skybayfivefiftyfive.dto.LoungeAccessPageDTO;
import com.skybayfivefiftyfive.dto.LoungeAccessConvertCriteriaDTO;
import com.skybayfivefiftyfive.service.GenericService;
import com.skybayfivefiftyfive.dto.common.RequestDTO;
import com.skybayfivefiftyfive.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface LoungeAccessService extends GenericService<LoungeAccess, Integer> {

	List<LoungeAccess> findAll();

	ResultDTO addLoungeAccess(LoungeAccessDTO loungeAccessDTO, RequestDTO requestDTO);

	ResultDTO updateLoungeAccess(LoungeAccessDTO loungeAccessDTO, RequestDTO requestDTO);

    Page<LoungeAccess> getAllLoungeAccesss(Pageable pageable);

    Page<LoungeAccess> getAllLoungeAccesss(Specification<LoungeAccess> spec, Pageable pageable);

	ResponseEntity<LoungeAccessPageDTO> getLoungeAccesss(LoungeAccessSearchDTO loungeAccessSearchDTO);
	
	List<LoungeAccessDTO> convertLoungeAccesssToLoungeAccessDTOs(List<LoungeAccess> loungeAccesss, LoungeAccessConvertCriteriaDTO convertCriteria);

	LoungeAccessDTO getLoungeAccessDTOById(Integer loungeAccessId);







}





