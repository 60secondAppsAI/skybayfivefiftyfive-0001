package com.skybayfivefiftyfive.dao;

import java.util.List;

import com.skybayfivefiftyfive.dao.GenericDAO;
import com.skybayfivefiftyfive.domain.Discount;





public interface DiscountDAO extends GenericDAO<Discount, Integer> {
  
	List<Discount> findAll();
	






}


