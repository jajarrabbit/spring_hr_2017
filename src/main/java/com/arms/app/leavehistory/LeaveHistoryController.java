package com.arms.app.leavehistory;

import com.arms.domain.entity.CompDetail;
import com.arms.domain.entity.Employee;
import com.arms.domain.entity.LeaveHistory;
import com.arms.domain.entity.LeaveType;
import com.arms.domain.repository.EmployeeRepository;
import com.arms.domain.repository.LeaveHistoryRepository;
import com.arms.domain.repository.LeaveTypeRepository;
import com.arms.domain.service.ExportPdfService;
import com.arms.domain.service.LeaveHistoryService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by arms20170106 on 16/1/2560.
 */
@Controller
@RequestMapping("leaveHistory")
public class LeaveHistoryController {
    @Autowired
    LeaveHistoryRepository leaveHistoryRepository;
    @Autowired
    ExportPdfService exportPdfService;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    LeaveHistoryService leaveHistoryService;
    @Autowired
    LeaveTypeRepository leaveTypeRepository;


    @ModelAttribute
    LeaveHistorySearch setLeaveHistorySearch() {
        return new LeaveHistorySearch();
    }

    @ModelAttribute
    LeaveHistoryForm setLeaveHistoryForm() {
        return new LeaveHistoryForm();
    }

    @ModelAttribute
    LeaveHistoryDetailForm setLeaveHiistoryDetailForm() {
        return new LeaveHistoryDetailForm();
    }

