package com.mc.hotel.services;

import com.mc.hotel.entities.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelService {

    Hotel create(Hotel hotel);

    List<Hotel> getAll();

    Hotel get(String id);

    Hotel getByName(String hotelName);

    Hotel update(Hotel hotel, String hotelName);

}
