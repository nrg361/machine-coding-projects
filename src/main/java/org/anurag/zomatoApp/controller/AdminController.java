package org.anurag.zomatoApp.controller;

import lombok.AllArgsConstructor;
import org.anurag.zomatoApp.models.Show;
import org.anurag.zomatoApp.models.Theatre;
import org.anurag.zomatoApp.models.UserType;
import org.anurag.zomatoApp.service.SeatService;
import org.anurag.zomatoApp.service.ShowService;
import org.anurag.zomatoApp.service.TheatreService;
import org.anurag.zomatoApp.service.UserService;

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
