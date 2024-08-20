package com.mc.user.service.UserService.services;

import com.mc.user.service.UserService.dtos.UserDto;
import com.mc.user.service.UserService.entities.User;

import java.net.URISyntaxException;
import java.util.List;

public interface UserService {

    //create user
    User saveUser(User user);

    //get all users
    List<User> getAllUsers();

    //get single user
    UserDto getUser(String userId) throws URISyntaxException;
}
