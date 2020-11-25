package com.courier.login;

import com.courier.ParcelManageApplication;
import com.courier.RecordTable.RecordTableController;
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
class LoginServiceTest {
    @InjectMocks
    LoginService mockloginservice = Mockito.spy(new LoginService());

    @Mock
    LoginRepository mockLoginRepo;

    @Test
    void test_validation() throws Exception{
        LoginTable mockLoginEntity = new LoginTable("shreya" , "1234");
        Mockito.when(mockLoginRepo.findByusernameAndpassword(mockLoginEntity.username, mockLoginEntity.password)).thenReturn(mockLoginEntity);

        Boolean mockresult = mockloginservice.validation(mockLoginEntity);
        Assert.assertEquals(true,mockresult);
    }

    @Test
    void test_validationdeclined() throws Exception{
        LoginTable mocklogin = new LoginTable();
        mocklogin.username = "wrongname";
        mocklogin.password = "wrongpassword";
        LoginTable result = mockLoginRepo.findByusernameAndpassword(mocklogin.username, mocklogin.password);
        Mockito.when(mockLoginRepo.findByusernameAndpassword(mocklogin.username, mocklogin.password)).thenReturn(result);
        Boolean mockresult = mockloginservice.validation(mocklogin);
        Assert.assertEquals(false,mockresult);
    }

}