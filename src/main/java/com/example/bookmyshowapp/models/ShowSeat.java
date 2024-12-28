package com.example.bookmyshowapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeat extends BaseModel{

    @ManyToOne
    private Show show;

    @ManyToOne
    private Seat seat;

    @Enumerated(EnumType.ORDINAL)
    private ShowSeatStatus showSeatStatus;
}

/*

    1              1
ShowSeats  ------  Show => M:1
    M              1

Show S
Seat x1 x2 ..     Sx1 Sx2 ...


    1              1
ShowSeats  ------  Seat => M:1
    M              1

Show X, Y
Seats A1 A2 ..
ShowSeats XA1 YA1..

 */