package com.project.TravelEase.controller;
 
import com.project.TravelEase.model.Hotel;
import com.project.TravelEase.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
@Controller
@RequestMapping("/hotels")
public class HotelController {
 
    @Autowired
    private HotelService hotelService;
 
    // Show All Hotels
    @GetMapping("/hotel-list")
    public String getAllHotels(Model model) {
        List<Hotel> hotels = hotelService.getAllHotels();
        
//        hotels.forEach(hotel->System.out.println(hotel.getName()));
//        model.addAttribute("hotels", hotels.size());
        model.addAttribute("hotels", hotels);
        return "hotel-list";
    }
 
    // Show Hotel Details
    @GetMapping("/{id}")
    public String getHotelById(@PathVariable Long id, Model model) {
        Hotel hotel = hotelService.getHotelById(id);
//        if (hotel == null) {
//            return "error";
//        }
        model.addAttribute("hotel", hotel);
        return "hotel-details";
    }
 
    // Show Hotel Creation Form
    @GetMapping("/admin/create")
    public String showHotelForm(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "hotel-form";
    }
 
    // Handle New Hotel Submission
    @PostMapping("/admin/create")
    public String createHotel(@ModelAttribute Hotel hotel) {
        hotelService.saveHotel(hotel);
        return "redirect:/hotels/hotel-list";
    }
}