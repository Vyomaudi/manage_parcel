package com.courier.registration;

import com.courier.ParcelManageApplication;
import com.courier.login.LoginRepository;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ParcelManageApplication.class)
@AutoConfigureMockMvc
class RegisterServiceTest {
    @InjectMocks
    RegisterService mockRegisterService;

    @Mock
    LoginRepository mockLoginRepository;

    @Test
    void testRegisterServiceReturns1() throws Exception{
        //LoginTable mockRegisterEntity = new LoginTable("shreya" , "1234");
        LoginTable mockRegisterEntity = new LoginTable();
        mockRegisterEntity.setSpecial("abcdef");
        Mockito.when(mockLoginRepository.findByusername("shreya")).thenReturn(mockRegisterEntity);

        int mockresult = mockRegisterService.register(mockRegisterEntity);
        Assert.assertEquals(1,mockresult);
    }

    @Test
    void testRegisterServiceReturns2() throws Exception{
        LoginTable mockRegisterEntity = new LoginTable("shreya" , "1234");
        mockRegisterEntity.setSpecial("abc");
        Mockito.when(mockLoginRepository.findByusername("shreya")).thenReturn(mockRegisterEntity);

        int mockresult = mockRegisterService.register(mockRegisterEntity);
        Assert.assertEquals(2,mockresult);
    }

    @Test
    void testRegisterServiceReturns3() throws Exception{
        LoginTable mockRegisterEntity = new LoginTable("shreya" , "1234");
        mockRegisterEntity.setSpecial("abcdef");
        Mockito.when(mockLoginRepository.findByusername("shreya")).thenReturn(mockRegisterEntity);

        int mockresult = mockRegisterService.register(mockRegisterEntity);
        Assert.assertEquals(3,mockresult);
    }

}