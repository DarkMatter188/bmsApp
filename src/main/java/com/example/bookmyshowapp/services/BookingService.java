package com.example.bookmyshowapp.services;

import com.example.bookmyshowapp.exceptions.SeatNotFoundException;
import com.example.bookmyshowapp.exceptions.ShowNotFoundException;
import com.example.bookmyshowapp.exceptions.UserNotFoundException;
import com.example.bookmyshowapp.models.*;
import com.example.bookmyshowapp.repositories.BookingRepository;
import com.example.bookmyshowapp.repositories.ShowRepository;
import com.example.bookmyshowapp.repositories.ShowSeatRepository;
import com.example.bookmyshowapp.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    public UserRepository userRepository;
    public ShowRepository showRepository;
    public ShowSeatRepository showSeatRepository;
    public PriceCalculator priceCalculator;
    public BookingRepository bookingRepository;


    public BookingService(UserRepository userRepository,
                            ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository,
                          PriceCalculator priceCalculator,
                          BookingRepository bookingRepository){

        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.priceCalculator = priceCalculator;
        this.bookingRepository = bookingRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking createBooking(Long userId, List<Long> showSeatIds, Long showId) throws UserNotFoundException, ShowNotFoundException, SeatNotFoundException {
        /*
        1. Get the user with the given userId
        2. Get the show with given showId
        3. Get List of showSeats with given showSeatIds
        --------------Take the LOCK ---------------
        4. Check if all seats are available or not.
        5. If not throw exception
        6. If yes, Mark status of all seats as Blocked
        ---------------Release the LOCK --------------
        7. Save changes in DB as well.
        8. Create booking with pending status and save booking obj to DB
        9. Return the booking object
         */

        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User with id: " + userId +" not found!");
        }

        User user = optionalUser.get();

        Optional<Show> optionalShow = showRepository.findById(showId);
        if(optionalShow.isEmpty()){
            throw new ShowNotFoundException("Show with id: " + showId + " not found!");
        }

        Show show = optionalShow.get();

        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);
        //Check if all seats are vacant


//        4. Check if all seats are available or not.
//        5. If not throw exception
//        6. If yes, Mark status of all seats as Blocked
        for(ShowSeat showSeat : showSeats){
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                throw new SeatNotFoundException("Show seat with id: "+ showSeat.getId() + " is not available");
            }
        }
//        7. Save changes in DB as well.
//        8. Create booking with pending status and save booking obj to DB

        for(ShowSeat showSeat : showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            showSeatRepository.save(showSeat);
        }

        //Return booking obj
        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setBookingNumber("A12W123");
        booking.setCreatedAt(new Date());
        booking.setUser(user);
        booking.setShow(show);
        booking.setShowSeats(showSeats);
        booking.setPayments(new ArrayList<>());
        booking.setAmount(priceCalculator.calculatePrice(show, showSeats));

        return bookingRepository.save(booking);
    }
}
