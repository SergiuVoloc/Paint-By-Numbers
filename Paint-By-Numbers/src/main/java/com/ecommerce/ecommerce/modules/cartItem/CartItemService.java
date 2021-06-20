package com.ecommerce.ecommerce.modules.cartItem;

import com.ecommerce.ecommerce.modules.user.UserService;
import com.ecommerce.ecommerce.modules.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ObjectUtils objectUtils;
    @Autowired
    private UserService userService;

    public Iterable<CartItem> all(){
        return cartItemRepository.findAll();
    }

    public CartItem read(UUID id){
        return cartItemRepository.findById(id).orElseThrow();
    }

    public void create(CartItem a){
        CartItem q = productExistInCart(a.getProduct().getId());
        if ( q!= null){
            q.setQty(q.getQty() + a.getQty());
            cartItemRepository.save(q);
        } else {
            cartItemRepository.save(a);
        }
    }

    public CartItem update(UUID id, CartItem o) throws IllegalAccessException, InstantiationException {
        CartItem c = cartItemRepository.findById(id).orElseThrow();
        cartItemRepository.save((CartItem) objectUtils.update(c,o));
        return c;
    }
    public ArrayList<CartItem> findAllByUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return cartItemRepository.findAllByUser_Id(userService.findByUsername(auth.getName()).getId());
    }

    public float total() {
        return findAllByUser().stream()
                .map(CartItem::getSubtotal)
                .reduce(0.0f, Float::sum);

    }


    public CartItem productExistInCart(UUID pid){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return cartItemRepository.findByProduct_IdAndUser_Id(pid, userService.findByUsername(auth.getName()).getId());
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
