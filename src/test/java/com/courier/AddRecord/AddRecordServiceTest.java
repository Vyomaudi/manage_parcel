package com.courier.AddRecord;

import com.courier.ParcelManageApplication;
import com.courier.RecordTable.RecordTable;
import com.courier.RecordTable.RecordTableRepository;
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

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ParcelManageApplication.class)
@AutoConfigureMockMvc
class AddRecordServiceTest {
    @InjectMocks
    AddRecordService mockAddRecordService;
    @Mock
    RecordTableRepository mockRecordRepo;

    @Test
    void testCreateaddrecord() throws Exception{
        String s_date="2020-11-24";
        Date date=new SimpleDateFormat("yyyy-MM-dd").parse(s_date);
        RecordTable recordTableEntity = new RecordTable("two" ,"Shreya" ,"Myntra", "collect from reception", "Unpicked" , date , "9090909090");
        Mockito.when(mockRecordRepo.save(recordTableEntity)).thenReturn(recordTableEntity);
        System.out.println(recordTableEntity.toString());
    }
}