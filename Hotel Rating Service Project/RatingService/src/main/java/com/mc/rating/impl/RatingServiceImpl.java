package com.mc.rating.impl;

import com.mc.rating.entities.Rating;
import com.mc.rating.repositories.RatingRepository;
import com.mc.rating.services.RatingService;
import com.mc.rating.services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private SequenceGeneratorService sequenceService;

    @Override
    public Rating create(Rating rating) {
       // rating.setRatingId(sequenceService.generateSequence(Rating.SEQUENCE_NAME));
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingsByUser(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingsByHotel(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