    @ModelAttribute
    LeaveHistoryEditForm setLeaveHistoryEditForm() {
        return new LeaveHistoryEditForm();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView blankList(ModelAndView modelAndView) {
        List<LeaveHistory> leaveHistoryList = leaveHistoryRepository.findAll();
        List<Employee> employeeList = employeeRepository.findAll();
        modelAndView.addObject("employeeList", employeeList);
        modelAndView.addObject("leaveHistoryList", leaveHistoryList);
        modelAndView.addObject("leaveHistorySearch", new LeaveHistorySearch());
        modelAndView.setViewName("/leaveHistory/list");
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ModelAndView showList(ModelAndView modelAndView, LeaveHistorySearch leaveHistorySearch) {
        List<Employee> employeeList = employeeRepository.findAll();
        modelAndView.addObject("employeeList", employeeList);
        List<LeaveHistory> leaveSearchList = leaveHistoryRepository.findAllByLeaveHistory(leaveHistorySearch.getEmpId(), leaveHistorySearch.getPeriodFrom(), leaveHistorySearch.getPeriodUntil());
        modelAndView.addObject("leaveHistoryList", leaveSearchList);
        modelAndView.setViewName("/leaveHistory/list");
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @RequestMapping(value = "detail/{leaveId}", method = RequestMethod.GET)
    public ModelAndView showDetail(@PathVariable Integer leaveId, ModelAndView modelAndView) throws IOException {

            try {
                BufferedImage originalImage = ImageIO.read(new File("/home/arms20170106/Projects/spring_hr_2017/src/main/resources/static/imageNoti/" + leaveId + ".png"));
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(originalImage, "png", baos);
                baos.flush();
                baos.close();

                String imageString = "data:image/png;base64," +
                        DatatypeConverter.printBase64Binary(baos.toByteArray());
                modelAndView.addObject("imageString", imageString);
            }catch(Exception e)
            {

            }
        modelAndView.addObject("leaveHistoryDetailForm",leaveHistoryService.getHistoryDetailByLeaveId(leaveId));
        modelAndView.setViewName("leaveHistory/detail");
        return modelAndView;
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "edit/{leaveId}", method = RequestMethod.GET)
    public ModelAndView showEdit(@PathVariable Integer leaveId, ModelAndView modelAndView) {
        modelAndView.addObject("leaveHistoryEditForm", leaveHistoryService.getHistoryEditByLeaveId(leaveId));
        List<LeaveType> leaveTypeList = leaveTypeRepository.findAll();
        modelAndView.addObject("leaveTypeList", leaveTypeList);
        List<Employee> employeeList = employeeRepository.findAll();
        modelAndView.addObject("employeeList", employeeList);
        modelAndView.setViewName("leaveHistory/edit");
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ModelAndView edit(ModelAndView modelAndView, @Validated LeaveHistoryEditForm leaveHistoryEditForm, BindingResult bindingResult, Integer leaveId) {
        if (leaveHistoryService.checkDateEdit(leaveHistoryEditForm) == 2) {
            bindingResult.rejectValue("periodFrom", "messageError", "Please Check your date");
        }
        if (leaveHistoryService.checkAmountEdit(leaveHistoryEditForm) == 1) {
            bindingResult.rejectValue("fullday", "messageError", "Please check leave amount");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("leaveHistoryEditForm", leaveHistoryService.getHistoryEditByLeaveId(leaveId));
            List<LeaveType> leaveTypeList = leaveTypeRepository.findAll();
            modelAndView.addObject("leaveTypeList", leaveTypeList);
            List<Employee> employeeList = employeeRepository.findAll();
            modelAndView.addObject("employeeList", employeeList);
            modelAndView.setViewName("leaveHistory/edit");
            return modelAndView;
        } else {
            leaveHistoryService.update(leaveHistoryEditForm);
            modelAndView.setViewName("redirect:/leaveHistory/list");
            return modelAndView;
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "delete/{leaveId}", method = RequestMethod.GET)
    public String delete(@PathVariable Integer leaveId) throws Exception {
        leaveHistoryService.delete(leaveId);
        return "redirect:/leaveHistory/list";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "create", params = "form", method = RequestMethod.GET)
    public ModelAndView createForm(ModelAndView modelAndView) {
        List<LeaveType> leaveTypeList = leaveTypeRepository.findAll();
        List<Employee> employeeList = employeeRepository.findAll();
        modelAndView.addObject("employeeList", employeeList);
        modelAndView.addObject("leaveTypeList", leaveTypeList);
        modelAndView.addObject("leaveHistoryForm", new LeaveHistoryForm());
        modelAndView.setViewName("/leaveHistory/create");
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ModelAndView create( ModelAndView modelAndView, @Validated LeaveHistoryForm leaveHistoryForm, BindingResult bindingResult, Integer empId, CompDetail compDetail) throws Exception {
        if (leaveHistoryService.checkDateInput(leaveHistoryForm) == 2) {
            bindingResult.rejectValue("periodFrom", "messageError", "Period From must be Greater Than Until");
        }
        if (leaveHistoryService.checkAmount(leaveHistoryForm) == 1) {
            bindingResult.rejectValue("fullday", "messageError", "Please check leave amount");
        }
        if (leaveHistoryService.checkLeave(leaveHistoryForm) == 1) {
            bindingResult.rejectValue("categoryId", "messageError", "Please check Business Leave Amount");
        }
        if (leaveHistoryService.checkLeave(leaveHistoryForm) == 2)
        {
            bindingResult.rejectValue("categoryId", "messageError", "Please check Compensation Day Amount");
        }
        if (bindingResult.hasErrors()) {
            List<LeaveType> leaveTypeList = leaveTypeRepository.findAll();
            List<Employee> employeeList = employeeRepository.findAll();
            modelAndView.addObject("employeeList", employeeList);
            modelAndView.addObject("leaveTypeList", leaveTypeList);
            modelAndView.setViewName("/leaveHistory/create");
            return modelAndView;
        } else {
            leaveHistoryService.save(leaveHistoryForm);
//            leaveHistoryService.sendMail();

            modelAndView.setViewName("redirect:/leaveHistory/list");
            return modelAndView;
        }
    }


    @ResponseBody
    @RequestMapping(value = "getHireDate")
    Map<String, String> getHireDate(@RequestParam String empId) throws Exception {
        Map<String, String> returnMap = new LinkedHashMap<String, String>();
        returnMap = leaveHistoryService.getHireDate(empId);
        return returnMap;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @RequestMapping(value = "exportPdf/{leaveId}", method = RequestMethod.GET)
    public ModelAndView exportPdf(@PathVariable("leaveId") Integer leaveId) {
        return new ModelAndView(exportPdfService.getJasperPdfView(leaveId), new HashedMap());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "approve/{leaveId}", method = RequestMethod.GET)
    public ModelAndView approve(@PathVariable Integer leaveId,ModelAndView modelAndView) throws Exception {
        leaveHistoryService.approved(leaveId);
        leaveHistoryService.sendMail(leaveId);
     modelAndView.setViewName("redirect:/leaveHistory/list");
     return modelAndView;
    }
}
