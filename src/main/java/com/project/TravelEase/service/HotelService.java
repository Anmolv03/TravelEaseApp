package com.project.TravelEase.service;
 
import com.project.TravelEase.model.Hotel;
import com.project.TravelEase.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List;
import java.util.Optional;
 
@Service
public class HotelService {
 
    @Autowired
    private HotelRepository hotelRepository;
 

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
 

    public Hotel getHotelById(Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        return hotel.orElse(null); 
    }
 

    public Hotel saveHotel(Hotel hotel) {
    	hotel.setRoomsAvailable(hotel.getTotalRooms()-hotel.getBookedRooms());
        return hotelRepository.save(hotel);
    }
 
 
    public Hotel updateHotel(Long id, Hotel hotel) {
        Hotel existingHotel = getHotelById(id);
        if (existingHotel != null) {
            existingHotel.setName(hotel.getName());
            existingHotel.setAddress(hotel.getAddress());
            existingHotel.setPricePerNight(hotel.getPricePerNight());
            existingHotel.setTotalRooms(hotel.getTotalRooms());
            existingHotel.setBookedRooms(hotel.getBookedRooms());
            existingHotel.setRoomsAvailable(hotel.getRoomsAvailable());
            return hotelRepository.save(existingHotel); 
        }
        return null; 
    }
 
  

 

    public int getRoomsAvailable(int totalRooms, int bookedRooms) {
        return totalRooms - bookedRooms;
    }
 


    





  }
