package com.dental.clinic_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dental.clinic_api.model.Booking;
import com.dental.clinic_api.repository.BookingRepository;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private BookingRepository bookingRepository;

    // GET ALL BOOKINGS
    @GetMapping("/bookings")
    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }

    // UPDATE STATUS
    @PutMapping("/booking/{id}")
    public String updateStatus(@PathVariable Long id, @RequestParam String status){

        Booking booking = bookingRepository.findById(id).orElse(null);

        if(booking == null){
            return "Booking not found";
        }

        booking.setStatus(status);
        bookingRepository.save(booking);

        return "Updated";
    }
    
    @GetMapping("")
public String adminHome(){
    return "Admin API is working";
}
}