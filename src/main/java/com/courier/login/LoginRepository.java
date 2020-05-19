package com.courier.login;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface LoginRepository extends CrudRepository<LoginTable,Long> {

    @Query("SELECT R FROM LoginTable R WHERE(R.username=?1 AND R.password=?2)")
    LoginTable findByusernameAndpassword(String usrname,String password);

    @Query("SELECT R FROM LoginTable R WHERE(R.username=?1)")
    LoginTable findByusername(String usrname);

}
