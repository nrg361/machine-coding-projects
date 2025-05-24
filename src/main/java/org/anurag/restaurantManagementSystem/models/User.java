package org.anurag.restaurantManagementSystem.models;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class User {
    private String userId;
    private String phone;
    private String name;
    private List<Order> orders;
    private String gender;
    private String pincode;
}
