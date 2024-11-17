package com.skybayfivefiftyfive.dao;

import java.util.List;

import com.skybayfivefiftyfive.dao.GenericDAO;
import com.skybayfivefiftyfive.domain.Membership;





public interface MembershipDAO extends GenericDAO<Membership, Integer> {
  
	List<Membership> findAll();
	






}


