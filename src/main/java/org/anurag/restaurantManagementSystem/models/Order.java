package org.anurag.restaurantManagementSystem.models;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Order {
    private String orderId;
    private String userId;
    private String restaurantId;
    private Integer totalAmout;
    private OrderedFood food;
}
