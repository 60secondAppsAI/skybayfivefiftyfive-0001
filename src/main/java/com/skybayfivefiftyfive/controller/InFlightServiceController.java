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

import com.skybayfivefiftyfive.domain.InFlightService;
import com.skybayfivefiftyfive.dto.InFlightServiceDTO;
import com.skybayfivefiftyfive.dto.InFlightServiceSearchDTO;
import com.skybayfivefiftyfive.dto.InFlightServicePageDTO;
import com.skybayfivefiftyfive.service.InFlightServiceService;
import com.skybayfivefiftyfive.dto.common.RequestDTO;
import com.skybayfivefiftyfive.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/inFlightService")
@RestController
public class InFlightServiceController {

	private final static Logger logger = LoggerFactory.getLogger(InFlightServiceController.class);

	@Autowired
	InFlightServiceService inFlightServiceService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<InFlightService> getAll() {

		List<InFlightService> inFlightServices = inFlightServiceService.findAll();
		
		return inFlightServices;	
	}

	@GetMapping(value = "/{inFlightServiceId}")
	@ResponseBody
	public InFlightServiceDTO getInFlightService(@PathVariable Integer inFlightServiceId) {
		
		return (inFlightServiceService.getInFlightServiceDTOById(inFlightServiceId));
	}

 	@RequestMapping(value = "/addInFlightService", method = RequestMethod.POST)
	public ResponseEntity<?> addInFlightService(@RequestBody InFlightServiceDTO inFlightServiceDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = inFlightServiceService.addInFlightService(inFlightServiceDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/inFlightServices")
	public ResponseEntity<InFlightServicePageDTO> getInFlightServices(InFlightServiceSearchDTO inFlightServiceSearchDTO) {
 
		return inFlightServiceService.getInFlightServices(inFlightServiceSearchDTO);
	}	

	@RequestMapping(value = "/updateInFlightService", method = RequestMethod.POST)
	public ResponseEntity<?> updateInFlightService(@RequestBody InFlightServiceDTO inFlightServiceDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = inFlightServiceService.updateInFlightService(inFlightServiceDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
