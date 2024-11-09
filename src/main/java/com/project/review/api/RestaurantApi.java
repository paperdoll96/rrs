package com.project.review.api;

import com.project.review.api.request.CreateAndEditRestaurantRequest;
import com.project.review.api.request.CreateAndEditRestaurantRequestMenu;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestaurantApi {

    @GetMapping("/restaurants")
    public String getRestaurants() {
        return "This is getRestaurants";
    }

    @GetMapping("/restaurant/{restaurantId}")
    public String getRestaurant(@PathVariable Long restaurantId) {
        return "This is getRestaurant, " + restaurantId;
    }

    @PostMapping("/restaurant") // 맛집 등록 API
    public String createRestaurant(
            @RequestBody CreateAndEditRestaurantRequest request
            ) {
        return "This is createRestaurant, name = " + request.getName() + " address=" + request.getAddress()
                + ", menu[0].name= " + request.getMenus().get(0).getName() + ", menu[0].price = " + request.getMenus().get(0).getPrice();
    }

    @PutMapping("/restaurant/{restaurantId}")
    public String editRestaurant(
            @PathVariable Long restaurantId,
            @RequestBody CreateAndEditRestaurantRequest request

    ) {
        return "This is edit Restaurant, " + restaurantId + "name = " + request.getName() + ", address = " + request.getAddress();
    }

    @DeleteMapping("/restaurant/{restaurantId}")
    public String deleteRestaurant(@PathVariable Long restaurantId) {
        return "This is delete Restaurant, " + restaurantId;
    }
}
