package com.Mct.food.delivery.platform.Repo;

import com.Mct.food.delivery.platform.Model.Auth.AdminAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminAuthRepo extends JpaRepository<AdminAuth,Integer> {
    AdminAuth findByTokenValue(String tokenValue);
}
