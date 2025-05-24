package org.anurag.restaurantManagementSystem.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
public class Restaurant {
    private String id;
    private RestaurantFood restaurantFood;
    private List<Order> restaurantOrders;
    private List<Review> reviews;
    @Setter
    private Double rating;
    private List<String> pincodes;
}
