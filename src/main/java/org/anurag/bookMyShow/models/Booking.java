package org.anurag.bookMyShow.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
public class Booking {
    @Setter
    private String bookingId;
    private String userId;
    private String showId;
    private String theatreId;
    private List<Seat> bookedSeats;
}
