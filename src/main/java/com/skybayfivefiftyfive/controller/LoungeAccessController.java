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

import com.skybayfivefiftyfive.domain.LoungeAccess;
import com.skybayfivefiftyfive.dto.LoungeAccessDTO;
import com.skybayfivefiftyfive.dto.LoungeAccessSearchDTO;
import com.skybayfivefiftyfive.dto.LoungeAccessPageDTO;
import com.skybayfivefiftyfive.service.LoungeAccessService;
import com.skybayfivefiftyfive.dto.common.RequestDTO;
import com.skybayfivefiftyfive.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/loungeAccess")
@RestController
public class LoungeAccessController {

	private final static Logger logger = LoggerFactory.getLogger(LoungeAccessController.class);

	@Autowired
	LoungeAccessService loungeAccessService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<LoungeAccess> getAll() {

		List<LoungeAccess> loungeAccesss = loungeAccessService.findAll();
		
		return loungeAccesss;	
	}

	@GetMapping(value = "/{loungeAccessId}")
	@ResponseBody
	public LoungeAccessDTO getLoungeAccess(@PathVariable Integer loungeAccessId) {
		
		return (loungeAccessService.getLoungeAccessDTOById(loungeAccessId));
	}

 	@RequestMapping(value = "/addLoungeAccess", method = RequestMethod.POST)
	public ResponseEntity<?> addLoungeAccess(@RequestBody LoungeAccessDTO loungeAccessDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = loungeAccessService.addLoungeAccess(loungeAccessDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/loungeAccesss")
	public ResponseEntity<LoungeAccessPageDTO> getLoungeAccesss(LoungeAccessSearchDTO loungeAccessSearchDTO) {
 
		return loungeAccessService.getLoungeAccesss(loungeAccessSearchDTO);
	}	

	@RequestMapping(value = "/updateLoungeAccess", method = RequestMethod.POST)
	public ResponseEntity<?> updateLoungeAccess(@RequestBody LoungeAccessDTO loungeAccessDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = loungeAccessService.updateLoungeAccess(loungeAccessDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
