package com.skybayfivefiftyfive.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybayfivefiftyfive.domain.RewardProgram;
import com.skybayfivefiftyfive.dto.RewardProgramDTO;
import com.skybayfivefiftyfive.dto.RewardProgramSearchDTO;
import com.skybayfivefiftyfive.dto.RewardProgramPageDTO;
import com.skybayfivefiftyfive.dto.RewardProgramConvertCriteriaDTO;
import com.skybayfivefiftyfive.service.GenericService;
import com.skybayfivefiftyfive.dto.common.RequestDTO;
import com.skybayfivefiftyfive.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface RewardProgramService extends GenericService<RewardProgram, Integer> {

	List<RewardProgram> findAll();

	ResultDTO addRewardProgram(RewardProgramDTO rewardProgramDTO, RequestDTO requestDTO);

	ResultDTO updateRewardProgram(RewardProgramDTO rewardProgramDTO, RequestDTO requestDTO);

    Page<RewardProgram> getAllRewardPrograms(Pageable pageable);

    Page<RewardProgram> getAllRewardPrograms(Specification<RewardProgram> spec, Pageable pageable);

	ResponseEntity<RewardProgramPageDTO> getRewardPrograms(RewardProgramSearchDTO rewardProgramSearchDTO);
	
	List<RewardProgramDTO> convertRewardProgramsToRewardProgramDTOs(List<RewardProgram> rewardPrograms, RewardProgramConvertCriteriaDTO convertCriteria);

	RewardProgramDTO getRewardProgramDTOById(Integer rewardProgramId);







}





