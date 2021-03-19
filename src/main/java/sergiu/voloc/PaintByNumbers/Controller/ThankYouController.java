package sergiu.voloc.PaintByNumbers.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThankYouController {


    @GetMapping("/thankYou")
    public String getThankYouPage(){
        return  "pages/basket/thankYou";
    }

}
