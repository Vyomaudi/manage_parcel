package com.courier.AddRecord;

import com.courier.ParcelManageApplication;
import com.courier.RecordTable.RecordTable;
import com.courier.RecordTable.RecordTableController;
import com.courier.RecordTable.RecordTableRepository;
import com.courier.RecordTable.RecordTableService;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ParcelManageApplication.class)
@AutoConfigureMockMvc
class AddRecordControllerTest {
    @InjectMocks
    AddRecordController mockAddRecordController = Mockito.spy(new AddRecordController());

    @Mock
    RedirectAttributes mockredir;

    @Mock
    MockHttpSession mocksession = new MockHttpSession();

    @Mock
    private RecordTableService mockRecordService;
    @Mock
    RecordTableRepository mokRecordRepo;

    @Test
    void testUpdate_record() throws Exception {
        Mockito.when(mokRecordRepo.find_id_tracking_id("one")).thenReturn((long) 1);
        RecordTable r = mokRecordRepo.findBytracking_id("one");
        Mockito.when(mokRecordRepo.findBytracking_id("one")).thenReturn(r);

        ModelAndView mockmodel = mockAddRecordController.update_record("one", mockredir);
        Assert.assertEquals("update_record", mockmodel.getViewName());
    }
    @Test
    void testUpdate_recordNullId() throws Exception {
        Mockito.when(mokRecordRepo.find_id_tracking_id("six")).thenReturn(null);
        RecordTable r = mokRecordRepo.findBytracking_id("one");
        Mockito.when(mokRecordRepo.findBytracking_id("one")).thenReturn(r);

        ModelAndView mockmodel = mockAddRecordController.update_record("six", mockredir);
        Assert.assertEquals("redirect:/usersession", mockmodel.getViewName());
    }

    @Test
    void testUpdated_record() throws Exception {
        String s_date="2020-11-24";
        Date date=new SimpleDateFormat("yyyy-MM-dd").parse(s_date);
        RecordTable recordTableEntity = new RecordTable("two" ,"Shreya" ,"Myntra", "collect from reception", "Unpicked" , date , "9090909090");
        Mockito.doNothing().when(mockRecordService).createRecord(recordTableEntity);

        ModelAndView mockmodel = mockAddRecordController.updated_record(recordTableEntity, mockredir);
        Assert.assertEquals("redirect:/usersession", mockmodel.getViewName());
    }

    @Test
    void testCreateadd_record() throws Exception {
        String s_date="2020-11-24";
        Date date=new SimpleDateFormat("yyyy-MM-dd").parse(s_date);
        RecordTable recordTableEntity = new RecordTable("two" ,"Shreya" ,"Myntra", "collect from reception", "Unpicked" , date , "9090909090");
        RecordTable reslist = mokRecordRepo.findBytracking_id(recordTableEntity.getTrack_id());

        Mockito.when(mokRecordRepo.findBytracking_id("three")).thenReturn(reslist);

        ModelAndView mockmodel = mockAddRecordController.createadd_record(recordTableEntity, mockredir);
        Assert.assertEquals("redirect:/usersession", mockmodel.getViewName());
    }

    @Test
    void testCreate_record() throws Exception {
        MockHttpSession mocksession1 = new MockHttpSession();
        mocksession1.setAttribute("username","shreya");
        ModelAndView mockmodel = mockAddRecordController.create_record(mocksession1,mockredir);
        Assert.assertEquals("createrecord", mockmodel.getViewName());
    }

    @Test
    void testCreate_recordWithNoUsername() throws Exception {
        MockHttpSession mocksession1 = new MockHttpSession();
        ModelAndView mockmodel = mockAddRecordController.create_record(mocksession1,mockredir);
        Assert.assertEquals("redirect:/login", mockmodel.getViewName());
    }

    @Test
    void testDelete_record() throws Exception {
        String s_date="2020-11-24";
        Date date=new SimpleDateFormat("yyyy-MM-dd").parse(s_date);
        RecordTable recordTableEntity = new RecordTable("four" ,"Shreya" ,"Myntra", "collect from reception", "Unpicked" , date , "9090909090");
        RecordTable reslist = mokRecordRepo.findBytracking_id(recordTableEntity.getTrack_id());

        Mockito.when(mokRecordRepo.findBytracking_id("four")).thenReturn(reslist);

        ModelAndView mockmodel = mockAddRecordController.createadd_record(recordTableEntity, mockredir);
        Assert.assertEquals("redirect:/usersession", mockmodel.getViewName());
    }

}



