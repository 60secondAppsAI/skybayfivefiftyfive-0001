package com.skybayfivefiftyfive.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.skybayfivefiftyfive.dao.GenericDAO;
import com.skybayfivefiftyfive.service.GenericService;
import com.skybayfivefiftyfive.service.impl.GenericServiceImpl;
import com.skybayfivefiftyfive.dao.RewardProgramDAO;
import com.skybayfivefiftyfive.domain.RewardProgram;
import com.skybayfivefiftyfive.dto.RewardProgramDTO;
import com.skybayfivefiftyfive.dto.RewardProgramSearchDTO;
import com.skybayfivefiftyfive.dto.RewardProgramPageDTO;
import com.skybayfivefiftyfive.dto.RewardProgramConvertCriteriaDTO;
import com.skybayfivefiftyfive.dto.common.RequestDTO;
import com.skybayfivefiftyfive.dto.common.ResultDTO;
import com.skybayfivefiftyfive.service.RewardProgramService;
import com.skybayfivefiftyfive.util.ControllerUtils;





@Service
public class RewardProgramServiceImpl extends GenericServiceImpl<RewardProgram, Integer> implements RewardProgramService {

    private final static Logger logger = LoggerFactory.getLogger(RewardProgramServiceImpl.class);

	@Autowired
	RewardProgramDAO rewardProgramDao;

	


	@Override
	public GenericDAO<RewardProgram, Integer> getDAO() {
		return (GenericDAO<RewardProgram, Integer>) rewardProgramDao;
	}
	
	public List<RewardProgram> findAll () {
		List<RewardProgram> rewardPrograms = rewardProgramDao.findAll();
		
		return rewardPrograms;	
		
	}

	public ResultDTO addRewardProgram(RewardProgramDTO rewardProgramDTO, RequestDTO requestDTO) {

		RewardProgram rewardProgram = new RewardProgram();

		rewardProgram.setRewardProgramId(rewardProgramDTO.getRewardProgramId());


		rewardProgram.setName(rewardProgramDTO.getName());


		rewardProgram.setPointsPerMile(rewardProgramDTO.getPointsPerMile());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		rewardProgram = rewardProgramDao.save(rewardProgram);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<RewardProgram> getAllRewardPrograms(Pageable pageable) {
		return rewardProgramDao.findAll(pageable);
	}

	public Page<RewardProgram> getAllRewardPrograms(Specification<RewardProgram> spec, Pageable pageable) {
		return rewardProgramDao.findAll(spec, pageable);
	}

	public ResponseEntity<RewardProgramPageDTO> getRewardPrograms(RewardProgramSearchDTO rewardProgramSearchDTO) {
	
			Integer rewardProgramId = rewardProgramSearchDTO.getRewardProgramId(); 
 			String name = rewardProgramSearchDTO.getName(); 
  			String sortBy = rewardProgramSearchDTO.getSortBy();
			String sortOrder = rewardProgramSearchDTO.getSortOrder();
			String searchQuery = rewardProgramSearchDTO.getSearchQuery();
			Integer page = rewardProgramSearchDTO.getPage();
			Integer size = rewardProgramSearchDTO.getSize();

	        Specification<RewardProgram> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, rewardProgramId, "rewardProgramId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<RewardProgram> rewardPrograms = this.getAllRewardPrograms(spec, pageable);
		
		//System.out.println(String.valueOf(rewardPrograms.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(rewardPrograms.getTotalPages()));
		
		List<RewardProgram> rewardProgramsList = rewardPrograms.getContent();
		
		RewardProgramConvertCriteriaDTO convertCriteria = new RewardProgramConvertCriteriaDTO();
		List<RewardProgramDTO> rewardProgramDTOs = this.convertRewardProgramsToRewardProgramDTOs(rewardProgramsList,convertCriteria);
		
		RewardProgramPageDTO rewardProgramPageDTO = new RewardProgramPageDTO();
		rewardProgramPageDTO.setRewardPrograms(rewardProgramDTOs);
		rewardProgramPageDTO.setTotalElements(rewardPrograms.getTotalElements());
		return ResponseEntity.ok(rewardProgramPageDTO);
	}

	public List<RewardProgramDTO> convertRewardProgramsToRewardProgramDTOs(List<RewardProgram> rewardPrograms, RewardProgramConvertCriteriaDTO convertCriteria) {
		
		List<RewardProgramDTO> rewardProgramDTOs = new ArrayList<RewardProgramDTO>();
		
		for (RewardProgram rewardProgram : rewardPrograms) {
			rewardProgramDTOs.add(convertRewardProgramToRewardProgramDTO(rewardProgram,convertCriteria));
		}
		
		return rewardProgramDTOs;

	}
	
	public RewardProgramDTO convertRewardProgramToRewardProgramDTO(RewardProgram rewardProgram, RewardProgramConvertCriteriaDTO convertCriteria) {
		
		RewardProgramDTO rewardProgramDTO = new RewardProgramDTO();
		
		rewardProgramDTO.setRewardProgramId(rewardProgram.getRewardProgramId());

	
		rewardProgramDTO.setName(rewardProgram.getName());

	
		rewardProgramDTO.setPointsPerMile(rewardProgram.getPointsPerMile());

	

		
		return rewardProgramDTO;
	}

	public ResultDTO updateRewardProgram(RewardProgramDTO rewardProgramDTO, RequestDTO requestDTO) {
		
		RewardProgram rewardProgram = rewardProgramDao.getById(rewardProgramDTO.getRewardProgramId());

		rewardProgram.setRewardProgramId(ControllerUtils.setValue(rewardProgram.getRewardProgramId(), rewardProgramDTO.getRewardProgramId()));

		rewardProgram.setName(ControllerUtils.setValue(rewardProgram.getName(), rewardProgramDTO.getName()));

		rewardProgram.setPointsPerMile(ControllerUtils.setValue(rewardProgram.getPointsPerMile(), rewardProgramDTO.getPointsPerMile()));



        rewardProgram = rewardProgramDao.save(rewardProgram);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public RewardProgramDTO getRewardProgramDTOById(Integer rewardProgramId) {
	
		RewardProgram rewardProgram = rewardProgramDao.getById(rewardProgramId);
			
		
		RewardProgramConvertCriteriaDTO convertCriteria = new RewardProgramConvertCriteriaDTO();
		return(this.convertRewardProgramToRewardProgramDTO(rewardProgram,convertCriteria));
	}







}
