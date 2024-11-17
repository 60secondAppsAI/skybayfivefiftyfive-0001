package com.skybayfivefiftyfive.service;

import com.skybayfivefiftyfive.dao.GenericDAO;

public interface GenericService<T, ID> {

    abstract GenericDAO<T, ID> getDAO();

    T getById(Integer id) ;

}