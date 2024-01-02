package com.Mct.food.delivery.platform.Service;

import com.Mct.food.delivery.platform.Model.Order;
import com.Mct.food.delivery.platform.Model.User;
import com.Mct.food.delivery.platform.Repo.IOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    IOrderRepo orderRepo;

    public void createOrder(Order order) {
        orderRepo.save(order);
    }

    public List<Order> getHistory(User user) {
        return orderRepo.findByUser(user);
    }

    public Order getOrderDetails(Integer orderId) {
        return orderRepo.findById(orderId).orElseThrow();
    }

    public void deleteOrder(Order order) {
        orderRepo.delete(order);
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }
}
