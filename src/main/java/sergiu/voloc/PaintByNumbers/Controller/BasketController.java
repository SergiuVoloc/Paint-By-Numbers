package sergiu.voloc.PaintByNumbers.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sergiu.voloc.PaintByNumbers.Service.BasketService;
import sergiu.voloc.PaintByNumbers.Service.ProductService;

@Controller
public class BasketController {

    @Autowired
    private BasketService basketService;


    @GetMapping("/basket")
    public String getBasket(){
        return "/pages/basket/basket";
    }


}
