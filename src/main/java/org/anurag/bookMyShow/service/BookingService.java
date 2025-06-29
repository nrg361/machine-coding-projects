package org.anurag.bookMyShow.service;

import lombok.AllArgsConstructor;
import org.anurag.bookMyShow.models.Booking;
import org.anurag.bookMyShow.utils.CommonUtils;

import java.util.List;

@AllArgsConstructor
public class BookingService {

    PaymentsService paymentsService;
    SeatLockService seatLockService;
    SeatService seatService;

    List<Booking> bookingList;

    public String createBooking(Booking newBooking){
        newBooking.setBookingId(CommonUtils.getUniqueId());
        seatLockService.lockSeats(newBooking.getBookedSeats());
        seatService.bookSeats(newBooking.getBookedSeats());
        paymentsService.doPayment(newBooking);
        seatLockService.unlockSeats(newBooking.getBookedSeats());
        return newBooking.getBookingId();
    }

    public Boolean cancelBooking(Booking booking){
        seatLockService.lockSeats(booking.getBookedSeats());
        seatService.cancelBookedSeats(booking.getBookedSeats());
        seatLockService.unlockSeats(booking.getBookedSeats());
        return true;
    }

    public Booking getBookingDetails(String bookingId){
        return bookingList.stream().filter(booking -> booking.getBookingId().equals(bookingId)).findFirst().orElse(null);
    }
}
