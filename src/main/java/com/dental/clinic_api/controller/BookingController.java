package com.dental.clinic_api.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

import com.dental.clinic_api.model.Booking;
import com.dental.clinic_api.repository.BookingRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingRepository repo;

    public BookingController(BookingRepository repo) {
        this.repo = repo;
    }

    // GET all bookings
    @GetMapping
    public List<Booking> getAll() {
        return repo.findAll();
    }

    // POST new booking
    @PostMapping
    public Booking create(@RequestBody Booking booking) {
        return repo.save(booking);
    }
}