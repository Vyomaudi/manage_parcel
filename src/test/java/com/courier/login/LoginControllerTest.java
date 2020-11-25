package com.courier.login;

import com.courier.ParcelManageApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.rest.api.v2010.account.Application;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ParcelManageApplication.class)
@AutoConfigureMockMvc
class LoginControllerTest {

    @InjectMocks
    LoginController mocklogincontroller = Mockito.spy(new LoginController());

    @Mock
    RedirectAttributes mockredir;

    @Mock
    MockHttpSession mocksession = new MockHttpSession();

    @Mock
    LoginService mockloginservice;



    @Test
    void testwelcome() {
        ModelAndView m = mocklogincontroller.welcome();
        Assert.assertEquals("index", m.getViewName());
    }

    @Test
    void testregister() {
        ModelAndView m = mocklogincontroller.registerpage();
        Assert.assertEquals("register", m.getViewName());
    }

    @Test
    void testloginpage() {
        ModelAndView mockmodel = mocklogincontroller.loginpage();
        Assert.assertEquals("login", mockmodel.getViewName());
        Assert.assertNotNull(mockmodel);
    }
    //@Autowired



    @Test
    void testprocessrequest() throws Exception {

        LoginTable mocklogin = new LoginTable();
        mocklogin.username="aaaa";
        mocklogin.password="aaaaaaaa";

        Mockito.when(mockloginservice.validation(mocklogin)).thenReturn(true);

        ModelAndView mockmodel = mocklogincontroller.processRequest(mocklogin,mockredir,mocksession);
        Assert.assertEquals("redirect:/usersession", mockmodel.getViewName());
    }

    @Test
    void testprocessrequestdeclined() throws Exception {

        LoginTable mocklogin = new LoginTable();
        mocklogin.username="wrongname";
        mocklogin.password="wrongpassword";

        Mockito.when(mockloginservice.validation(mocklogin)).thenReturn(false);

        ModelAndView mockmodel = mocklogincontroller.processRequest(mocklogin,mockredir,mocksession);
        Assert.assertEquals("redirect:/login", mockmodel.getViewName());
    }

    @Test
    void teststartsession() throws Exception{
        MockHttpSession mocksession1 = new MockHttpSession();
        mocksession1.setAttribute("username","vyom");
        ModelAndView mockmodel = mocklogincontroller.start(mocksession1,mockredir);
        Assert.assertEquals("usersession", mockmodel.getViewName());
    }
    @Test
    void teststartsessiondeclined() throws Exception{

        mocksession.setAttribute("username",null);
        ModelAndView mockmodel = mocklogincontroller.start(mocksession,mockredir);
        Assert.assertEquals("redirect:/login", mockmodel.getViewName());
    }

    @Test
    void testlogout() throws Exception{
        ModelAndView mockmodel = mocklogincontroller.logout(mocksession,mockredir);
        Assert.assertEquals("redirect:/login", mockmodel.getViewName());
    }
}