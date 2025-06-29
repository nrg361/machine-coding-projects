package org.anurag.restaurantManagementSystem.service;

import lombok.AllArgsConstructor;
import org.anurag.restaurantManagementSystem.models.Order;
import org.anurag.restaurantManagementSystem.models.Restaurant;
import org.anurag.restaurantManagementSystem.models.Review;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class RestaurantService {
    List<Restaurant> restaurants;

    public String placeOrder(Order order){
        Restaurant restaurant = restaurants.stream().filter(restaurant1 -> restaurant1.getId().equals(order.getRestaurantId()))
                .findFirst().orElse(null);
        restaurant.getRestaurantOrders().add(order);
        return order.getOrderId();
    }

    public List<Restaurant> getRestaurants(String pincode, String criteria){
        List<Restaurant> possibleRestaurants = restaurants.stream()
                .filter(restaurant -> restaurant.getPincodes().contains(pincode)).collect(Collectors.toList());

        switch (criteria.toLowerCase()) {
            case "price":
                return sortBasedOnPrice(possibleRestaurants);
            case "rating":
                return sortBasedOnRating(possibleRestaurants);
            default:
                return Collections.emptyList();
        }
    }

    public void addRating(Review review){
        Restaurant currentRestaurant = restaurants.stream().filter(restaurant -> restaurant.getId().equals(review.getRestaurantId()))
                .findFirst().orElse(null);

        currentRestaurant.getReviews().add(review);
        updateRating(currentRestaurant);
    }

    private void updateRating(Restaurant restaurant){
        int totalRating = restaurant.getReviews()
                .stream()
                .map(review -> review.getRating())
                .reduce(0, (a, b) -> a + b);
        restaurant.setRating((double) totalRating / restaurant.getReviews().size());
    }

    private List<Restaurant> sortBasedOnPrice(List<Restaurant> restaurantList){
        restaurantList.sort((restaurant1, restaurant2) -> {
            if(restaurant1.getRestaurantFood().getPrice() < restaurant2.getRestaurantFood().getPrice())
                return -1;
            else if(restaurant1.getRestaurantFood().getPrice() > restaurant2.getRestaurantFood().getPrice())
                return 1;
            else
                return 0;
        });
        return restaurantList;
    }

    private List<Restaurant> sortBasedOnRating(List<Restaurant> restaurantList){
        restaurantList.sort((restaurant1, restaurant2) -> {
            if(restaurant1.getRating() > restaurant2.getRating())
                return -1;
            else if(restaurant1.getRating() < restaurant2.getRating())
                return 1;
            else
                return 0;
        });
        return restaurantList;
    }

    public Restaurant getRestaurant(String restaurantId){
        return restaurants.stream().filter(restaurant -> restaurant.getId().equals(restaurantId))
                .findFirst().orElse(null);
    }

    public void increaseQuantity(String restaurantId, Integer quantity){
        Restaurant restaurant = getRestaurant(restaurantId);
        Integer updatedQuantity = restaurant.getRestaurantFood().getQuantity() + quantity;
        restaurant.getRestaurantFood().setQuantity(updatedQuantity);
    }

    public String addRestaurant(Restaurant restaurant){
        restaurants.add(restaurant);
        return restaurant.getId();
    }
}
