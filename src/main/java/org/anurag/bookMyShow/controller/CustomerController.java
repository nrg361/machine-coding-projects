package org.anurag.bookMyShow.controller;

import lombok.AllArgsConstructor;
import org.anurag.bookMyShow.models.Booking;
import org.anurag.bookMyShow.models.Seat;
import org.anurag.bookMyShow.models.Show;
import org.anurag.bookMyShow.models.Theatre;
import org.anurag.bookMyShow.service.BookingService;
import org.anurag.bookMyShow.service.SeatService;
import org.anurag.bookMyShow.service.ShowService;
import org.anurag.bookMyShow.service.TheatreService;

import java.util.List;

@AllArgsConstructor
public class CustomerController {
    BookingService bookingService;
    SeatService seatService;
    TheatreService theatreService;

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
