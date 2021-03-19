package sergiu.voloc.PaintByNumbers.Controller;

import sergiu.voloc.PaintByNumbers.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("file")
public class FileStorageController {
    
    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/")
    public String index() {
        return "/pages/homepage/index";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {

        fileStorageService.uploadFile(file);

        return "redirect:/";
    }


}
