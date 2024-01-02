package com.Mct.food.delivery.platform.Controller;


import com.Mct.food.delivery.platform.Model.*;
import com.Mct.food.delivery.platform.Model.Admin;
import com.Mct.food.delivery.platform.Model.Enums.OrderStatus;
import com.Mct.food.delivery.platform.Service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class AdminController {

    @Autowired
    AdminService adminService;
//signup
    @PostMapping("admin/signup")
    public String adminSignUp(@Valid @RequestBody Admin newAdmin)
    {

        return adminService.adminSignUp(newAdmin);
    }



    //sign in
    @PostMapping("admin/signIn/{email}/{password}")
    public String adminSignIn(@PathVariable String email, @PathVariable String password)
    {
        return adminService.adminSignIn(email,password);
    }


    //sign out
    @DeleteMapping("admin/signOut")
    public String adminSignOut(@RequestParam String email, @RequestParam String tokenValue)
    {
        return adminService.adminSignOut(email,tokenValue);
    }
    //adding the restaurant
    @PostMapping("restaurant")
    public String addRestaurant(@RequestParam String email, @RequestParam String tokenValue,@RequestBody Restaurant restaurant){

        return adminService.addRestaurant(email,tokenValue,restaurant);
    }
    /// adding food item
    @PostMapping("foodItem")
    public String addFoodItem(@RequestParam String email, @RequestParam String tokenValue, @RequestBody Food food){
        return adminService.addFoodItem(email,tokenValue,food);
    }
    //getting food item by id
    @GetMapping("foodItem/{foodId}")
    public Food getFoodItem(@RequestParam String email, @RequestParam String tokenValue,@PathVariable Integer foodId){
        return adminService.getFoodItem(email,tokenValue,foodId);
    }

    //deleting food item by id
    @DeleteMapping("foodItem/{foodId}")
    public String deleteFoodItem(@PathVariable Integer foodId, @RequestParam String email, @RequestParam String tokenValue){
        return adminService.deleteFoodItem(email,tokenValue,foodId);
    }

    //updating price of food item
    @PutMapping("price/{price}")
    public String modifyPrice(@RequestParam String email, @RequestParam String tokenValue,@RequestParam Integer foodId,@PathVariable Double price){
        return adminService.changePrice(email,tokenValue,foodId,price);
    }

    //getting all restaurants available
    @GetMapping("restaurants")
    public List<Restaurant> getAllRestaurants(@RequestParam String email, @RequestParam String tokenValue){
        return adminService.getAllRestaurants(email,tokenValue);
    }
    //getting all food items available
    @GetMapping("foodItems")
    public List<Food> getAllFoodItems(@RequestParam String email, @RequestParam String tokenValue){
        return adminService.getAllFoodItems(email,tokenValue);
    }
    //getting all food items of a particular restaurant by restaurant id
    @GetMapping("foodItems/{restaurantId}")
    public List<Food> getAllRestaurantFood(@RequestParam String email, @RequestParam String tokenValue,@PathVariable Integer restaurantId){
        return adminService.getAllRestaurantFood(email,tokenValue,restaurantId);
    }
    //getting all the orders present
    @GetMapping("orders")
    public List<Order> getAllOrders(@RequestParam String email, @RequestParam String tokenValue){
        return adminService.getAllOrders(email, tokenValue);
    }
    //updating order status
    @PutMapping("order/status/{orderId}")
    public String updateOrderStatus(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer orderId, @RequestParam OrderStatus orderStatus){
        return adminService.updateOrderStatus(email,tokenValue,orderId,orderStatus);
    }
    //get all users
    @GetMapping("users")
    public List<User> getAllUser(@RequestParam String email, @RequestParam String tokenValue){
        return adminService.getAllUsers(email,tokenValue);
    }


}
