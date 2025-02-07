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
 
    @Autowired
    private HotelService hotelService;
 
    @Autowired
    private TripService tripService;
 
    @Autowired
    private UserService userService;
 
    // Get all bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
 
    // Get booking by ID
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id " + id));
    }
 
    // Create a new booking
    public Booking createBooking(Booking booking) {
        // Validate if the hotel is available
        int availableRooms = hotelService.getRoomsAvailable(booking.getHotel().getTotalRooms(), booking.getHotel().getBookedRooms());
        if (availableRooms <= 0) {
            throw new RuntimeException("No rooms available in the selected hotel.");
        }
 
        // Calculate the total amount
        double totalAmount = booking.getHotel().getPricePerNight() * booking.getNights();
        booking.setTotalAmount(totalAmount);
 
        // Book the room
        booking.getHotel().setBookedRooms(booking.getHotel().getBookedRooms() + 1); // Increment booked rooms
 
        // Save the booking
        return bookingRepository.save(booking);
    }
 
    // Update an existing booking
    public Booking updateBooking(Long id, Booking bookingDetails) {
        Booking existingBooking = getBookingById(id);
 
        // Update fields
        existingBooking.setBookingDate(bookingDetails.getBookingDate());
        existingBooking.setNights(bookingDetails.getNights());
        existingBooking.setTotalAmount(bookingDetails.getTotalAmount());
 
        // Save updated booking
        return bookingRepository.save(existingBooking);
    }
 
    // Delete a booking
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
    
    public List<Booking> getBookingByUserId(long userId) {
    	
    	return bookingRepository.findByUserId(userId);
    	
    }
}