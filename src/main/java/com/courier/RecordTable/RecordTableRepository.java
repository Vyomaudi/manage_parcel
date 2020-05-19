package com.courier.RecordTable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecordTableRepository extends CrudRepository<RecordTable,Long> {

    @Query("SELECT R FROM RecordTable R WHERE(R.track_id=?1)")
    RecordTable findBytracking_id(String track_id);


    @Query("SELECT R.item_no FROM RecordTable R WHERE(R.track_id=?1)")
    Long find_id_tracking_id(String track_id);


    // @Query("SELECT R FROM RecordTable R WHERE(R.status='Unpicked')")
   // List<RecordTable> findUnpicked();
}
