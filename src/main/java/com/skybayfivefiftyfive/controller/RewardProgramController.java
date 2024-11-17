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

import com.skybayfivefiftyfive.domain.RewardProgram;
import com.skybayfivefiftyfive.dto.RewardProgramDTO;
import com.skybayfivefiftyfive.dto.RewardProgramSearchDTO;
import com.skybayfivefiftyfive.dto.RewardProgramPageDTO;
import com.skybayfivefiftyfive.service.RewardProgramService;
import com.skybayfivefiftyfive.dto.common.RequestDTO;
import com.skybayfivefiftyfive.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/rewardProgram")
@RestController
public class RewardProgramController {

	private final static Logger logger = LoggerFactory.getLogger(RewardProgramController.class);

	@Autowired
	RewardProgramService rewardProgramService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<RewardProgram> getAll() {

		List<RewardProgram> rewardPrograms = rewardProgramService.findAll();
		
		return rewardPrograms;	
	}

	@GetMapping(value = "/{rewardProgramId}")
	@ResponseBody
	public RewardProgramDTO getRewardProgram(@PathVariable Integer rewardProgramId) {
		
		return (rewardProgramService.getRewardProgramDTOById(rewardProgramId));
	}

 	@RequestMapping(value = "/addRewardProgram", method = RequestMethod.POST)
	public ResponseEntity<?> addRewardProgram(@RequestBody RewardProgramDTO rewardProgramDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = rewardProgramService.addRewardProgram(rewardProgramDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/rewardPrograms")
	public ResponseEntity<RewardProgramPageDTO> getRewardPrograms(RewardProgramSearchDTO rewardProgramSearchDTO) {
 
		return rewardProgramService.getRewardPrograms(rewardProgramSearchDTO);
	}	

	@RequestMapping(value = "/updateRewardProgram", method = RequestMethod.POST)
	public ResponseEntity<?> updateRewardProgram(@RequestBody RewardProgramDTO rewardProgramDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = rewardProgramService.updateRewardProgram(rewardProgramDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
