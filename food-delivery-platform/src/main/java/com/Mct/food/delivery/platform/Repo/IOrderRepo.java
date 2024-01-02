package com.Mct.food.delivery.platform.Repo;

import com.Mct.food.delivery.platform.Model.Order;
import com.Mct.food.delivery.platform.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderRepo extends JpaRepository<Order,Integer> {
    List<Order> findByUser(User user);
}
