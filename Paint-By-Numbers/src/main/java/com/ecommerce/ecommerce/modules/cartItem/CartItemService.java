package com.ecommerce.ecommerce.modules.cartItem;

import com.ecommerce.ecommerce.modules.fileStorage.FileStorageService;
import com.ecommerce.ecommerce.modules.product.Product;
import com.ecommerce.ecommerce.modules.user.User;
import com.ecommerce.ecommerce.modules.user.UserServiceImpl;
import com.ecommerce.ecommerce.modules.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ObjectUtils objectUtils;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    FileStorageService fileStorageService;

    public Iterable<CartItem> all(){
        return cartItemRepository.findAll();
    }


    public CartItem read(UUID id){
        return cartItemRepository.findById(id).orElseThrow();
    }


    public void create(CartItem a){
//        CartItem q = productExistInCart(a.getProduct().getId());
//        if ( q != null){
//            q.setQty(q.getQty() + a.getQty());
//            q.setPbn(null);
//            cartItemRepository.save(q);
//        }
//
//        CartItem q1 = pbnExistInCart(a.getPbn().getId());
//        if ( q1 != null) {
//            q1.setProduct(null);
//            q1.setQty(q1.getQty() + a.getQty());
//            cartItemRepository.save(q1);
//        }

        cartItemRepository.save(a);
    }



    public CartItem create(int quantity, User user, UUID productId, Boolean frame, MultipartFile imageFile, String size){
        CartItem cartItem = new CartItem();
        Product theProduct = new Product();
        cartItem.setQty(quantity);
        cartItem.setUser(user);
//        cartItem.getProduct().setId(productId);
        theProduct.setId(productId);
        cartItem.setFrame(frame);
        cartItem.setSize(size);
        cartItem.addFile(fileStorageService.uploadFile(imageFile));


        // Image processing method
//        pbnUtils.process(cartItem.getFiles().get(0).getPath());

        return cartItem;
    }


    public CartItem update(UUID id, CartItem o) throws IllegalAccessException, InstantiationException {
        CartItem c = cartItemRepository.findById(id).orElseThrow();
        cartItemRepository.save((CartItem) objectUtils.update(c,o));
        return c;
    }

    public ArrayList<CartItem> findAllByUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return cartItemRepository.findAllByUser_Id(userServiceImpl.findByUsername(auth.getName()).getId());
    }


    public float total() {
        return findAllByUser().stream()
                .map(CartItem::getSubtotal)
                .reduce(0.0f, Float::sum);

    }


    public CartItem productExistInCart(UUID pid){
        User user = userServiceImpl.getCurrent();
        return cartItemRepository.findByProduct_IdAndUser_Id(pid, user.getId());
    }
    public CartItem pbnExistInCart(UUID pid){
        User user = userServiceImpl.getCurrent();
        return cartItemRepository.findByPbn_IdAndUser_Id(pid, user.getId());
    }


    public void addQty(UUID id){
        CartItem c = cartItemRepository.findById(id).orElseThrow();
        c.setQty(c.getQty() + 1);
        cartItemRepository.save(c);
    }


    public void decreaseQty(UUID id){
        CartItem c = cartItemRepository.findById(id).orElseThrow();
        if(c.getQty() == 1){
            this.delete(c.getId());
        } else {
            c.setQty(c.getQty() - 1);
            cartItemRepository.save(c);
        }
    }

    public void delete(UUID id){
        cartItemRepository.deleteById(id);
    }

    public void clear(){
        Iterable<CartItem> list = all();
        list.forEach((e)-> {
            delete(e.getId());
        });
    }

}
