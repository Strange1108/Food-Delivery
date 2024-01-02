package com.Mct.food.delivery.platform.Repo;

import com.Mct.food.delivery.platform.Model.Auth.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserAuthRepo extends JpaRepository<UserAuth,Integer> {
    UserAuth findByTokenValue(String tokenValue);
}
