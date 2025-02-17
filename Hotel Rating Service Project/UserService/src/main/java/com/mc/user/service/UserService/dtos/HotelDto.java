package com.mc.user.service.UserService.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDto {
    private String id;
    private String name;
    private String location;
    private String about;
}
