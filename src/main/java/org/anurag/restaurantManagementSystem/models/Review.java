package org.anurag.restaurantManagementSystem.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Review {
    private String reviewId;
    private String userId;
    private Integer rating;
    private String comment;
    private String restaurantId;
}
