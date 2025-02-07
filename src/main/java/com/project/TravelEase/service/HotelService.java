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
 
    // Get all hotels
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
 
    // Get a hotel by ID
    public Hotel getHotelById(Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        return hotel.orElse(null); // Return hotel if found, otherwise return null
    }
 
    // Create or update a hotel
    public Hotel saveHotel(Hotel hotel) {
    	hotel.setRoomsAvailable(hotel.getTotalRooms()-hotel.getBookedRooms());
        return hotelRepository.save(hotel);
    }
 
    // Update an existing hotel
    public Hotel updateHotel(Long id, Hotel hotel) {
        Hotel existingHotel = getHotelById(id);
        if (existingHotel != null) {
            existingHotel.setName(hotel.getName());
            existingHotel.setAddress(hotel.getAddress());
            existingHotel.setPricePerNight(hotel.getPricePerNight());
            existingHotel.setTotalRooms(hotel.getTotalRooms());
            existingHotel.setBookedRooms(hotel.getBookedRooms());
            return hotelRepository.save(existingHotel); // Save updated hotel
        }
        return null; // If hotel not found, return null
    }
 
    // Delete a hotel by ID
    public boolean deleteHotel(Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (hotel.isPresent()) {
            hotelRepository.delete(hotel.get());
            return true; // Hotel deleted successfully
        }
        return false; // Hotel not found
    }
 
    // Calculate rooms available
    public int getRoomsAvailable(int totalRooms, int bookedRooms) {
        return totalRooms - bookedRooms; // Available rooms = total rooms - booked rooms
    }
 
    // Search hotels by city (this can be added to your HotelRepository as a custom query)
    public List<Hotel> searchHotelsByCity(String city) {
        return hotelRepository.findByCity(city);
    }
 
    // Search hotels by price range (this can be added to your HotelRepository as a custom query)
    public List<Hotel> searchHotelsByPriceRange(Double minPrice, Double maxPrice) {
        return hotelRepository.findByPriceRange(minPrice, maxPrice);
    }
 
    // Get a list of available hotels (filter hotels based on rooms available)
    public List<Hotel> getAvailableHotels() {
        return hotelRepository.findAvailableHotels();
    }
    





  }
