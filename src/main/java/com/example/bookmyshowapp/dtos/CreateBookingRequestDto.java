package com.example.bookmyshowapp.dtos;

import com.example.bookmyshowapp.models.ShowSeat;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateBookingRequestDto {
    private Long userId;
    private Long showId;
    private List<Long> showSeatId;
}