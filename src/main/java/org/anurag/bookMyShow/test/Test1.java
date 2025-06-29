package org.anurag.bookMyShow.test;

import lombok.extern.slf4j.Slf4j;
import org.anurag.bookMyShow.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class Test1 extends BaseTest{

    @BeforeEach
    void setup(){
        setupControllers();
    }

    @Test
    void test1(){
        String theatreId = createTheatre("Theatre-1");
        String showId = createShow("Show-1", theatreId);
        createSeats(showId, 10);
        String userId = createUser("User-1", UserType.CUSTOMER);

        List<Theatre> theatreList = customerController.getTheatresList();
        List<Show> showList = customerController.getShowList(theatreList.getFirst().getTheatreId());
        List<Seat> availableSeats = customerController.getAvailableSeats(showList.getFirst().getShowId());

        Booking booking = Booking.builder()
                .bookedSeats(Arrays.asList(availableSeats.get(2), availableSeats.get(5)))
                .theatreId(theatreId)
                .showId(showId)
                .userId(userId)
                .build();

        customerController.createBooking(booking);

        customerController.cancelBooking(booking);

        return;
    }
}