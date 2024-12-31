package com.example.bookmyshowapp.controllers;

import com.example.bookmyshowapp.dtos.CreateBookingRequestDto;
import com.example.bookmyshowapp.dtos.CreateBookingResponseDto;
import com.example.bookmyshowapp.dtos.ResponseStatus;
import com.example.bookmyshowapp.exceptions.SeatNotFoundException;
import com.example.bookmyshowapp.exceptions.ShowNotFoundException;
import com.example.bookmyshowapp.exceptions.UserNotFoundException;
import com.example.bookmyshowapp.models.Booking;
import com.example.bookmyshowapp.services.BookingService;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }
    public CreateBookingResponseDto createBooking(CreateBookingRequestDto requestDto){
//        bookingService.createBooking();
        CreateBookingResponseDto responseDto = new CreateBookingResponseDto();

        try {
            Booking booking = bookingService.createBooking(requestDto.getUserId(),
                    requestDto.getShowSeatIds(),
                    requestDto.getShowId());

            responseDto.setBookingId(booking.getId());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
