package com.Mct.food.delivery.platform.Service;

import com.Mct.food.delivery.platform.Model.*;
import com.Mct.food.delivery.platform.Model.Auth.UserAuth;
import com.Mct.food.delivery.platform.Model.Enums.OrderStatus;
import com.Mct.food.delivery.platform.Repo.IUserRepo;
import com.Mct.food.delivery.platform.Service.HashingUtility.PasswordEncryptor;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;
    @Autowired
    UserAuthService userAuthService;

    @Autowired
    FoodService foodService;

    @Autowired
    OrderService orderService;
    @Autowired
    RestaurantService restaurantService;

    @Autowired
    AddressService addressService;


    public String userSignUp(User newUser, Address address) {

        //check if already exist -> Not allowed : try logging in

        String newEmail = newUser.getUserEmail();

        User existingUser = userRepo.findFirstByUserEmail(newEmail);

        if(existingUser != null)
        {
            return "email already in use";
        }

        // passwords are encrypted before we store it in the table

        String signUpPassword = newUser.getUserPassword();

        try {

            String encryptedPassword = PasswordEncryptor.encrypt(signUpPassword);


            newUser.setUserPassword(encryptedPassword);
            addressService.addAddress(address);
            userRepo.save(newUser);
            return "User registered please login";

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }

    }

    public String userSignIn(String email, String password) {

        //check if the email is there in your tables
        //sign in only possible if this person ever signed up


        User existingUser = userRepo.findFirstByUserEmail(email);

        if(existingUser == null)
        {
            return "Not a valid email, Please sign up first !!!";
        }

        //password should be matched

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);

            if(existingUser.getUserPassword().equals(encryptedPassword))
            {
                // return a token for this sign in
                UserAuth token  = new UserAuth(existingUser);
                    userAuthService.createToken(token);
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

    public String userSignOut(String email,String tokenValue) {
        if(userAuthService.authenticate(email,tokenValue)) {
            userAuthService.deleteToken(tokenValue);
            return "Sign Out successful!!";
        }
        else {
            return "Un Authenticated access!!!";
        }
    }

    public String orderFood(String email, String tokenValue, String foodName) {
        if (userAuthService.authenticate(email, tokenValue)){
            if (foodService.available(foodName)){
                User user=userRepo.findFirstByUserEmail(email);
                Food food= foodService.getFood(foodName);
                Order order = new Order(null, LocalDateTime.now(), OrderStatus.Ordered,user,food);
                orderService.createOrder(order);
                return "order placed";
            }
            return "Out of stock";
        }
        return "Unauthenticated access";
    }

    public List<Order> orderHistory(String email, String tokenValue) {
        if (userAuthService.authenticate(email, tokenValue)){
            User user = userRepo.findFirstByUserEmail(email);
         return  orderService.getHistory(user);
        }
        return null;
    }


    public Order getOrderDetails(String email, String tokenValue, Integer orderId) {
        if (userAuthService.authenticate(email, tokenValue)){
          Order order= orderService.getOrderDetails(orderId);
          if(order.getUser().getUserEmail().equals(email)){
              return order;
          }
          return null;

        }
        return null;
    }

    public String cancelOrder(String email, String tokenValue, Integer orderId) {
        if (userAuthService.authenticate(email, tokenValue)){
            Order order = orderService.getOrderDetails(orderId);
            if (order.getUser().getUserEmail().equals(email)){
                if (!order.getOrderStatus().equals(OrderStatus.Delivered)){

                    orderService.deleteOrder(order);
                    return "Order Canceled";
                }
                return "can't cancel delivered order";

            }
            return "unauthorised access";

        }
        return "unauthenticated access";
    }

    public List<Food> getAllRestaurantFood(String email, String tokenValue, Integer restaurantId) {
        if(userAuthService.authenticate(email, tokenValue)){
            return restaurantService.getRestaurant(restaurantId).getFoodList();
        }
        return null;
    }

    public List<Food> getAllFoodItems(String email, String tokenValue) {
        if (userAuthService.authenticate(email, tokenValue)){
            return foodService.getAllFoodItems();
        }
        return null;
    }

    public List<Restaurant> getAllRestaurants(String email, String tokenValue) {
        if (userAuthService.authenticate(email, tokenValue)){
            return restaurantService.getAllRestaurants();
        }
        return null;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public Food getFoodItem(String email, String tokenValue, Integer foodId) {
        if (userAuthService.authenticate(email, tokenValue)){
            return foodService.getFoodItem(foodId);
        }
        return null;
    }
}
