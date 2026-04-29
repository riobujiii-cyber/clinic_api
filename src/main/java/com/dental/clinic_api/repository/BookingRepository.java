package com.dental.clinic_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dental.clinic_api.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}