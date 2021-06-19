//package com.ecommerce.ecommerce.modules.pbn;
//
//import com.ecommerce.ecommerce.modules.fileStorage.FileStorageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.UUID;
//
//@Controller
//@RequestMapping("/pbn")
//public class PBNController {
//
//    @Autowired
//    private PBNService pbnService;
//    @Autowired
//    private HttpServletRequest request;
//    @Autowired
//    private FileStorageService fileStorageService;
//
//
//    @GetMapping()
//    public String all(Model model){
//        model.addAttribute("list", pbnService.all());
//        return "pages/pbn/index";
//    }
//
//
//
//
////    @GetMapping(value ="/{id}")
////    public String read(@PathVariable(value = "id") UUID id, Model model){
////        model.addAttribute("item", productService.read(id));
////        return "pages/product/read";
////    }
//
//
//    @GetMapping("/create")
//    public  String addPage(Model model){
//        return "pages/pbn/create";
//    }
//
//
//
//    @PostMapping()
//    public String create(
//            @RequestParam String name,
//            @RequestParam MultipartFile file,
//            @RequestParam String size
//    ) throws IOException {
//        pbnService.create(name, size, file);
//        return "redirect:/pbn";
//    }
//
//
//
////    @GetMapping("/{id}/edit")
////    public String editPage(@PathVariable(value = "id") UUID id, Model model){
////        model.addAttribute("item", productServiceImpl.read(id));
////        model.addAttribute("categories", categoryService.all());
////        model.addAttribute("attributes", attributeService.all());
////        return "pages/product/edit";
////    }
////
////
////
////    @PostMapping("/{id}/edit")
////    public String update(
////            @PathVariable(value = "id") UUID id,
////            @RequestParam String name,
////            @RequestParam Float price,
////            @RequestParam String description,
////            @RequestParam List<String> categories,
////            @RequestParam List<MultipartFile> files
////    ){
////        productServiceImpl.update(
////                id,
////                name,
////                price,
////                description,
////                categories,
////                files
////        );
////        return "pages/product/edit";
////    }
////
////
//    @GetMapping("/{id}/delete")
//    public String delete(@PathVariable(value = "id") UUID id){
//        pbnService.delete(id);
//        return "redirect:/pbn";
//    }
////
////
////    @GetMapping("/{pid}/rFile/{fid}")
////    public String removeFile (@PathVariable(value = "pid") UUID pid,@PathVariable(value = "fid") UUID fid){
////        productServiceImpl.removeFile(pid, fid);
////        return "redirect:" + request.getHeader("Referer");
////    }
//
//
//}
