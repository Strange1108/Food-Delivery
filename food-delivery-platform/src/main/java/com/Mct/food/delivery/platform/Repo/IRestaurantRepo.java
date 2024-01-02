package com.Mct.food.delivery.platform.Repo;

import com.Mct.food.delivery.platform.Model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRestaurantRepo extends JpaRepository<Restaurant, Integer> {
    Restaurant findByRestaurantName(String restaurantName);

    Restaurant findByRestaurantEmail(String restaurantEmail);
}
