package org.anurag.restaurantManagementSystem.Utils;

import org.anurag.restaurantManagementSystem.models.Food;
import org.anurag.restaurantManagementSystem.models.OrderedFood;
import org.anurag.restaurantManagementSystem.models.RestaurantFood;

import java.util.UUID;

public class Utils {
    public static OrderedFood getOrderedFood(RestaurantFood food, Integer quantity){
        OrderedFood orderedFood = OrderedFood.builder()
                .id(UUID.randomUUID().toString()).name(food.getName())
                .price(food.getPrice()).quantity(quantity).restaurantId(food.getRestaurantId())
                .build();
        return orderedFood;
    }
}
