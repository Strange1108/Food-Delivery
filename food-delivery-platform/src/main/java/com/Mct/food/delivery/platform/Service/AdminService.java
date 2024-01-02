package com.Mct.food.delivery.platform.Service;

import com.Mct.food.delivery.platform.Model.*;
import com.Mct.food.delivery.platform.Model.Auth.AdminAuth;
import com.Mct.food.delivery.platform.Model.Auth.UserAuth;
import com.Mct.food.delivery.platform.Model.Enums.OrderStatus;
import com.Mct.food.delivery.platform.Repo.IAdminRepo;
import com.Mct.food.delivery.platform.Service.HashingUtility.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    IAdminRepo adminRepo;

    @Autowired
    AdminAuthService adminAuthService;

    @Autowired
    RestaurantService restaurantService;
    @Autowired
    FoodService foodService;

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

    public String adminSignUp(Admin newAdmin) {

        //check if already exist -> Not allowed : try logging in

        String newEmail = newAdmin.getAdminEmail();

        Admin existingAdmin = adminRepo.findFirstByAdminEmail(newEmail);

        if(existingAdmin != null)
        {
            return "email already in use";
        }

        // passwords are encrypted before we store it in the table

        String signUpPassword = newAdmin.getAdminPassword();

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(signUpPassword);

            newAdmin.setAdminPassword(encryptedPassword);
            adminRepo.save(newAdmin);
            return "Admin registered please login";

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }

    }

    public String adminSignIn(String email, String password) {

        //check if the email is there in your tables
        //sign in only possible if this person ever signed up


        Admin existingAdmin = adminRepo.findFirstByAdminEmail(email);

        if(existingAdmin == null)
        {
            return "Not a valid email,  !!!";
        }

        //password should be matched

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);

            if(existingAdmin.getAdminPassword().equals(encryptedPassword))
            {
                // return a token for this sign in
                AdminAuth token  = new AdminAuth(existingAdmin);
                adminAuthService.createToken(token);
                return "Login success here is token:- "+ token.getTokenValue();
            }
            else {
                //password was wrong!!!
                return "Invalid Credentials!!!";
            }

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }
    }

    public String adminSignOut(String email,String tokenValue) {
        if(adminAuthService.authenticate(email,tokenValue)) {
            adminAuthService.deleteToken(tokenValue);
            return "Sign Out successful!!";
        }
        else {
            return "Un Authenticated access!!!";
        }
    }

    public String addFoodItem(String email, String tokenValue, Food food) {
        if (adminAuthService.authenticate(email, tokenValue)){
            if(foodService.checkAlreadyThere(food)){
                return "Food is already there added";
            }
            foodService.addFood(food);
            return "Food added ";
        }
        return "Unauthenticated access";
    }

    public List<Order> getAllOrders(String email, String tokenValue) {
        if(adminAuthService.authenticate(email, tokenValue)){
            List<Order> orderList = orderService.getAllOrders();
            for(Order order: orderList){
                User user = new User
                        (order.getUser().getUserId(),order.getUser().getUserName(),null,null,order.getUser().getAddress());
                order.setUser(user);

            }
            return orderList;
        }
        return null;
    }

    public String deleteFoodItem(String email, String tokenValue, Integer foodId) {
        if (adminAuthService.authenticate(email, tokenValue)){
            Food food= foodService.getFoodItem(foodId);
            foodService.deleteFood(food);
            return "Food Removed";
        }
        return "Unauthenticated access";
    }

    public String changePrice(String email, String tokenValue, Integer foodId, Double price) {
        if (adminAuthService.authenticate(email, tokenValue)){
            Food food= foodService.getFoodItem(foodId);
            food.setFoodPrice(price);
            foodService.addFood(food);
            return "price of food "+ food.getFoodName() +" updated by :- "+ price;
        }
        return "Unauthenticated access";
    }

    public List<Restaurant> getAllRestaurants(String email, String tokenValue) {
        if (adminAuthService.authenticate(email, tokenValue)){
            return restaurantService.getAllRestaurants();
        }
        return null;
    }

    public List<Food> getAllFoodItems(String email, String tokenValue) {
        if (adminAuthService.authenticate(email,tokenValue)){
            return foodService.getAllFoodItems();
        }
        return null;
    }

    public List<Food> getAllRestaurantFood(String email, String tokenValue, Integer restaurantId) {
        if (adminAuthService.authenticate(email, tokenValue)){
            return restaurantService.getRestaurant(restaurantId).getFoodList();
        }
        return null;
    }

    public Food getFoodItem(String email, String tokenValue, Integer foodId) {
        if (adminAuthService.authenticate(email, tokenValue)){
            return foodService.getFoodItem(foodId);
        }
        return null;
    }

    public String updateOrderStatus(String email, String tokenValue, Integer orderId, OrderStatus orderStatus) {
        if (adminAuthService.authenticate(email, tokenValue)){
            Order order=orderService.getOrderDetails(orderId);
            order.setOrderStatus(orderStatus);
            orderService.createOrder(order);
            return "order status of order id "+ order.getOrderId() +"updated to :- "+ orderStatus;
        }
        return "Unauthenticated access";
    }

    public String addRestaurant(String email, String tokenValue, Restaurant restaurant) {
        if (adminAuthService.authenticate(email, tokenValue)){
            String restaurantEmail= restaurant.getRestaurantEmail();
            String restaurantName=restaurant.getRestaurantName();
            if(restaurantService.isAlready(restaurantEmail) && restaurantService.checkByName(restaurantName)){
                return "restaurant already exists with the same mail and name";
            }
            Address address=restaurant.getAddress();
            addressService.addAddress(address);
            restaurantService.addRestaurant(restaurant);
            return "Restaurant "+restaurantName+ " added ";
        }
        return "Unauthenticated access";
    }

    public List<User> getAllUsers(String email, String tokenValue) {
        if (adminAuthService.authenticate(email, tokenValue)){

            List<User> users = userService.getAllUsers();
            for (User user: users) {
                user.setUserPassword("Not visible");
            }
            return users;
        }
        return null;
    }
}
