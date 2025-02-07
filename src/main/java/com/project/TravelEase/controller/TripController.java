package com.project.TravelEase.controller;

import com.project.TravelEase.dto.TripDTO;
import com.project.TravelEase.model.Hotel;
import com.project.TravelEase.model.Trip;
import com.project.TravelEase.model.User;
import com.project.TravelEase.service.HotelService;
import com.project.TravelEase.service.TripService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/trips")
public class TripController {
	
	@Autowired
	private HotelService hotelService; 
 
    @Autowired
    private TripService tripService;
    
    
    public TripController(TripService tripService) {

        this.tripService = tripService;

      }



      @GetMapping("/search")

      public String listTrips(@RequestParam(value = "destination", required = false) String destination, Model model) {

        List<Trip> trips = (destination != null && !destination.isEmpty()) 

                  ? tripService.searchTrips(destination) 

                  : tripService.getAllTrips();

        model.addAttribute("trips", trips);

        return "search";

      }
 
    // Show All Trips
    @GetMapping("/trip-list")
    public String getAllTrips(Model model) {
        List<Trip> trips = tripService.getAllTrips();
        model.addAttribute("trips", trips);
        return "trip-list";
    }
 
    // Show Trip Details
    @GetMapping("/trip-details/{id}")
    public String getTripById(@PathVariable Long id, Model model) {
        Trip trip = tripService.getTripById(id);
        if (trip == null) {
            return "error";
        }
        model.addAttribute("trip", trip);
        return "trip-details";
    }
 
    // Show Trip Creation Form
    @GetMapping("/admin/create")
    public String showTripForm(Model model ) {
    	
    	List<Hotel> hotels=hotelService.getAllHotels();
    	System.out.println("Hotels found :" + hotels.size());
    	model.addAttribute("hotels",hotels);
        model.addAttribute("trip", new TripDTO());
        return "trip-form";
    }
 
    // Handle New Trip Submission
    @PostMapping("/admin/create")
    
    public String createTrip(@ModelAttribute TripDTO tripDTO,Model model) {
    	
        tripService.saveTrip(tripDTO);
       
 
//     model.getAttribute("hotels")
        return "redirect:/trips/trip-list";
    }
}