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
    
    
 

    @GetMapping("/hotel-list")
    public String getAllHotels(Model model) {
        List<Hotel> hotels = hotelService.getAllHotels();

 
        model.addAttribute("hotels", hotels);
        return "hotel-list";
    }
 
 
    @GetMapping("/{id}")
    public String getHotelById(@PathVariable Long id, Model model) {
        Hotel hotel = hotelService.getHotelById(id);

        model.addAttribute("hotel", hotel);
        return "hotel-details";
    }
 
    
    @GetMapping("/admin/create")
    public String showHotelForm(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "hotel-form";
    }
 
   
    @PostMapping("/admin/create")
    public String createHotel(@ModelAttribute Hotel hotel) {
        hotelService.saveHotel(hotel);
        return "redirect:/hotels/hotel-list";
    }
}