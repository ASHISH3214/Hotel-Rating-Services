package com.mc.rating.services;

import com.mc.rating.entities.Rating;

import java.util.List;

public interface RatingService {
    Rating create(Rating rating);
    List<Rating> getRatings();
    List<Rating> getRatingsByUser(String userId);
    List<Rating> getRatingsByHotel(String hotelId);
}
