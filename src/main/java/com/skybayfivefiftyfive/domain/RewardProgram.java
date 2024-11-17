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
@Table(name="reward_programs")
@Getter @Setter @NoArgsConstructor
public class RewardProgram {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="reward_program_id")
	private Integer rewardProgramId;
    
  	@Column(name="name")
	private String name;
    
  	@Column(name="points_per_mile")
	private double pointsPerMile;
    
	




}
