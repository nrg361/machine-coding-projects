package org.anurag.restaurantManagementSystem.models;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class RestaurantOwner {
    private String id;
    private List<String> restaurantIds;
}
