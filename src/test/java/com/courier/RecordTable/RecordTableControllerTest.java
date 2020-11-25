package com.courier.RecordTable;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ParcelManageApplication.class)
@AutoConfigureMockMvc
class RecordTableControllerTest {
    @InjectMocks
    RecordTableController mockRecordTableController = Mockito.spy(new RecordTableController());

    @Mock
    RedirectAttributes mockredir;

    @Mock
    MockHttpSession mocksession = new MockHttpSession();

    @Mock
    private RecordTableService mockRecordService;
    @Mock
    RecordTableRepository mokRecordRepo;

    @Test
    void testRecordList() throws Exception {

        MockHttpSession mocksession1 = new MockHttpSession();
        mocksession1.setAttribute("username", "shreya");
        List<RecordTable> record = mockRecordService.getAllRecord();

        Mockito.when(mockRecordService.getAllRecord()).thenReturn(record);

        ModelAndView mockmodel = mockRecordTableController.record_list(mocksession1, mockredir);
        Assert.assertEquals("recordlist", mockmodel.getViewName());
    }

    @Test
    void testRecordListWithNullSession() throws Exception {

        MockHttpSession mocksession1 = new MockHttpSession();
        //mocksession1.setAttribute("username","shreya");
        List<RecordTable> record = mockRecordService.getAllRecord();

        Mockito.when(mockRecordService.getAllRecord()).thenReturn(record);

        ModelAndView mockmodel = mockRecordTableController.record_list(mocksession1, mockredir);
        Assert.assertEquals("redirect:/login", mockmodel.getViewName());
    }

    @Test
    void testFindRecord() throws Exception {
        Mockito.when(mokRecordRepo.find_id_tracking_id("one")).thenReturn((long) 1);
        RecordTable r = mokRecordRepo.findBytracking_id("one");
        Mockito.when(mokRecordRepo.findBytracking_id("one")).thenReturn(r);

        ModelAndView mockmodel = mockRecordTableController.find_record("one", mockredir);
        Assert.assertEquals("find_record", mockmodel.getViewName());
    }
}