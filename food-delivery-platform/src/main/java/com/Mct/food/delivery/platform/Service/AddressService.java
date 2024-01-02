package com.Mct.food.delivery.platform.Service;

import com.Mct.food.delivery.platform.Model.Address;
import com.Mct.food.delivery.platform.Repo.IAddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    IAddressRepo addressRepo;

    public void addAddress(Address address) {
        addressRepo.save(address);
    }
}
