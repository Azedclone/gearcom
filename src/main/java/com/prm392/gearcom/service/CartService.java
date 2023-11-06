package com.prm392.gearcom.service;

import com.prm392.gearcom.model.Cart;
import com.prm392.gearcom.model.Product;
import com.prm392.gearcom.model.User;
import com.prm392.gearcom.repository.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService  {
    private  final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Cart> getCartByUserId(int id) {
        List<Cart> carts = new ArrayList<>();
        carts = cartRepository.findByUserId(id);
        return carts;
    }
    @Transactional
    public void addToCart(User user,Cart cart) {
        Cart c = cartRepository.findByUserIdAndProductId(user.getId(),cart.getProduct().getId());
        if(c != null)
        {
            c.setQuantity(c.getQuantity()+1);
            cartRepository.save(c);
        }
        else {
            cart.setUser(user);
            cartRepository.save(cart);
        }
    }
    @Transactional
    public void removeFromCart(User user,Cart cart) {
        Cart c = cartRepository.findByUserIdAndProductId(user.getId(),cart.getProduct().getId());
        if(c != null)
        {
            cartRepository.delete(c);
        }
    }
    @Transactional
    public void removeCartByUser(User user) {
        cartRepository.deleteByUserId(user.getId());
    }

    @Transactional
    public void minusQuantity(User user,Cart cart) {
        Cart c = cartRepository.findByUserIdAndProductId(user.getId(),cart.getProduct().getId());
        if(c != null)
        {
            c.setQuantity(c.getQuantity()-1);
            if(c.getQuantity() == 0) {
                cartRepository.delete(c);
            } else {
                cartRepository.save(c);
            }
        }
    }
}
