package org.anurag.bookMyShow.models;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class User {
    String userId;
    String name;
    List<Booking> bookings;
    UserType userType;
}
