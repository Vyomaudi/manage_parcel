package com.courier.AddRecord;

import com.courier.RecordTable.RecordTable;
import com.courier.RecordTable.RecordTableRepository;
import com.courier.RecordTable.RecordTableService;
import com.courier.login.LoginController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;



@RestController
public class AddRecordController {

    @Autowired
    AddRecordService addrecordService;

    @Autowired
    RecordTableService recordService;

    @Autowired
    RecordTableRepository recordRepo;


    //update record
    @RequestMapping(value = {"/updaterecord"}, method = RequestMethod.GET)
    public ModelAndView update_record(String track_id ,RedirectAttributes redir){
        if((recordRepo.find_id_tracking_id(track_id))!=null) {
            RecordTable r = recordRepo.findBytracking_id(track_id);
            ModelAndView model= new ModelAndView("update_record");
            model.addObject("r",r);
            return model;
        }
        else {
            redir.addFlashAttribute("update", "No such Item");
            return new ModelAndView("redirect:/usersession");
        }
    }


    //saving the updated record and redirecting to usersession
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updated_record(RecordTable recordEntity,RedirectAttributes redir) {
        recordService.createRecord(recordEntity);
        redir.addFlashAttribute("update", "Record Updated");
        return new ModelAndView("redirect:/usersession");
    }


    //delete a record by ID
    @RequestMapping(value = {"/deleterecord"}, method = RequestMethod.POST)
    public ModelAndView delete_record(String track_id ,RedirectAttributes redir) {
        if(recordRepo.find_id_tracking_id(track_id)!=null) {
            RecordTable r = recordRepo.findBytracking_id(track_id);
            recordRepo.deleteById(r.getItem_no());
            redir.addFlashAttribute("delete","Record Deleted");
        }
        else { redir.addFlashAttribute("delete","No such Item");}

        return new ModelAndView("redirect:/usersession");
    }


     //create record
    @RequestMapping(value = "/createrecord", method = RequestMethod.POST)
    public ModelAndView createadd_record(RecordTable recordEntity, RedirectAttributes redir) {

        ModelAndView model = new ModelAndView("redirect:/usersession");
        RecordTable reslist = recordRepo.findBytracking_id(recordEntity.getTrack_id());
        if (reslist !=null) {
            redir.addFlashAttribute("create", "Record Already Exists");
        }
        else {
            recordService.createRecord(recordEntity);
            redir.addFlashAttribute("create", "Record Added");
        }
        return model;
    }


    @RequestMapping(value = "/createrecord", method = RequestMethod.GET)
    public ModelAndView create_record(HttpSession session,RedirectAttributes redir) {
        if(session.getAttribute("username")!=null)
        return new ModelAndView("createrecord", "recordtable", new RecordTable());
        else {
            redir.addFlashAttribute("message", "Log In First");
            return new ModelAndView("redirect:/login");
        }
    }
}
