package com.project.TravelEase.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
public class Trip {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trip_seq")
	@SequenceGenerator(name = "trip_seq", sequenceName = "trip_sequence", allocationSize = 1)
	private Long id;
	private String destination;
	private String startDate;
	private String endDate;
	private Double price;

	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Hotel> hotels=new ArrayList<>();

}