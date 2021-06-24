package com.ecommerce.ecommerce.modules.product;

import com.ecommerce.ecommerce.modules.cartItem.CartItemService;
import com.ecommerce.ecommerce.modules.fileStorage.FileStorageService;
import com.ecommerce.ecommerce.modules.pbn.PBNService;
import com.ecommerce.ecommerce.modules.user.UserService;
import com.ecommerce.ecommerce.modules.utils.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@Controller
@RequestMapping("/personalize")
public class PersonalizeController {

    @Autowired
    private ProductServiceImpl productServiceImpl;
    @Autowired
    UserService userService;
    @Autowired
    CartItemService cartItemService;
    @Autowired
    FileStorageService fileStorageService;
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    PBNService pbnService;


    @GetMapping(value ="/{id}")
    public String indexPage(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("product", productServiceImpl.read(id));
        return "pages/personalizedImages/index";
    }


//    @PostMapping("/personalize/c6a08882-212f-431b-a60f-8e5189c77381")
//    public String uploadImage(@RequestParam("imageFile") MultipartFile imageFile) throws Exception {
//        String returnValue="start";
//        try{
//            productServiceImpl.saveUploadedImage((List<MultipartFile>) imageFile);
//        }catch (Exception e){
//            e.printStackTrace();
//            log.error("Error saving photo",e);
//            returnValue = "error";
//        }
//
//        return returnValue;
//    }





//    @PostMapping("/c6a08882-212f-431b-a60f-8e5189c77381")
//    public String addPersonalizedItemToCart(
//            @RequestParam String name,
//            @RequestParam(value = "size") String size,
//            @RequestParam(value = "qty") Integer quantity,
//            @RequestParam(value = "frame") Boolean frame,
//            @RequestParam(value = "file") MultipartFile imageFile,
//            @RequestParam float total
//    ) throws IOException {
//
//        pbnService.create(quantity, total, name, frame, size, imageFile);
//
//        return "redirect:/cart";
//    }



    @PostMapping()
    public String addPersonalizedItemToCart(
            @RequestParam String name,
            @RequestParam String size,
            @RequestParam Integer quantity,
            @RequestParam Boolean frame,
            @RequestParam MultipartFile imageFile,
            @RequestParam float total
    ) throws IOException {

        pbnService.create(quantity, total, name, frame, size, imageFile);

        return "redirect:/cart";
    }



//    @PostMapping("/addProduct")
//    public String addProduct(
//            @RequestParam("pid") UUID pid,
//            @RequestParam("qty") Integer qty,
//            @RequestParam("total") float total,
//            @RequestParam Boolean frame,
//            @RequestParam String size,
//            @RequestParam("imageFile") MultipartFile imageFile
//    ){
//        User user = userService.getCurrent();
//        Product p = productServiceImpl.read(pid);
//        FileStorage pbn = fileStorageService.uploadFile(imageFile);
//        cartItemService.create(new CartItem(qty, user, p, pbn, total, frame, size));
//
//
//
//        return "redirect:/cart";
//    }





}
