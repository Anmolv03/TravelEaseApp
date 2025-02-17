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
    
    




      @GetMapping("/search")

      public String listTrips(@RequestParam(value = "destination", required = false) String destination, Model model) {

        List<Trip> trips = (destination != null && !destination.isEmpty()) 

                  ? tripService.searchTrips(destination) 

                  : tripService.getAllTrips();

        model.addAttribute("trips", trips);

        return "search";

      }
 

    @GetMapping("/trip-list")
    public String getAllTrips(Model model) {
        List<Trip> trips = tripService.getAllTrips();
        model.addAttribute("trips", trips);
        return "trip-list";
    }
 

    @GetMapping("/trip-details/{id}")
    public String getTripById(@PathVariable Long id, Model model) {
        Trip trip = tripService.getTripById(id);
        if (trip == null) {
            return "error";
        }
        model.addAttribute("trip", trip);
        return "trip-details";
    }
 
   
    @GetMapping("/admin/create")
    public String showTripForm(Model model ) {
    	
    	List<Hotel> hotels=hotelService.getAllHotels();
    	model.addAttribute("hotels",hotels);
        model.addAttribute("trip", new TripDTO());
        return "trip-form";
    }
 
   
    @PostMapping("/admin/create")
    
    public String createTrip(@ModelAttribute TripDTO tripDTO) {
    	
        tripService.saveTrip(tripDTO);
       
 

        return "redirect:/trips/trip-list";
    }
}