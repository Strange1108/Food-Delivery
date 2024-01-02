package com.Mct.food.delivery.platform.Service;

import com.Mct.food.delivery.platform.Model.Food;
import com.Mct.food.delivery.platform.Repo.IFoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    @Autowired
    IFoodRepo foodRepo;

    public Food getFood(String foodName) {
       return foodRepo.findFirstByFoodName(foodName);
    }

    public boolean available(String foodName) {
       Food food= foodRepo.findFirstByFoodName(foodName);
        return food != null;
    }

    public boolean checkAlreadyThere(Food food) {
        List<Food> foodList= foodRepo.findByFoodNameAndFoodCategoryAndFoodCuisineAndFoodPrice
                (food.getFoodName(),food.getFoodCategory(),food.getFoodCuisine(),food.getFoodPrice());
        return foodList.size() != 0;
    }

    public void addFood(Food food) {
        foodRepo.save(food);
    }

    public Food getFoodItem(Integer foodId) {
       return foodRepo.findById(foodId).orElseThrow();
    }

    public void deleteFood(Food food) {
        foodRepo.delete(food);
    }

    public List<Food> getAllFoodItems() {
        return foodRepo.findAll();
    }
}
