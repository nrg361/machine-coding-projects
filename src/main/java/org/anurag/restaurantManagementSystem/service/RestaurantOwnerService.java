package org.anurag.restaurantManagementSystem.service;

import lombok.AllArgsConstructor;
import org.anurag.restaurantManagementSystem.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class RestaurantOwnerService {
    private RestaurantService restaurantService;
    private List<RestaurantOwner> restaurantOwners;

    public String registerRestaurant(String foodName, List<String> pincodes, Integer foodPrice, Integer quantity){
        String restaurantId = UUID.randomUUID().toString();
        RestaurantFood restaurantFood = RestaurantFood.builder()
                .id(UUID.randomUUID().toString())
                .restaurantId(restaurantId)
                .price(foodPrice).quantity(quantity)
                .name(foodName)
                .build();

        Restaurant restaurant = Restaurant.builder()
                .restaurantOrders(new ArrayList<>())
                .id(UUID.randomUUID().toString()).reviews(new ArrayList<>())
                .rating(0d).pincodes(pincodes)
                .restaurantFood(restaurantFood)
                .build();

        return restaurantService.addRestaurant(restaurant);
    }

    public void increaseFoodQuantity(String restaurantId, Integer quantity){
        restaurantService.increaseQuantity(restaurantId, quantity);
    }
}
