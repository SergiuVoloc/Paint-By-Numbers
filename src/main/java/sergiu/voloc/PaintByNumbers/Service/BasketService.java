package sergiu.voloc.PaintByNumbers.Service;

import sergiu.voloc.PaintByNumbers.Model.Basket_Item;
import sergiu.voloc.PaintByNumbers.Repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private BasketService basketService;
    @Autowired
    private ProductPhotoService productPhotoService;


    public Iterable<Basket_Item> all(){
        return basketRepository.findAll();
    }

    public Basket_Item getById( UUID id){
        return basketRepository.findById(id).orElseThrow();
    }



}
