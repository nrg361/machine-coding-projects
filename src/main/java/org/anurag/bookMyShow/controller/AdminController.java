package org.anurag.bookMyShow.controller;

import lombok.AllArgsConstructor;
import org.anurag.bookMyShow.models.UserType;
import org.anurag.bookMyShow.service.SeatService;
import org.anurag.bookMyShow.service.ShowService;
import org.anurag.bookMyShow.service.TheatreService;
import org.anurag.bookMyShow.service.UserService;

@AllArgsConstructor
public class AdminController {
    ShowService showService;
    TheatreService theatreService;
    SeatService seatService;
    UserService userService;

    public String createTheatre(String theatreName){
        return theatreService.createTheatre(theatreName);
    }

    public String createShow(String showName, String theatreId){
        return showService.createShow(showName, theatreId);
    }

    public void createSeats(String showId, Integer numberOfSeats){
        seatService.createSeats(numberOfSeats, showId);
    }

    public String createUser(String name, UserType userType){
        return userService.createUser(name, userType);
    }
}
