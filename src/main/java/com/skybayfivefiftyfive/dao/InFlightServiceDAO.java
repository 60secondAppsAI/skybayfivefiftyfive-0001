package com.skybayfivefiftyfive.dao;

import java.util.List;

import com.skybayfivefiftyfive.dao.GenericDAO;
import com.skybayfivefiftyfive.domain.InFlightService;





public interface InFlightServiceDAO extends GenericDAO<InFlightService, Integer> {
  
	List<InFlightService> findAll();
	






}


