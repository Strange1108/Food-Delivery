package com.Mct.food.delivery.platform.Repo;

import com.Mct.food.delivery.platform.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepo  extends JpaRepository<Admin,Integer> {
    Admin findFirstByAdminEmail(String email);
}
