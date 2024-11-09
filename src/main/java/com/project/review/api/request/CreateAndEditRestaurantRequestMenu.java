package com.project.review.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateAndEditRestaurantRequestMenu {
    private final String name;
    private final int price;
}
