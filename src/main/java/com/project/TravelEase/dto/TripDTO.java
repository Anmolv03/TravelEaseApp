package com.project.TravelEase.dto;


import lombok.Data;

@Data
public class TripDTO {
	
	private Long id;
	private String destination;
	private String startDate;
	private String endDate;
	private Double price;
	private Long hotelId;
	
}
