package com.mc.user.service.UserService.external.services;

import com.mc.user.service.UserService.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @GetMapping("/api/rating/userid/{id}")
    ResponseEntity<List<Rating>> getRating(@PathVariable("id") String userId);

    @PostMapping("/api/rating")
    ResponseEntity<Rating> createRating(@RequestBody Rating rating);

}
