package com.project.TravelEase.controller;



import com.project.TravelEase.model.Booking;

import com.project.TravelEase.model.Hotel;

import com.project.TravelEase.model.Trip;

import com.project.TravelEase.model.User;
import com.project.TravelEase.repository.BookingRepository;
import com.project.TravelEase.repository.UserRepository;
import com.project.TravelEase.service.BookingService;

import com.project.TravelEase.service.HotelService;

import com.project.TravelEase.service.TripService;

import com.project.TravelEase.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@Controller

@RequestMapping("/bookings")

public class BookingController {



  @Autowired

  private BookingService bookingService;



  @Autowired

  private HotelService hotelService;



  @Autowired

  private TripService tripService;



  @Autowired

  private UserService userService;
  
  @Autowired
  private BookingRepository bookingRepository;



  // Booking a trip with hotel

  @PostMapping("/create")

  public String createBooking(@RequestParam("tripId") Long tripId,

                @RequestParam("hotelId") Long hotelId
                ,HttpSession  session) {
	      User user=(User) session.getAttribute("user");
          Trip trip  = tripService.getTripById(tripId);
          Hotel hotel = hotelService.getHotelById(hotelId);

    System.out.println(trip);
    System.out.println(hotel);
    System.out.println(user);

    if (trip != null && hotel != null && user != null) {

      Booking booking = new Booking();

      booking.setTrip(trip);

      booking.setHotel(hotel);

      booking.setUser(user);

      booking.setBookingDate("2025-02-04"); // This can be dynamic

      booking.setNights(3); // This can be dynamic or selected by the user

      booking.setTotalAmount(hotel.getPricePerNight() * 3); // Calculate based on nights

      hotel.setBookedRooms(hotel.getBookedRooms() + 1);

      bookingService.createBooking(booking);

      hotelService.updateHotel( hotelId ,hotel); // Ensure the hotel is updated



      return "redirect:/bookings/confirmation/" + booking.getId();

    }



    return "error"; 

  }



  // Booking Confirmation

  @GetMapping("/confirmation/{id}")

  public String confirmBooking(@PathVariable Long id, Model model) {

    Booking booking = bookingService.getBookingById(id);

    model.addAttribute("booking", booking);

    return "booking-confirmation";

  }
   
  @GetMapping("/mybooking")
  public String mybooking(Model model,HttpSession httpSession) {
	  
	  User user= (User) httpSession.getAttribute("user");
	  List<Booking> bookings=bookingService.getBookingByUserId(user.getId());
	  System.out.println(bookings);
	  model.addAttribute("bookings",bookings);
	  return "user-bookings";
  }
  
  

  }

