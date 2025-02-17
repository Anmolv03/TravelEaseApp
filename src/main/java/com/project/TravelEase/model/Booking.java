package com.project.TravelEase.model;
 
import java.time.LocalDate;

import jakarta.persistence.CascadeType;
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
public class Booking {
 
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_seq")
    @SequenceGenerator(name = "booking_seq", sequenceName = "booking_sequence", allocationSize = 1)
    private Long id;
    private String bookingDate;
    private int nights;
    private double totalAmount;
 
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
 
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;
 
    
    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;
    
}
