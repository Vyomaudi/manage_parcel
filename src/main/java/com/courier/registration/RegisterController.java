package com.courier.registration;

import com.courier.login.LoginTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView processRequest(@ModelAttribute("logintable") LoginTable registerEntity) {
        ModelAndView model = null;
        int result = registerService.register(registerEntity);
        if (result == 1) {
            model = new ModelAndView("register", "logintable", new LoginTable());
            model.addObject("success", "AddedSussessfully");
        }
        else if (result == 2) {
            model = new ModelAndView("register", "logintable", new LoginTable());
            model.addObject("wrongroot", "Incorrect Root Password!");

        }
        else if (result == 3) {
            model = new ModelAndView("register", "logintable", new LoginTable());
            model.addObject("userexists", "Username Exists!");
        }
        return model;
    }

}
