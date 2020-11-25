package com.courier.registration;

import com.courier.ParcelManageApplication;
import com.courier.login.LoginController;
import com.courier.login.LoginService;
import com.courier.login.LoginTable;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ParcelManageApplication.class)
@AutoConfigureMockMvc
class RegisterControllerTest {
    @InjectMocks
    RegisterController mockregistercontroller = Mockito.spy(new RegisterController());

    @Mock
    RedirectAttributes mockredir;

    @Mock
    MockHttpSession mocksession = new MockHttpSession();

    @Mock
    RegisterService mockregisterservice;

    @Test
    void testSuccessfullRegistration() throws Exception {

        LoginTable mockRegisterEntity = new LoginTable("shreya" , "1234");
        Mockito.when(mockregisterservice.register(mockRegisterEntity)).thenReturn(1);

        ModelAndView mockmodel = mockregistercontroller.processRequest(mockRegisterEntity);
        Assert.assertEquals("register", mockmodel.getViewName());
    }

    @Test
    void testValidRootPassword() throws Exception {

        LoginTable mockRegisterEntity = new LoginTable("shreya" , "1234");
        Mockito.when(mockregisterservice.register(mockRegisterEntity)).thenReturn(2);

        ModelAndView mockmodel = mockregistercontroller.processRequest(mockRegisterEntity);
        Assert.assertEquals("register", mockmodel.getViewName());
    }

    @Test
    void testIfUserExists() throws Exception {

        LoginTable mockRegisterEntity = new LoginTable("shreya" , "1234");
        Mockito.when(mockregisterservice.register(mockRegisterEntity)).thenReturn(3);

        ModelAndView mockmodel = mockregistercontroller.processRequest(mockRegisterEntity);
        Assert.assertEquals("register", mockmodel.getViewName());
    }


}