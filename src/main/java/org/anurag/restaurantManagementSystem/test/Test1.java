package org.anurag.restaurantManagementSystem.test;

import org.anurag.restaurantManagementSystem.models.Restaurant;
import org.anurag.restaurantManagementSystem.models.User;
import org.anurag.restaurantManagementSystem.service.RestaurantOwnerService;
import org.anurag.restaurantManagementSystem.service.RestaurantService;
import org.anurag.restaurantManagementSystem.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test1 {

    private UserService userService;
    private RestaurantService restaurantService;
    private RestaurantOwnerService restaurantOwnerService;

    @BeforeEach
    public void initialize(){
        restaurantService = new RestaurantService(new ArrayList<>());
        userService = new UserService(restaurantService, new ArrayList<>());
        restaurantOwnerService = new RestaurantOwnerService(restaurantService, new ArrayList<>());
    }

    @Test
    public void test(){
        String user1 = userService.registerUser("Anurag", "7879924374", "M", "492008");

        String restaurant1 = restaurantOwnerService.registerRestaurant("Idli", Arrays.asList("492008"), 120, 10);

        String restaurant2 = restaurantOwnerService.registerRestaurant("Idli", Arrays.asList("492008"), 1000, 10);

        List<Restaurant> restaurants = userService.getAllRestaurantAvailable(user1, "price");

        userService.placeOrder(user1, restaurant1, 5);

        userService.rateRestaurant(user1, "bad food", 6, restaurant1);

        userService.rateRestaurant(user1, "good food", 5, restaurant2);

        User user = userService.gerUser(user1);

        restaurants = userService.getAllRestaurantAvailable(user1, "rating");
        System.out.println("temp line");
    }
}
