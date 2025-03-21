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
import com.skybayfivefiftyfive.dao.MembershipDAO;
import com.skybayfivefiftyfive.domain.Membership;
import com.skybayfivefiftyfive.dto.MembershipDTO;
import com.skybayfivefiftyfive.dto.MembershipSearchDTO;
import com.skybayfivefiftyfive.dto.MembershipPageDTO;
import com.skybayfivefiftyfive.dto.MembershipConvertCriteriaDTO;
import com.skybayfivefiftyfive.dto.common.RequestDTO;
import com.skybayfivefiftyfive.dto.common.ResultDTO;
import com.skybayfivefiftyfive.service.MembershipService;
import com.skybayfivefiftyfive.util.ControllerUtils;





@Service
public class MembershipServiceImpl extends GenericServiceImpl<Membership, Integer> implements MembershipService {

    private final static Logger logger = LoggerFactory.getLogger(MembershipServiceImpl.class);

	@Autowired
	MembershipDAO membershipDao;

	


	@Override
	public GenericDAO<Membership, Integer> getDAO() {
		return (GenericDAO<Membership, Integer>) membershipDao;
	}
	
	public List<Membership> findAll () {
		List<Membership> memberships = membershipDao.findAll();
		
		return memberships;	
		
	}

	public ResultDTO addMembership(MembershipDTO membershipDTO, RequestDTO requestDTO) {

		Membership membership = new Membership();

		membership.setMembershipId(membershipDTO.getMembershipId());


		membership.setPointsAccumulated(membershipDTO.getPointsAccumulated());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		membership = membershipDao.save(membership);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Membership> getAllMemberships(Pageable pageable) {
		return membershipDao.findAll(pageable);
	}

	public Page<Membership> getAllMemberships(Specification<Membership> spec, Pageable pageable) {
		return membershipDao.findAll(spec, pageable);
	}

	public ResponseEntity<MembershipPageDTO> getMemberships(MembershipSearchDTO membershipSearchDTO) {
	
			Integer membershipId = membershipSearchDTO.getMembershipId(); 
  			String sortBy = membershipSearchDTO.getSortBy();
			String sortOrder = membershipSearchDTO.getSortOrder();
			String searchQuery = membershipSearchDTO.getSearchQuery();
			Integer page = membershipSearchDTO.getPage();
			Integer size = membershipSearchDTO.getSize();

	        Specification<Membership> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, membershipId, "membershipId"); 
			
			

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

		Page<Membership> memberships = this.getAllMemberships(spec, pageable);
		
		//System.out.println(String.valueOf(memberships.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(memberships.getTotalPages()));
		
		List<Membership> membershipsList = memberships.getContent();
		
		MembershipConvertCriteriaDTO convertCriteria = new MembershipConvertCriteriaDTO();
		List<MembershipDTO> membershipDTOs = this.convertMembershipsToMembershipDTOs(membershipsList,convertCriteria);
		
		MembershipPageDTO membershipPageDTO = new MembershipPageDTO();
		membershipPageDTO.setMemberships(membershipDTOs);
		membershipPageDTO.setTotalElements(memberships.getTotalElements());
		return ResponseEntity.ok(membershipPageDTO);
	}

	public List<MembershipDTO> convertMembershipsToMembershipDTOs(List<Membership> memberships, MembershipConvertCriteriaDTO convertCriteria) {
		
		List<MembershipDTO> membershipDTOs = new ArrayList<MembershipDTO>();
		
		for (Membership membership : memberships) {
			membershipDTOs.add(convertMembershipToMembershipDTO(membership,convertCriteria));
		}
		
		return membershipDTOs;

	}
	
	public MembershipDTO convertMembershipToMembershipDTO(Membership membership, MembershipConvertCriteriaDTO convertCriteria) {
		
		MembershipDTO membershipDTO = new MembershipDTO();
		
		membershipDTO.setMembershipId(membership.getMembershipId());

	
		membershipDTO.setPointsAccumulated(membership.getPointsAccumulated());

	

		
		return membershipDTO;
	}

	public ResultDTO updateMembership(MembershipDTO membershipDTO, RequestDTO requestDTO) {
		
		Membership membership = membershipDao.getById(membershipDTO.getMembershipId());

		membership.setMembershipId(ControllerUtils.setValue(membership.getMembershipId(), membershipDTO.getMembershipId()));

		membership.setPointsAccumulated(ControllerUtils.setValue(membership.getPointsAccumulated(), membershipDTO.getPointsAccumulated()));



        membership = membershipDao.save(membership);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public MembershipDTO getMembershipDTOById(Integer membershipId) {
	
		Membership membership = membershipDao.getById(membershipId);
			
		
		MembershipConvertCriteriaDTO convertCriteria = new MembershipConvertCriteriaDTO();
		return(this.convertMembershipToMembershipDTO(membership,convertCriteria));
	}







}
