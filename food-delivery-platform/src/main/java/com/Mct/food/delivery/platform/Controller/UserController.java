package com.Mct.food.delivery.platform.Controller;

import com.Mct.food.delivery.platform.Model.*;
import com.Mct.food.delivery.platform.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class UserController {

    @Autowired
    UserService userService;

    //signUp
    @PostMapping("user/signup")
    public String userSignUp(@Valid @RequestBody User newUser)
    {

        Address address =newUser.getAddress();
        return userService.userSignUp(newUser,address);
    }



    //sign in
    @PostMapping("user/signIn/{email}/{password}")
    public String userSignIn(@PathVariable String email, @PathVariable String password)
    {
        return userService.userSignIn(email,password);
    }


    //sign out
    @DeleteMapping("user/signOut")
    public String userSignOut(@RequestParam String email, @RequestParam String tokenValue)
    {
        return userService.userSignOut(email,tokenValue);
    }

    //order food
    @PostMapping("order/{foodName}")
    public String orderFood(@RequestParam String email, @RequestParam String tokenValue,@PathVariable String foodName){
        return userService.orderFood(email,tokenValue,foodName);
    }

    //order history
    @GetMapping("order/history")
    public List<Order> orderHistory(@RequestParam String email, @RequestParam String tokenValue){
        return userService.orderHistory(email,tokenValue);
    }
    //get Order details
    @GetMapping("order/details")
    public Order orderDetails(@RequestParam String email, @RequestParam String tokenValue,@RequestParam Integer orderId){
        return userService.getOrderDetails(email,tokenValue,orderId);
    }
    //cancel order by order id
    @DeleteMapping("order/cancel/{orderId}")
    public String cancelOrder(@RequestParam String email, @RequestParam String tokenValue,@PathVariable Integer orderId){
        return userService.cancelOrder(email,tokenValue,orderId);
    }
    //get all restaurant food this functionality can use admin and user also
    @GetMapping("user/foodItems/{restaurantId}")
    public List<Food> getAllRestaurantFood(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer restaurantId){
        return userService.getAllRestaurantFood(email,tokenValue,restaurantId);
    }
    //get all food items available this is also same as admin
    @GetMapping("user/foodItems")
    public List<Food> getAllFoodItems(@RequestParam String email, @RequestParam String tokenValue){
        return userService.getAllFoodItems(email,tokenValue);
    }
    // get all restaurants same as admin
    @GetMapping("user/restaurants")
    public List<Restaurant> getAllRestaurants(@RequestParam String email, @RequestParam String tokenValue){
        return userService.getAllRestaurants(email,tokenValue);
    }
    //same as admin like getting food details
    @GetMapping("user/foodItem/{foodId}")
    public Food getFoodItem(@RequestParam String email, @RequestParam String tokenValue,@PathVariable Integer foodId){
        return userService.getFoodItem(email,tokenValue,foodId);
    }

}
