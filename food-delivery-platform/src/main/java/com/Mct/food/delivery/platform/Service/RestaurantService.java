package com.Mct.food.delivery.platform.Service;

import com.Mct.food.delivery.platform.Model.Restaurant;
import com.Mct.food.delivery.platform.Repo.IRestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    IRestaurantRepo restaurantRepo;

    public List<Restaurant> getAllRestaurants() {
       return restaurantRepo.findAll();
    }

    public Restaurant getRestaurant(Integer restaurantId) {
        return restaurantRepo.findById(restaurantId).orElseThrow();
    }

    public boolean checkByName(String restaurantName) {
        Restaurant restaurant= restaurantRepo.findByRestaurantName(restaurantName);
        if (restaurant!=null){
            return true;
        }
        return false;
    }

    public boolean isAlready(String restaurantEmail) {
        Restaurant restaurant= restaurantRepo.findByRestaurantEmail(restaurantEmail);
        if (restaurant!=null){
            return true;
        }
        return false;
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurantRepo.save(restaurant);
    }
}
