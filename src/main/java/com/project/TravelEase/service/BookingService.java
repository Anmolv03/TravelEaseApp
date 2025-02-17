package com.project.TravelEase.service;
 

import com.project.TravelEase.model.Booking;
import com.project.TravelEase.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List;
import java.util.Optional;
 
@Service
public class BookingService {
 
    @Autowired
    private BookingRepository bookingRepository;
 

 

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
 

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id " + id));
    }
 

    public Booking createBooking(Booking booking) {


        if (booking.getHotel().getRoomsAvailable() <= 0) {
            throw new RuntimeException("No rooms available in the selected hotel.");
        }
 
       
        double totalAmount = booking.getHotel().getPricePerNight() * booking.getNights();
        booking.setTotalAmount(totalAmount);
 

        booking.getHotel().setBookedRooms(booking.getHotel().getBookedRooms() + 1);
        booking.getHotel().setRoomsAvailable(booking.getHotel().getTotalRooms() - booking.getHotel().getBookedRooms());
 
     
        return bookingRepository.save(booking);
    }
 

 

    
    public List<Booking> getBookingByUserId(long userId) {
    	
    	return bookingRepository.findByUserId(userId);
    	
    }
}