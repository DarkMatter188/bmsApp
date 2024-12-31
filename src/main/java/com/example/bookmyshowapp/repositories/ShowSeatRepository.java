package com.example.bookmyshowapp.repositories;

import com.example.bookmyshowapp.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    //select * from showseat where id in (1,2,3);
    List<ShowSeat> findAllById(Iterable<Long> showSeatIds);

    //Update + Insert here only update ops
    @Override
    ShowSeat save(ShowSeat showSeat);
}
