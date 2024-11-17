package com.skybayfivefiftyfive.dao;

import java.util.List;

import com.skybayfivefiftyfive.dao.GenericDAO;
import com.skybayfivefiftyfive.domain.RewardProgram;





public interface RewardProgramDAO extends GenericDAO<RewardProgram, Integer> {
  
	List<RewardProgram> findAll();
	






}


