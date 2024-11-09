package com.project.review.api;

import com.project.review.api.model.RestaurantEntity;
import com.project.review.api.request.CreateAndEditRestaurantRequest;
import com.project.review.api.request.CreateAndEditRestaurantRequestMenu;
import com.project.review.api.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class RestaurantApi {

    private final RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public String getRestaurants() {
        return "This is getRestaurants";
    }

    @GetMapping("/restaurant/{restaurantId}")
    public String getRestaurant(@PathVariable Long restaurantId) {
        return "This is getRestaurant, " + restaurantId;
    }

    @PostMapping("/restaurant") // 맛집 등록 API
    public void createRestaurant(
            @RequestBody CreateAndEditRestaurantRequest request
            ) {
                 restaurantService.createRestaurant(request);
    }

    @PutMapping("/restaurant/{restaurantId}")
    public void editRestaurant(
            @PathVariable Long restaurantId,
            @RequestBody CreateAndEditRestaurantRequest request

    ) {
        restaurantService.editRestaurant(restaurantId, request);
    }

    @DeleteMapping("/restaurant/{restaurantId}")
    public void deleteRestaurant(@PathVariable Long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
    }
}
