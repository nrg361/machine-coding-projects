package org.anurag.zomatoApp.service;

import lombok.AllArgsConstructor;
import org.anurag.zomatoApp.models.Seat;
import org.anurag.zomatoApp.models.Show;
import org.anurag.zomatoApp.utils.CommonUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SeatService {

    private ShowService showService;

    public void createSeats(Integer numberOfSeats, String showId){
        Show currentShow = showService.getShowDetails(showId);
        if(Objects.isNull(currentShow))
            return;
        for(int i=0;i<numberOfSeats;i++){
            Seat seat = Seat.builder()
                    .seatNumber(CommonUtils.getUniqueId()).showId(currentShow.getShowId())
                    .seatLocked(Boolean.FALSE)
                    .availability(Boolean.TRUE).build();
            currentShow.getSeats().add(seat);
        }
    }

    public Boolean bookSeats(List<Seat> seats){
        seats.forEach(seat -> seat.setAvailability(Boolean.FALSE));
        return true;
    }

    public Boolean cancelBookedSeats(List<Seat> seats){
        seats.forEach(seat -> seat.setAvailability(Boolean.TRUE));
        return true;
    }

    public List<Seat> getAvailableSeats(String showId){
        Show currentShow = showService.getShowDetails(showId);
        if(Objects.isNull(currentShow))
            return null;
        return currentShow.getSeats().stream().filter(seat -> seat.getAvailability() && !seat.getSeatLocked())
                .collect(Collectors.toList());
    }
}
