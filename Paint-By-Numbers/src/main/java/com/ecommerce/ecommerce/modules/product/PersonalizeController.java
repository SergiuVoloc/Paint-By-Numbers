package com.ecommerce.ecommerce.modules.product;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/personalize")
public class PersonalizeController {

    @Autowired
    private ProductServiceImpl productServiceImpl;
    @Autowired
    private HttpServletRequest request;

    Logger log = (Logger) LoggerFactory.getLogger(this.getClass());


    @GetMapping(value ="/{id}")
    public String indexPage(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("product", productServiceImpl.read(id));
        return "pages/personalizedImages/index";
    }

    @PostMapping("/personalize/c6a08882-212f-431b-a60f-8e5189c77381")
    public String uploadImage(@RequestParam("imageFile") MultipartFile imageFile) throws Exception {
        String returnValue="start";
        try{
            productServiceImpl.saveUploadedImage((List<MultipartFile>) imageFile);
        }catch (Exception e){
            e.printStackTrace();
            log.error("Error saving photo",e);
            returnValue = "error";
        }

        return returnValue;
    }
}
