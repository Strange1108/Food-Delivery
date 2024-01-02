package com.Mct.food.delivery.platform.Repo;

import com.Mct.food.delivery.platform.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepo extends JpaRepository<Address,Integer> {

}
