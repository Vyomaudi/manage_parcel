package com.courier.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    LoginRepository loginRepo;


    public boolean validation(LoginTable loginentity) {

        LoginTable result = loginRepo.findByusernameAndpassword(loginentity.username, loginentity.password);
        if (result != null) {
            return true;
        }
        return false;
    }

}


