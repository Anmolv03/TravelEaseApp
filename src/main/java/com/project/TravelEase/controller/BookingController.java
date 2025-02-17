package com.project.TravelEase.controller;

import com.project.TravelEase.model.Booking;
import com.project.TravelEase.model.Hotel;
import com.project.TravelEase.model.Trip;
import com.project.TravelEase.model.User;
import com.project.TravelEase.service.BookingService;
import com.project.TravelEase.service.HotelService;
import com.project.TravelEase.service.TripService;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bookings")
public class BookingController {
  @Autowired private BookingService bookingService;

  @Autowired private HotelService hotelService;

  @Autowired private TripService tripService;

  @PostMapping("/create")

  public String createBooking(@RequestParam("tripId") Long tripId,

      @RequestParam("hotelId") Long hotelId, HttpSession session) {
    User user = (User) session.getAttribute("user");
    Trip trip = tripService.getTripById(tripId);
    Hotel hotel = hotelService.getHotelById(hotelId);

    if (trip != null && hotel != null && user != null) {
      Booking booking = new Booking();

      booking.setTrip(trip);

      booking.setHotel(hotel);

      booking.setUser(user);

      booking.setBookingDate(LocalDate.now().toString());

      booking.setNights(3);

      booking.setTotalAmount(hotel.getPricePerNight() * 3);

      bookingService.createBooking(booking);

      return "redirect:/bookings/confirmation/" + booking.getId();
    }

    return "error";
  }

  @GetMapping("/confirmation/{id}")

  public String confirmBooking(@PathVariable Long id, Model model) {
    Booking booking = bookingService.getBookingById(id);

    model.addAttribute("booking", booking);

    return "booking-confirmation";
  }

  @GetMapping("/mybooking")
  public String mybooking(Model model, HttpSession httpSession) {
    User user = (User) httpSession.getAttribute("user");
    List<Booking> bookings = bookingService.getBookingByUserId(user.getId());
    model.addAttribute("bookings", bookings);
    return "user-bookings";
  }
}