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
import com.skybayfivefiftyfive.dao.MealDAO;
import com.skybayfivefiftyfive.domain.Meal;
import com.skybayfivefiftyfive.dto.MealDTO;
import com.skybayfivefiftyfive.dto.MealSearchDTO;
import com.skybayfivefiftyfive.dto.MealPageDTO;
import com.skybayfivefiftyfive.dto.MealConvertCriteriaDTO;
import com.skybayfivefiftyfive.dto.common.RequestDTO;
import com.skybayfivefiftyfive.dto.common.ResultDTO;
import com.skybayfivefiftyfive.service.MealService;
import com.skybayfivefiftyfive.util.ControllerUtils;





@Service
public class MealServiceImpl extends GenericServiceImpl<Meal, Integer> implements MealService {

    private final static Logger logger = LoggerFactory.getLogger(MealServiceImpl.class);

	@Autowired
	MealDAO mealDao;

	


	@Override
	public GenericDAO<Meal, Integer> getDAO() {
		return (GenericDAO<Meal, Integer>) mealDao;
	}
	
	public List<Meal> findAll () {
		List<Meal> meals = mealDao.findAll();
		
		return meals;	
		
	}

	public ResultDTO addMeal(MealDTO mealDTO, RequestDTO requestDTO) {

		Meal meal = new Meal();

		meal.setMealId(mealDTO.getMealId());


		meal.setName(mealDTO.getName());


		meal.setDescription(mealDTO.getDescription());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		meal = mealDao.save(meal);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Meal> getAllMeals(Pageable pageable) {
		return mealDao.findAll(pageable);
	}

	public Page<Meal> getAllMeals(Specification<Meal> spec, Pageable pageable) {
		return mealDao.findAll(spec, pageable);
	}

	public ResponseEntity<MealPageDTO> getMeals(MealSearchDTO mealSearchDTO) {
	
			Integer mealId = mealSearchDTO.getMealId(); 
 			String name = mealSearchDTO.getName(); 
 			String description = mealSearchDTO.getDescription(); 
 			String sortBy = mealSearchDTO.getSortBy();
			String sortOrder = mealSearchDTO.getSortOrder();
			String searchQuery = mealSearchDTO.getSearchQuery();
			Integer page = mealSearchDTO.getPage();
			Integer size = mealSearchDTO.getSize();

	        Specification<Meal> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, mealId, "mealId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Meal> meals = this.getAllMeals(spec, pageable);
		
		//System.out.println(String.valueOf(meals.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(meals.getTotalPages()));
		
		List<Meal> mealsList = meals.getContent();
		
		MealConvertCriteriaDTO convertCriteria = new MealConvertCriteriaDTO();
		List<MealDTO> mealDTOs = this.convertMealsToMealDTOs(mealsList,convertCriteria);
		
		MealPageDTO mealPageDTO = new MealPageDTO();
		mealPageDTO.setMeals(mealDTOs);
		mealPageDTO.setTotalElements(meals.getTotalElements());
		return ResponseEntity.ok(mealPageDTO);
	}

	public List<MealDTO> convertMealsToMealDTOs(List<Meal> meals, MealConvertCriteriaDTO convertCriteria) {
		
		List<MealDTO> mealDTOs = new ArrayList<MealDTO>();
		
		for (Meal meal : meals) {
			mealDTOs.add(convertMealToMealDTO(meal,convertCriteria));
		}
		
		return mealDTOs;

	}
	
	public MealDTO convertMealToMealDTO(Meal meal, MealConvertCriteriaDTO convertCriteria) {
		
		MealDTO mealDTO = new MealDTO();
		
		mealDTO.setMealId(meal.getMealId());

	
		mealDTO.setName(meal.getName());

	
		mealDTO.setDescription(meal.getDescription());

	

		
		return mealDTO;
	}

	public ResultDTO updateMeal(MealDTO mealDTO, RequestDTO requestDTO) {
		
		Meal meal = mealDao.getById(mealDTO.getMealId());

		meal.setMealId(ControllerUtils.setValue(meal.getMealId(), mealDTO.getMealId()));

		meal.setName(ControllerUtils.setValue(meal.getName(), mealDTO.getName()));

		meal.setDescription(ControllerUtils.setValue(meal.getDescription(), mealDTO.getDescription()));



        meal = mealDao.save(meal);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public MealDTO getMealDTOById(Integer mealId) {
	
		Meal meal = mealDao.getById(mealId);
			
		
		MealConvertCriteriaDTO convertCriteria = new MealConvertCriteriaDTO();
		return(this.convertMealToMealDTO(meal,convertCriteria));
	}







}
