package org.anurag.restaurantManagementSystem.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Food {
    private String id;
    private String name;
    private Integer price;
    private String restaurantId;
    @Setter
    private Integer quantity;
}
