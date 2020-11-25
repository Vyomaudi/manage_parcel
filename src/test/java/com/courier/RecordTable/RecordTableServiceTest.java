package com.courier.RecordTable;

import com.courier.AddRecord.AddRecordService;
import com.courier.ParcelManageApplication;
import com.courier.login.LoginTable;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ParcelManageApplication.class)
@AutoConfigureMockMvc
class RecordTableServiceTest {
    @InjectMocks
    RecordTableService mockRecordTableService;
    @Mock
    RecordTableRepository mockRecordTableRepository;

    @Test
    void testGetAllRecord() throws Exception{
        List<RecordTable> records =  (List<RecordTable>)mockRecordTableRepository.findAll();
        Mockito.when(mockRecordTableRepository.findAll()).thenReturn(records);

        List<RecordTable> mockresult = mockRecordTableService.getAllRecord();
        Assert.assertEquals(records,mockresult);
    }

    @Test
    void testCreateRecord() throws Exception{
        String s_date="2020-11-24";
        Date date=new SimpleDateFormat("yyyy-MM-dd").parse(s_date);
        RecordTable recordTableEntity = new RecordTable("two" ,"Shreya" ,"Myntra", "collect from reception", "Unpicked" , date , "9090909090");
        Mockito.when(mockRecordTableRepository.save(recordTableEntity)).thenReturn(recordTableEntity);
        System.out.println(recordTableEntity.toString());
    }

}