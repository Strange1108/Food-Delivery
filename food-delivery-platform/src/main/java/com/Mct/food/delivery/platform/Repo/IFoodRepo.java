package com.Mct.food.delivery.platform.Repo;

import com.Mct.food.delivery.platform.Model.Enums.Cuisine;
import com.Mct.food.delivery.platform.Model.Enums.FCategory;
import com.Mct.food.delivery.platform.Model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFoodRepo extends JpaRepository<Food,Integer> {


    Food findFirstByFoodName(String foodName);

    List<Food> findByFoodNameAndFoodCategoryAndFoodCuisineAndFoodPrice(String foodName, FCategory foodCategory, Cuisine foodCuisine, Double foodPrice);
}
