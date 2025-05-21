package org.anurag.zomatoApp.service;

import lombok.AllArgsConstructor;
import org.anurag.zomatoApp.models.Seat;
import org.anurag.zomatoApp.utils.CommonUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class SeatLockService {
    Map<String, Boolean> lockedSeats;

    public synchronized Boolean lockSeats(List<Seat> seats){
        seats.forEach(seat -> {
            List<String> entities = Arrays.asList(seat.getSeatNumber(), seat.getShowId());
            String hash = CommonUtils.createHash(entities);
            lockedSeats.put(hash, Boolean.TRUE);
            seat.setSeatLocked(Boolean.TRUE);
        });
        return true;
    }

    public synchronized Boolean unlockSeats(List<Seat> seats){
        seats.forEach(seat -> {
            List<String> entities = Arrays.asList(seat.getSeatNumber(), seat.getShowId());
            String hash = CommonUtils.createHash(entities);
            lockedSeats.remove(hash);
            seat.setSeatLocked(Boolean.FALSE);
        });
        return true;
    }
}
