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

import com.skybayfivefiftyfive.domain.Booking;
import com.skybayfivefiftyfive.dto.BookingDTO;
import com.skybayfivefiftyfive.dto.BookingSearchDTO;
import com.skybayfivefiftyfive.dto.BookingPageDTO;
import com.skybayfivefiftyfive.service.BookingService;
import com.skybayfivefiftyfive.dto.common.RequestDTO;
import com.skybayfivefiftyfive.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/booking")
@RestController
public class BookingController {

	private final static Logger logger = LoggerFactory.getLogger(BookingController.class);

	@Autowired
	BookingService bookingService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Booking> getAll() {

		List<Booking> bookings = bookingService.findAll();
		
		return bookings;	
	}

	@GetMapping(value = "/{bookingId}")
	@ResponseBody
	public BookingDTO getBooking(@PathVariable Integer bookingId) {
		
		return (bookingService.getBookingDTOById(bookingId));
	}

 	@RequestMapping(value = "/addBooking", method = RequestMethod.POST)
	public ResponseEntity<?> addBooking(@RequestBody BookingDTO bookingDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = bookingService.addBooking(bookingDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/bookings")
	public ResponseEntity<BookingPageDTO> getBookings(BookingSearchDTO bookingSearchDTO) {
 
		return bookingService.getBookings(bookingSearchDTO);
	}	

	@RequestMapping(value = "/updateBooking", method = RequestMethod.POST)
	public ResponseEntity<?> updateBooking(@RequestBody BookingDTO bookingDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = bookingService.updateBooking(bookingDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
