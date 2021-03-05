package sergiu.voloc.PaintByNumbers.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempController {


    @GetMapping
    String getVar(Model model){
        model.addAttribute("myVar", "Thymeleaf power!");
        return "TempController";
    }
}
