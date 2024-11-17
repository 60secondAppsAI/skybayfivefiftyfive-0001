package com.skybayfivefiftyfive.dao;

import java.util.List;

import com.skybayfivefiftyfive.dao.GenericDAO;
import com.skybayfivefiftyfive.domain.LoungeAccess;





public interface LoungeAccessDAO extends GenericDAO<LoungeAccess, Integer> {
  
	List<LoungeAccess> findAll();
	






}


