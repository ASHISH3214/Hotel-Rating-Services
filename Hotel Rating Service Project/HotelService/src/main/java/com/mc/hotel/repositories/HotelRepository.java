package com.mc.hotel.repositories;

import com.mc.hotel.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, String> {

    Optional<Hotel>findByName(String name);
}
