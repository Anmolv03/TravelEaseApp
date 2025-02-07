package com.project.TravelEase.repository;

import com.project.TravelEase.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

  @Query("SELECT t FROM Trip t WHERE LOWER(t.destination) LIKE LOWER(CONCAT('%', :destination, '%'))")
  List<Trip> findByDestination(String destination);
  
}
