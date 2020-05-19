package com.courier.RecordTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.print.DocFlavor;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class RecordTableController
{

    @Autowired
    private RecordTableService recordService;
    @Autowired
    RecordTableRepository recordRepo;

    @RequestMapping(value = "/table")
    public List<RecordTable> record(){
        return recordService.getAllRecord();
    }
/*
    @RequestMapping(method = RequestMethod.POST,value = "/table")
    public void addRecord(@RequestBody RecordTable recordtable){
        recordService.createRecord(recordtable);
    }


*/

    @RequestMapping(value = {"/recordlist"},method = RequestMethod.GET)
    public ModelAndView record_list(HttpSession session,RedirectAttributes redir) {
        if(session.getAttribute("username")!=null){
            List<RecordTable> record = recordService.getAllRecord();
            ModelAndView model = new ModelAndView("recordlist");
            model.addObject("record", record);
            return model;
        }
        else {
            redir.addFlashAttribute("message","Log In First");
            return new ModelAndView("redirect:/login");
        }
    }

    @RequestMapping(value = {"/findrecord"}, method = RequestMethod.GET)
    public ModelAndView find_record(String track_id , RedirectAttributes redir){
        if((recordRepo.find_id_tracking_id(track_id))!=null) {
            RecordTable r = recordRepo.findBytracking_id(track_id);
            ModelAndView model= new ModelAndView("find_record");
            model.addObject("r",r);
            return model;
        }
        else {
            redir.addFlashAttribute("find", "No such Item Recieved");
            return new ModelAndView("redirect:/index");
        }
    }




}
