package com.project.TravelEase.service;
 
import com.project.TravelEase.dto.TripDTO;
import com.project.TravelEase.dto.TripDTO;
import com.project.TravelEase.model.Hotel;
import com.project.TravelEase.model.Trip;
import com.project.TravelEase.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List;
 
@Service
public class TripService {
	
 @Autowired
    private TripRepository tripRepository;
 
    @Autowired
    private HotelService hotelService;
    
    public TripService(TripRepository tripRepository) {

        this.tripRepository = tripRepository;

      }



      public List<Trip> searchTrips(String destination) {

        return tripRepository.findByDestination(destination);

      }
    
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }
 
    public Trip getTripById(Long id) {
        return tripRepository.findById(id).orElseThrow(() -> new RuntimeException("Trip not found"));
    }
 
 
  
    public Trip saveTrip(TripDTO tripDTO) {
    	Hotel hotel=hotelService.getHotelById(tripDTO.getHotelId());
   	 Trip trip=new Trip();
   	 trip.setPrice(tripDTO.getPrice());
   	 trip.setDestination(tripDTO.getDestination());
   	 trip.setStartDate(tripDTO.getStartDate());
   	 trip.setEndDate(tripDTO.getEndDate());
   	 trip.getHotels().add(hotel);
	    
        return tripRepository.save(trip);
    }
 

 
    
    public long getTotalAvailableRooms(Long tripId) {
        Trip trip = getTripById(tripId);  
 
       
        return trip.getHotels().stream()
                .mapToLong(hotel -> hotelService.getRoomsAvailable(hotel.getTotalRooms(), hotel.getBookedRooms()))
                .sum();
        
    }
 
   
    public double calculateTotalTripPrice(Long tripId) {
        Trip trip = getTripById(tripId);  
 
       
        return trip.getPrice() * getTotalAvailableRooms(tripId);  
    }
}