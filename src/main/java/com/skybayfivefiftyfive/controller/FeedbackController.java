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

import com.skybayfivefiftyfive.domain.Feedback;
import com.skybayfivefiftyfive.dto.FeedbackDTO;
import com.skybayfivefiftyfive.dto.FeedbackSearchDTO;
import com.skybayfivefiftyfive.dto.FeedbackPageDTO;
import com.skybayfivefiftyfive.service.FeedbackService;
import com.skybayfivefiftyfive.dto.common.RequestDTO;
import com.skybayfivefiftyfive.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/feedback")
@RestController
public class FeedbackController {

	private final static Logger logger = LoggerFactory.getLogger(FeedbackController.class);

	@Autowired
	FeedbackService feedbackService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Feedback> getAll() {

		List<Feedback> feedbacks = feedbackService.findAll();
		
		return feedbacks;	
	}

	@GetMapping(value = "/{feedbackId}")
	@ResponseBody
	public FeedbackDTO getFeedback(@PathVariable Integer feedbackId) {
		
		return (feedbackService.getFeedbackDTOById(feedbackId));
	}

 	@RequestMapping(value = "/addFeedback", method = RequestMethod.POST)
	public ResponseEntity<?> addFeedback(@RequestBody FeedbackDTO feedbackDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = feedbackService.addFeedback(feedbackDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/feedbacks")
	public ResponseEntity<FeedbackPageDTO> getFeedbacks(FeedbackSearchDTO feedbackSearchDTO) {
 
		return feedbackService.getFeedbacks(feedbackSearchDTO);
	}	

	@RequestMapping(value = "/updateFeedback", method = RequestMethod.POST)
	public ResponseEntity<?> updateFeedback(@RequestBody FeedbackDTO feedbackDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = feedbackService.updateFeedback(feedbackDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
