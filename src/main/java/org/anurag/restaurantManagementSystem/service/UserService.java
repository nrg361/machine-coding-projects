package org.anurag.restaurantManagementSystem.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anurag.restaurantManagementSystem.Utils.Utils;
import org.anurag.restaurantManagementSystem.models.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
public class UserService {
    private RestaurantService restaurantService;
    private List<User> users;

    public String registerUser(String name, String phone, String gender, String pincode){
        Boolean isUserExisting = users.stream().anyMatch(user1 -> user1.getPhone().equals(phone));
        if(isUserExisting){
            log.info("User already exists!");
            return null;
        }
        User user = User.builder()
                .userId(UUID.randomUUID().toString()).phone(phone).name(name)
                .orders(new ArrayList<>()).gender(gender).pincode(pincode).build();
        users.add(user);
        return user.getUserId();
    }

    public void rateRestaurant(String userId, String comment, Integer rating, String restaurantId){
        Review review = Review.builder()
                .reviewId(UUID.randomUUID().toString())
                .userId(userId).comment(comment).rating(rating).restaurantId(restaurantId)
                .build();
        restaurantService.addRating(review);
    }

    public List<Restaurant> getAllRestaurantAvailable(String userId, String criteria){
        User user = gerUser(userId);
        return restaurantService.getRestaurants(user.getPincode(), criteria);
    }

    public String placeOrder(String userId, String restaurantId, Integer quantity){
        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
        Order order = Order.builder()
                .orderId(UUID.randomUUID().toString()).restaurantId(restaurantId)
                .food(Utils.getOrderedFood(restaurant.getRestaurantFood(), quantity))
                .userId(userId).totalAmout(quantity * restaurant.getRestaurantFood().getPrice())
                .build();
        User user = gerUser(userId);
        user.getOrders().add(order);
        return restaurantService.placeOrder(order);
    }

    public User gerUser(String userId){
        return users.stream().filter(user1 -> user1.getUserId().equals(userId))
                .findFirst().orElse(null);
    }
}
