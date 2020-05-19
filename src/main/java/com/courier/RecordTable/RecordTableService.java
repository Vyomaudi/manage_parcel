package com.courier.RecordTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class RecordTableService {

    private final RecordTableRepository recordRepo;

    public RecordTableService(RecordTableRepository recordRepo) {
        this.recordRepo = recordRepo;
    }

    List<RecordTable> getAllRecord() {
        return (List<RecordTable>) recordRepo.findAll();
    }

    public void createRecord(RecordTable r){
        recordRepo.save(r);
    }


    //public List<RecordTable> getUnpickedRecord() {
     //   return (List<RecordTable>) recordRepo.findUnpicked();
    //}
}

