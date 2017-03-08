package com.arms.app.HolidayLeave;

import com.arms.domain.entity.HolidayLeave;
import com.arms.domain.repository.HolidayLeaveRepository;
import com.arms.domain.service.EventService;
import com.arms.domain.service.HolidayLeaveService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

/**
 * Created by arms20170106 on 22/2/2560.
 */
@Controller
@RequestMapping("holidayLeave")
public class AnnualLeaveController {
    @Autowired
    HolidayLeaveRepository holidayLeaveRepository;
    @Autowired
    EventService eventService;
    @Autowired
    HolidayLeaveService holidayLeaveService;
    private org.slf4j.Logger logger = LoggerFactory.getLogger(AnnualLeaveController.class);

    @RequestMapping(value = "three", method = RequestMethod.GET)
    public ModelAndView blankList(ModelAndView modelAndView) {
        modelAndView.addObject("annualLeaveSearch", new AnnualLeaveSearch());
        modelAndView.setViewName("holidayLeave/three");
        return modelAndView;
    }
    @RequestMapping(value = "three", method = RequestMethod.POST)
    public  ModelAndView showList(ModelAndView modelAndView, AnnualLeaveSearch annualLeaveSearch) {
        List<HolidayLeave> holidayLeaveList = holidayLeaveRepository.findAllByHolidayDate(annualLeaveSearch.getYear());
        modelAndView.addObject("holidayLeaveList", holidayLeaveList);
        modelAndView.setViewName("holidayLeave/three");
        return modelAndView;
    }
    @RequestMapping(value = "add/{holId}", method = RequestMethod.GET)
    public ModelAndView add(@PathVariable Integer holId, ModelAndView modelAndView) {
        try{
                eventService.sendCalendar(holId);
        }catch (Exception e)
        {
            logger.info("Error sending mail: "+ e.getMessage());
        }
        modelAndView.setViewName("holidayLeave/three");
        return modelAndView;
    }
    @RequestMapping(value = "create", params = "form", method = RequestMethod.GET)
    public ModelAndView createForm(ModelAndView modelAndView) {
        List<HolidayLeave> holidayLeaveList = holidayLeaveRepository.findAll();
        modelAndView.addObject("holidayLeaveList", holidayLeaveList);
        modelAndView.addObject("holidayLeaveCreateForm", new HolidayLeaveCreateForm());
        modelAndView.setViewName("/holidayLeave/create");
        return modelAndView;
    }
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ModelAndView create(ModelAndView modelAndView, @Validated HolidayLeaveCreateForm holidayLeaveCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<HolidayLeave> holidayLeaveList = holidayLeaveRepository.findAll();
            modelAndView.addObject("holidayLeaveList", holidayLeaveList);
            modelAndView.setViewName("/holidayLeave/create");
            return modelAndView;
        } else {
            holidayLeaveService.save(holidayLeaveCreateForm);
            modelAndView.setViewName("redirect:/holidayLeave/three");
            return modelAndView;
        }
    }
    @RequestMapping(value = "delete/{holId}", method = RequestMethod.GET)
    public String delete(@PathVariable Integer holId) {
        holidayLeaveService.delete(holId);
        return "redirect:/holidayLeave/three";
    }
}