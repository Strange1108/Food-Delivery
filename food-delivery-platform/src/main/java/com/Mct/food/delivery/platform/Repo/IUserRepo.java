package com.Mct.food.delivery.platform.Repo;

import com.Mct.food.delivery.platform.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User,Integer> {
    User findFirstByUserEmail(String newEmail);
}
