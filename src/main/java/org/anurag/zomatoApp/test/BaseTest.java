package org.anurag.zomatoApp.test;

import org.anurag.zomatoApp.controller.AdminController;
import org.anurag.zomatoApp.controller.CustomerController;
import org.anurag.zomatoApp.models.UserType;
import org.anurag.zomatoApp.service.*;

import java.util.ArrayList;
import java.util.HashMap;

public class BaseTest {
    protected AdminController adminController;
    protected CustomerController customerController;

    protected void setupControllers(){
        UserService userService = new UserService(new ArrayList<>());
        TheatreService theatreService = new TheatreService(new ArrayList<>());
        SeatLockService seatLockService = new SeatLockService(new HashMap<>());
        ShowService showService = new ShowService(theatreService, new ArrayList<>());
        SeatService seatService = new SeatService(showService);
        PaymentsService paymentsService = new PaymentsService();
        BookingService bookingService = new BookingService(paymentsService, seatLockService, seatService, new ArrayList<>());

        adminController = new AdminController(showService, theatreService, seatService, userService);
        customerController = new CustomerController(bookingService, seatService, theatreService, showService);
    }

    protected String createTheatre(String theatreName){
        return adminController.createTheatre(theatreName);
    }

    protected String createShow(String showName, String theatreId){
        return adminController.createShow(showName, theatreId);
    }

    protected void createSeats(String showId, Integer numberOfSeats){
        adminController.createSeats(showId, numberOfSeats);
    }

    protected String createUser(String name, UserType userType){
        return adminController.createUser(name, userType);
    }
}
