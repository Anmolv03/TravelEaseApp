package com.project.TravelEase.repository;

import com.project.TravelEase.model.Booking;
import com.project.TravelEase.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {


	List<Booking> findByUserId(long userId);
	
}