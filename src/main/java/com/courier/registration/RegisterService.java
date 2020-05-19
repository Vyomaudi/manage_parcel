package com.courier.registration;

import com.courier.login.LoginRepository;
import com.courier.login.LoginTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RegisterService {

    @Autowired
    LoginRepository loginRepo;

    public int register(LoginTable loginentity) {;
        if(loginentity.getSpecial().equals("abcdef") && loginRepo.findByusername(loginentity.getUsername())==null)
        {
            loginRepo.save(loginentity);
            return 1;
        }
        else if(!(loginentity.getSpecial().equals("abcdef")))
        {
            return 2;
        }
        else if(loginRepo.findByusername(loginentity.getUsername())!=null)
        {
            return 3;
        }
        else
            return 4;
    }

}
