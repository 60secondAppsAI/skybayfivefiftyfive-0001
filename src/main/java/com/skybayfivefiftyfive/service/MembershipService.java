package com.skybayfivefiftyfive.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybayfivefiftyfive.domain.Membership;
import com.skybayfivefiftyfive.dto.MembershipDTO;
import com.skybayfivefiftyfive.dto.MembershipSearchDTO;
import com.skybayfivefiftyfive.dto.MembershipPageDTO;
import com.skybayfivefiftyfive.dto.MembershipConvertCriteriaDTO;
import com.skybayfivefiftyfive.service.GenericService;
import com.skybayfivefiftyfive.dto.common.RequestDTO;
import com.skybayfivefiftyfive.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MembershipService extends GenericService<Membership, Integer> {

	List<Membership> findAll();

	ResultDTO addMembership(MembershipDTO membershipDTO, RequestDTO requestDTO);

	ResultDTO updateMembership(MembershipDTO membershipDTO, RequestDTO requestDTO);

    Page<Membership> getAllMemberships(Pageable pageable);

    Page<Membership> getAllMemberships(Specification<Membership> spec, Pageable pageable);

	ResponseEntity<MembershipPageDTO> getMemberships(MembershipSearchDTO membershipSearchDTO);
	
	List<MembershipDTO> convertMembershipsToMembershipDTOs(List<Membership> memberships, MembershipConvertCriteriaDTO convertCriteria);

	MembershipDTO getMembershipDTOById(Integer membershipId);







}





