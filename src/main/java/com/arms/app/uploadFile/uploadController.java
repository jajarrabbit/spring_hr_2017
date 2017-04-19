package com.arms.app.uploadFile;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.io.*;
import java.nio.file.Paths;
/**
 * Created by arms20170106 on 7/4/2560.
 */
@Controller
public class uploadController {


    @RequestMapping(value = "/uploadFile/{leaveId}", method = RequestMethod.GET)
    public ModelAndView uploadFile(@PathVariable Integer leaveId, ModelAndView modelAndView)  {
        modelAndView.addObject("leaveId",leaveId);
        modelAndView.setViewName("uploadFile");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/uploadFile/{leaveId}", method = RequestMethod.POST)
    public ResponseEntity<?> uploadFile(@RequestParam("uploadfile") MultipartFile uploadfile,@PathVariable Integer leaveId) {
        try {
            String filename = leaveId.toString()+".png";
            String directory = "/home/arms20170106/Projects/spring_hr_2017/src/main/resources/static/imageNoti/";
            String filepath = Paths.get(directory, filename).toString();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
            stream.write(uploadfile.getBytes());
            stream.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
