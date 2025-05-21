package org.anurag.zomatoApp.models;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class Show {
    private String showId;
    private String showName;
    private String theatreId;
    private List<Seat> seats;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
