package com.skybayfivefiftyfive.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;



@Entity
@Table(name="flights")
@Getter @Setter @NoArgsConstructor
public class Flight {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="flight_id")
	private Integer flightId;
    
  	@Column(name="airline")
	private String airline;
    
  	@Column(name="flight_number")
	private String flightNumber;
    
  	@Column(name="departure_airport")
	private String departureAirport;
    
  	@Column(name="arrival_airport")
	private String arrivalAirport;
    
  	@Column(name="departure_time")
	private Date departureTime;
    
  	@Column(name="arrival_time")
	private Date arrivalTime;
    
	




}
