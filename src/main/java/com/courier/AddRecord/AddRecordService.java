package com.courier.AddRecord;
import com.courier.RecordTable.RecordTable;
import com.courier.RecordTable.RecordTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddRecordService {
    @Autowired
    RecordTableRepository RecordRepo;

    public void createaddrecord(RecordTable RecordEntity) {
        RecordRepo.save(RecordEntity);
    }
}
