package com.Mct.food.delivery.platform.Service;

import com.Mct.food.delivery.platform.Model.Auth.UserAuth;
import com.Mct.food.delivery.platform.Repo.IUserAuthRepo;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {
    @Autowired
    IUserAuthRepo userAuthRepo;

    public void createToken(UserAuth token) {
        userAuthRepo.save(token);
    }

    public boolean authenticate(String email, String tokenValue) {
        UserAuth token = userAuthRepo.findByTokenValue(tokenValue);
        if (token!=null){
            return token.getUser().getUserEmail().equals(email);
        }
        return false;
    }

    public void deleteToken(String tokenValue) {
        UserAuth token = userAuthRepo.findByTokenValue(tokenValue);
        userAuthRepo.delete(token);
    }
}
