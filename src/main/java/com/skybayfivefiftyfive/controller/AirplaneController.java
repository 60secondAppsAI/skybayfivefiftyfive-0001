package com.skybayfivefiftyfive.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.skybayfivefiftyfive.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.skybayfivefiftyfive.domain.Airplane;
import com.skybayfivefiftyfive.dto.AirplaneDTO;
import com.skybayfivefiftyfive.dto.AirplaneSearchDTO;
import com.skybayfivefiftyfive.dto.AirplanePageDTO;
import com.skybayfivefiftyfive.service.AirplaneService;
import com.skybayfivefiftyfive.dto.common.RequestDTO;
import com.skybayfivefiftyfive.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/airplane")
@RestController
public class AirplaneController {

	private final static Logger logger = LoggerFactory.getLogger(AirplaneController.class);

	@Autowired
	AirplaneService airplaneService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Airplane> getAll() {

		List<Airplane> airplanes = airplaneService.findAll();
		
		return airplanes;	
	}

	@GetMapping(value = "/{airplaneId}")
	@ResponseBody
	public AirplaneDTO getAirplane(@PathVariable Integer airplaneId) {
		
		return (airplaneService.getAirplaneDTOById(airplaneId));
	}

 	@RequestMapping(value = "/addAirplane", method = RequestMethod.POST)
	public ResponseEntity<?> addAirplane(@RequestBody AirplaneDTO airplaneDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = airplaneService.addAirplane(airplaneDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/airplanes")
	public ResponseEntity<AirplanePageDTO> getAirplanes(AirplaneSearchDTO airplaneSearchDTO) {
 
		return airplaneService.getAirplanes(airplaneSearchDTO);
	}	

	@RequestMapping(value = "/updateAirplane", method = RequestMethod.POST)
	public ResponseEntity<?> updateAirplane(@RequestBody AirplaneDTO airplaneDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = airplaneService.updateAirplane(airplaneDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
