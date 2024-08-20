package com.mc.hotel.impl;

import java.net.*;
import com.mc.hotel.entities.Hotel;
import com.mc.hotel.exceptions.ResourceNotFoundException;
import com.mc.hotel.repositories.HotelRepository;
import com.mc.hotel.services.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HotelServicesImpl implements HotelService{

    @Autowired
    private HotelRepository hotelRepository;

    private Logger logger = LoggerFactory.getLogger(HotelServicesImpl.class);
    @Override
    public Hotel create(Hotel hotel) {
        String uuid  = UUID.randomUUID().toString();
        hotel.setName(hotel.getName().toLowerCase());
        hotel.setId(uuid);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel get(String id) {
        return hotelRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Hotel with given id not found "+id));
    }

    @Override
    public Hotel getByName(String name) {
        Hotel hotel = hotelRepository.findByName(name.toLowerCase()).orElseThrow(() ->
                new ResourceNotFoundException("hotel with given name not found "+ name));
        logger.info("hotel entity from database {} "+ hotel);
        return hotel;
    }

    @Override
    public Hotel update(Hotel hotel, String name) {
        Hotel hotelEntity = hotelRepository.findByName(name.toLowerCase()).orElseThrow(() ->
                new ResourceNotFoundException("hotel with diven name not found "+ name));
        logger.info("hotel entity from database {} "+ hotelEntity);
        if(hotelEntity!=null){
            if(!hotel.getName().isEmpty())
                hotelEntity.setName(hotel.getName());
            if(!hotel.getAbout().isEmpty())
                hotelEntity.setAbout(hotel.getAbout());
            if(!hotel.getLocation().isEmpty())
                hotelEntity.setLocation(hotel.getLocation());
        }
        logger.info("hotel entity after update {} "+ hotelEntity);
        return hotelRepository.save(hotelEntity);
    }
}
