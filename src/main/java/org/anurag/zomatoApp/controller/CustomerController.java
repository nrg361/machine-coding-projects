package org.anurag.zomatoApp.controller;

import lombok.AllArgsConstructor;
import org.anurag.zomatoApp.models.Booking;
import org.anurag.zomatoApp.models.Seat;
import org.anurag.zomatoApp.models.Show;
import org.anurag.zomatoApp.models.Theatre;
import org.anurag.zomatoApp.service.BookingService;
import org.anurag.zomatoApp.service.SeatService;
import org.anurag.zomatoApp.service.ShowService;
import org.anurag.zomatoApp.service.TheatreService;

import java.util.List;

@AllArgsConstructor
public class CustomerController {
    BookingService bookingService;
    SeatService seatService;
    TheatreService theatreService;
    ShowService showService;

    public List<Theatre> getTheatresList(){
        return theatreService.getTheatreList();
    }

    public List<Show> getShowList(String theatreId){
        return theatreService.getTheatreDetails(theatreId).getShows();
    }

    public List<Seat> getAvailableSeats(String showId){
        return seatService.getAvailableSeats(showId);
    }

    public void createBooking(Booking booking){
        bookingService.createBooking(booking);
    }

    public void cancelBooking(Booking booking){
        bookingService.cancelBooking(booking);
    }
}
