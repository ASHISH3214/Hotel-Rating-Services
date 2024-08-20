package com.mc.hotel.controllers;

import com.mc.hotel.entities.Hotel;
import com.mc.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        return new ResponseEntity<>(hotelService.create(hotel), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/all")
    public ResponseEntity<List<Hotel>> getAll(){
        return new ResponseEntity<>(hotelService.getAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getSingle(@PathVariable String id){
        return new ResponseEntity<>(hotelService.get(id), HttpStatus.OK);
    }

    @GetMapping("/hotelname")
    public ResponseEntity<Hotel> getHotelByName(@RequestParam("hotelname") String hotelName){
        return new ResponseEntity<>(hotelService.getByName(hotelName), HttpStatus.OK);
    }

    @PutMapping("/update/hotelname")
    public ResponseEntity<Hotel> updateHotelDetails(@RequestParam("hotelname") String hotelName, @RequestBody Hotel hotel){
        return new ResponseEntity<>(hotelService.update(hotel ,hotelName), HttpStatus.OK);
    }

}
