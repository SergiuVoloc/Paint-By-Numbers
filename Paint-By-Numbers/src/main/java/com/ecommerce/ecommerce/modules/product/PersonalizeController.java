package com.ecommerce.ecommerce.modules.product;

import ch.qos.logback.classic.Logger;
import com.ecommerce.ecommerce.modules.cartItem.CartItem;
import com.ecommerce.ecommerce.modules.cartItem.CartItemService;
import com.ecommerce.ecommerce.modules.fileStorage.FileStorage;
import com.ecommerce.ecommerce.modules.fileStorage.FileStorageService;
import com.ecommerce.ecommerce.modules.user.User;
import com.ecommerce.ecommerce.modules.user.UserService;
import com.ecommerce.ecommerce.modules.utils.EmailSenderService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


@Controller
@RequestMapping("/personalize")
public class PersonalizeController {

    @Autowired
    private ProductServiceImpl productServiceImpl;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    UserService userService;
    @Autowired
    CartItemService cartItemService;
    @Autowired
    FileStorageService fileStorageService;
    @Autowired
    EmailSenderService emailSenderService;

    Logger log = (Logger) LoggerFactory.getLogger(this.getClass());


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
//            @RequestParam(value = "pid") UUID productId,
//            @RequestParam(value = "size") String size,
//            @RequestParam(value = "qty") Integer quantity,
//            @RequestParam(value = "frame") Boolean frame,
//            @RequestParam(value = "imageFile") MultipartFile imageFile
//    ) throws IOException {
//        productId = UUID.fromString("c6a08882-212f-431b-a60f-8e5189c77381");
//        User user = userService.getCurrent();
//
////        cartItemService.create(quantity, user, productId, frame, imageFile, size);
//
//        return "redirect:/cart";
//    }


    //////    Actions
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
//        cartItemService.create(new CartItem(qty, user, p, total, pbn, frame, size));
//
//
//
//        return "redirect:/cart";
//    }





}
