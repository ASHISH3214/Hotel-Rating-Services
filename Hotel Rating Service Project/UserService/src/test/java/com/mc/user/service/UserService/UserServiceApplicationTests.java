package com.mc.user.service.UserService;

import com.mc.user.service.UserService.entities.Hotel;
import com.mc.user.service.UserService.entities.Rating;
import com.mc.user.service.UserService.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

	@Test
	void createRating(){
		Rating rating = Rating.builder().rating(10).userId("").hotelId("").feedback("this is test using feignclient ").build();
		ResponseEntity<Rating> saveRating = ratingService.createRating(rating);

		System.out.println(saveRating);
	}

}
