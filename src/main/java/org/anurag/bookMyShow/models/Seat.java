package org.anurag.bookMyShow.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class Seat {
    private String seatNumber;
    private String showId;
    @Setter
    private Boolean availability;
    @Setter
    private Boolean seatLocked;
}
