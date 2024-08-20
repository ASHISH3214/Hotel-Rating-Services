package com.mc.user.service.UserService.external.services;

import com.mc.user.service.UserService.entities.Hotel;
import com.mc.user.service.UserService.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/api/hotel/{id}")
    ResponseEntity<Hotel> getHotel(@PathVariable("id") String hotelId);

    @GetMapping("/api/hotel/hotelname")
    ResponseEntity<Hotel> getHotelbyName(@RequestParam("hotelname") String hotelName);

}
