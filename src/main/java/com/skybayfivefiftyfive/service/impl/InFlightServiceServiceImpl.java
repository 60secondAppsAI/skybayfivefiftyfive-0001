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
import com.skybayfivefiftyfive.dao.InFlightServiceDAO;
import com.skybayfivefiftyfive.domain.InFlightService;
import com.skybayfivefiftyfive.dto.InFlightServiceDTO;
import com.skybayfivefiftyfive.dto.InFlightServiceSearchDTO;
import com.skybayfivefiftyfive.dto.InFlightServicePageDTO;
import com.skybayfivefiftyfive.dto.InFlightServiceConvertCriteriaDTO;
import com.skybayfivefiftyfive.dto.common.RequestDTO;
import com.skybayfivefiftyfive.dto.common.ResultDTO;
import com.skybayfivefiftyfive.service.InFlightServiceService;
import com.skybayfivefiftyfive.util.ControllerUtils;





@Service
public class InFlightServiceServiceImpl extends GenericServiceImpl<InFlightService, Integer> implements InFlightServiceService {

    private final static Logger logger = LoggerFactory.getLogger(InFlightServiceServiceImpl.class);

	@Autowired
	InFlightServiceDAO inFlightServiceDao;

	


	@Override
	public GenericDAO<InFlightService, Integer> getDAO() {
		return (GenericDAO<InFlightService, Integer>) inFlightServiceDao;
	}
	
	public List<InFlightService> findAll () {
		List<InFlightService> inFlightServices = inFlightServiceDao.findAll();
		
		return inFlightServices;	
		
	}

	public ResultDTO addInFlightService(InFlightServiceDTO inFlightServiceDTO, RequestDTO requestDTO) {

		InFlightService inFlightService = new InFlightService();

		inFlightService.setInFlightServiceId(inFlightServiceDTO.getInFlightServiceId());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		inFlightService = inFlightServiceDao.save(inFlightService);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<InFlightService> getAllInFlightServices(Pageable pageable) {
		return inFlightServiceDao.findAll(pageable);
	}

	public Page<InFlightService> getAllInFlightServices(Specification<InFlightService> spec, Pageable pageable) {
		return inFlightServiceDao.findAll(spec, pageable);
	}

	public ResponseEntity<InFlightServicePageDTO> getInFlightServices(InFlightServiceSearchDTO inFlightServiceSearchDTO) {
	
			Integer inFlightServiceId = inFlightServiceSearchDTO.getInFlightServiceId(); 
 			String sortBy = inFlightServiceSearchDTO.getSortBy();
			String sortOrder = inFlightServiceSearchDTO.getSortOrder();
			String searchQuery = inFlightServiceSearchDTO.getSearchQuery();
			Integer page = inFlightServiceSearchDTO.getPage();
			Integer size = inFlightServiceSearchDTO.getSize();

	        Specification<InFlightService> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, inFlightServiceId, "inFlightServiceId"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

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

		Page<InFlightService> inFlightServices = this.getAllInFlightServices(spec, pageable);
		
		//System.out.println(String.valueOf(inFlightServices.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(inFlightServices.getTotalPages()));
		
		List<InFlightService> inFlightServicesList = inFlightServices.getContent();
		
		InFlightServiceConvertCriteriaDTO convertCriteria = new InFlightServiceConvertCriteriaDTO();
		List<InFlightServiceDTO> inFlightServiceDTOs = this.convertInFlightServicesToInFlightServiceDTOs(inFlightServicesList,convertCriteria);
		
		InFlightServicePageDTO inFlightServicePageDTO = new InFlightServicePageDTO();
		inFlightServicePageDTO.setInFlightServices(inFlightServiceDTOs);
		inFlightServicePageDTO.setTotalElements(inFlightServices.getTotalElements());
		return ResponseEntity.ok(inFlightServicePageDTO);
	}

	public List<InFlightServiceDTO> convertInFlightServicesToInFlightServiceDTOs(List<InFlightService> inFlightServices, InFlightServiceConvertCriteriaDTO convertCriteria) {
		
		List<InFlightServiceDTO> inFlightServiceDTOs = new ArrayList<InFlightServiceDTO>();
		
		for (InFlightService inFlightService : inFlightServices) {
			inFlightServiceDTOs.add(convertInFlightServiceToInFlightServiceDTO(inFlightService,convertCriteria));
		}
		
		return inFlightServiceDTOs;

	}
	
	public InFlightServiceDTO convertInFlightServiceToInFlightServiceDTO(InFlightService inFlightService, InFlightServiceConvertCriteriaDTO convertCriteria) {
		
		InFlightServiceDTO inFlightServiceDTO = new InFlightServiceDTO();
		
		inFlightServiceDTO.setInFlightServiceId(inFlightService.getInFlightServiceId());

	

		
		return inFlightServiceDTO;
	}

	public ResultDTO updateInFlightService(InFlightServiceDTO inFlightServiceDTO, RequestDTO requestDTO) {
		
		InFlightService inFlightService = inFlightServiceDao.getById(inFlightServiceDTO.getInFlightServiceId());

		inFlightService.setInFlightServiceId(ControllerUtils.setValue(inFlightService.getInFlightServiceId(), inFlightServiceDTO.getInFlightServiceId()));



        inFlightService = inFlightServiceDao.save(inFlightService);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public InFlightServiceDTO getInFlightServiceDTOById(Integer inFlightServiceId) {
	
		InFlightService inFlightService = inFlightServiceDao.getById(inFlightServiceId);
			
		
		InFlightServiceConvertCriteriaDTO convertCriteria = new InFlightServiceConvertCriteriaDTO();
		return(this.convertInFlightServiceToInFlightServiceDTO(inFlightService,convertCriteria));
	}







}
