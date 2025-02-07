package com.project.TravelEase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Data
@Entity
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotel_seq")
	@SequenceGenerator(name = "hotel_seq", sequenceName = "hotel_sequence", allocationSize = 1)
	private Long id;
	private String name;
	private String address;
	private Double pricePerNight;
	private int totalRooms;
	private int bookedRooms;
	public int roomsAvailable;

}