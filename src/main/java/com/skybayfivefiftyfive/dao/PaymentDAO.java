package com.skybayfivefiftyfive.dao;

import java.util.List;

import com.skybayfivefiftyfive.dao.GenericDAO;
import com.skybayfivefiftyfive.domain.Payment;





public interface PaymentDAO extends GenericDAO<Payment, Integer> {
  
	List<Payment> findAll();
	






}


