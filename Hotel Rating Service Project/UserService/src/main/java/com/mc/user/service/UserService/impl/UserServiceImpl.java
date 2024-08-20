package com.mc.user.service.UserService.impl;

import com.mc.user.service.UserService.dtos.UserDto;
import com.mc.user.service.UserService.entities.Hotel;
import com.mc.user.service.UserService.entities.Rating;
import com.mc.user.service.UserService.entities.User;
import com.mc.user.service.UserService.exceptions.ResourceNotFoundException;
import com.mc.user.service.UserService.external.services.HotelService;
import com.mc.user.service.UserService.external.services.RatingService;
import com.mc.user.service.UserService.repositories.UserRepository;
import com.mc.user.service.UserService.services.UserService;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private ModelMapper modelMapper;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(@NotNull User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        ResponseEntity<Hotel> hotel = hotelService.getHotelbyName(user.getHotelName());
        List<Rating> ratings = new ArrayList<>();
        if(hotel.getStatusCode().equals(HttpStatus.OK)){
            Rating rating = new Rating("",user.getUserId(),hotel.getBody().getId(),
                    user.getRatings().get(0).getRating(), user.getRatings().get(0).getFeedback(), hotel.getBody());
            ratingService.createRating(rating);
            ratings.add(rating);
        }
        user.setRatings(ratings);
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();

        for(User user : userList){
//            ArrayList<Rating> userRatings = restTemplate.getForObject("http://localhost:8083/api/rating/userid/"+u.getUserId(), ArrayList.class);
//            u.setRatings(userRatings);

//            Rating[] userRatings = restTemplate.getForObject("http://Rating-Service/api/rating/userid/"+user.getUserId(), Rating[].class);
            ResponseEntity<List<Rating>> userRatingList = ratingService.getRating(user.getUserId());
            logger.info("response status code: {}", userRatingList.getStatusCode());
            logger.info("{}",userRatingList.getBody());
//            List<Rating> userRatingList = Arrays.stream(userRatings).toList();
            userRatingList.getBody().stream().map(rating -> {
                //http://localhost:8082/api/hotel/all
              //  ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://Hotel-Service/api/hotel/" + rating.getHotelId(), Hotel.class);

                ResponseEntity<Hotel> hotel = hotelService.getHotel(rating.getHotelId());
                logger.info("response status code: {} ",hotel.getStatusCode());
                logger.info("{}",hotel.getBody());

                rating.setHotel(hotel.getBody());
                return rating;
            }).collect(Collectors.toList());

            user.setRatings(userRatingList.getBody());
        }

        return userList;
    }

    @Override
    public UserDto getUser(String userId){
      // URI uri = new URI("http://localhost:8083/api/rating/userid/162f4f09-368f-4c20-a65e-6689639e7182");
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("user not found with given id "+ userId ));
        Rating[] userRatings = restTemplate.getForObject("http://RATING-SERVICE/api/rating/userid/"+user.getUserId(), Rating[].class);
       //ResponseEntity<List<Rating>> userRatingList = ratingService.getRating(user.getUserId());
        //logger.info("response status code: ", userRatingList.getStatusCode());
        //logger.info("{}",userRatingList.getBody());
        List<Rating> userRatingList = Arrays.stream(userRatings).toList();
        userRatingList.stream().map(rating -> {
            //http://localhost:8082/api/hotel/all
    //        ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/api/hotel/" + rating.getHotelId(), Hotel.class);
            ResponseEntity<Hotel> hotel = hotelService.getHotel(rating.getHotelId());
            logger.info("response status code: {} ",hotel.getStatusCode());
            logger.info("{}", hotel.getBody());

            rating.setHotel(hotel.getBody());
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(userRatingList);
        return modelMapper.map(user, UserDto.class);
    }
}
