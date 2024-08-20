package com.mc.user.service.UserService.dtos;

import com.mc.user.service.UserService.entities.Rating;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String userId;
    private String name;
    private String email;
    private String about;
    private List<Rating> ratings = new ArrayList<>();
}
