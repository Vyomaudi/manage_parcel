package com.courier.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
    public ModelAndView welcome() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView loginpage() {
        return new ModelAndView("login", "logintable", new LoginTable());
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registerpage() {
        return new ModelAndView("register", "logintable", new LoginTable());
    }

    @RequestMapping(value = "/logincheck", method = RequestMethod.POST)
    public ModelAndView processRequest(@ModelAttribute("logintable") LoginTable loginEntity , RedirectAttributes redir , HttpSession session) {

        boolean adminname = loginService.validation(loginEntity);
        //System.out.println(getAdminname());
        ModelAndView model;
        if (adminname) {
            model = new ModelAndView("redirect:/usersession");
            session.setAttribute("username", loginEntity.getUsername());
            return model;
        }

        else {
            model = new ModelAndView("redirect:/login");
            //model.addObject("message","User not Found");
            redir.addFlashAttribute("message", "User Not Found");
            return model;
        }

    }

    @RequestMapping(value = {"/usersession"}, method = RequestMethod.GET)
    public ModelAndView start(HttpSession session,RedirectAttributes redir) {
        if(session.getAttribute("username")!=null)
        return new ModelAndView("usersession");
        else {
            redir.addFlashAttribute("message","Log In First");
            return new ModelAndView("redirect:/login");

        }
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpSession session ,RedirectAttributes redir) {

        ModelAndView model;
        model= new ModelAndView("redirect:/login");
        redir.addFlashAttribute("logout", "Sucessfully Loged Out!");
        session.invalidate();
        return model;
    }
}






