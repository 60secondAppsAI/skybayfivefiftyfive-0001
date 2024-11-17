package com.skybayfivefiftyfive.dao;

import java.util.List;

import com.skybayfivefiftyfive.dao.GenericDAO;
import com.skybayfivefiftyfive.domain.Airplane;





public interface AirplaneDAO extends GenericDAO<Airplane, Integer> {
  
	List<Airplane> findAll();
	






}


