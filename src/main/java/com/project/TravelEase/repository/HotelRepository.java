package com.project.TravelEase.repository;

import com.project.TravelEase.model.Hotel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
	
	@Query("SELECT h from Hotel h where h.address like %:city%")
	List<Hotel> findByCity(String city);
	
	@Query("SELECT h from Hotel h where h.pricePerNight Between :minPrice AND :maxPrice")
	List<Hotel> findByPriceRange(Double minPrice,Double maxPrice);
	
	@Query("Select h from Hotel h where h.roomsAvailable>0")
	List<Hotel> findAvailableHotels();
	
}