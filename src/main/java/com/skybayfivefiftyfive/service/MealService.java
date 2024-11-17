package com.skybayfivefiftyfive.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybayfivefiftyfive.domain.Meal;
import com.skybayfivefiftyfive.dto.MealDTO;
import com.skybayfivefiftyfive.dto.MealSearchDTO;
import com.skybayfivefiftyfive.dto.MealPageDTO;
import com.skybayfivefiftyfive.dto.MealConvertCriteriaDTO;
import com.skybayfivefiftyfive.service.GenericService;
import com.skybayfivefiftyfive.dto.common.RequestDTO;
import com.skybayfivefiftyfive.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MealService extends GenericService<Meal, Integer> {

	List<Meal> findAll();

	ResultDTO addMeal(MealDTO mealDTO, RequestDTO requestDTO);

	ResultDTO updateMeal(MealDTO mealDTO, RequestDTO requestDTO);

    Page<Meal> getAllMeals(Pageable pageable);

    Page<Meal> getAllMeals(Specification<Meal> spec, Pageable pageable);

	ResponseEntity<MealPageDTO> getMeals(MealSearchDTO mealSearchDTO);
	
	List<MealDTO> convertMealsToMealDTOs(List<Meal> meals, MealConvertCriteriaDTO convertCriteria);

	MealDTO getMealDTOById(Integer mealId);







}





