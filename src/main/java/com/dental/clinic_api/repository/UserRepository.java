package com.dental.clinic_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dental.clinic_api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
