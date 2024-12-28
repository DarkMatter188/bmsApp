package com.example.bookmyshowapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{
    private int rowNum;
    private int colNum;
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;
}
