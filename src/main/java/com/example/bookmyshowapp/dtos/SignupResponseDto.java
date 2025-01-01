package com.example.bookmyshowapp.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupResponseDto {
    private Long userId;
    private ResponseStatus responseStatus;
}
