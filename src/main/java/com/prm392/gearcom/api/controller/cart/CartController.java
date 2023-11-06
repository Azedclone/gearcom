package com.prm392.gearcom.api.controller.cart;

import com.prm392.gearcom.api.model.RegistrationBody;
import com.prm392.gearcom.model.Cart;
import com.prm392.gearcom.model.User;
import com.prm392.gearcom.service.CartService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    public CartController(CartService _cartService) {
        this.cartService = _cartService;
    }


    @GetMapping
    public List<Cart> getCartByUserId(@AuthenticationPrincipal User user) {
       return  cartService.getCartByUserId(user.getId());
    }
    @PostMapping
    public ResponseEntity<Cart> addToCart(@AuthenticationPrincipal User user,@Valid @RequestBody Cart cart) {
        try {
            cartService.addToCart(user,cart);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @PostMapping("/remove")
    public ResponseEntity<Cart> removeFromCart(@AuthenticationPrincipal User user,@Valid @RequestBody Cart cart) {
        try {
            cartService.removeFromCart(user,cart);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @PutMapping
    public ResponseEntity<Cart> minusQuantity(@AuthenticationPrincipal User user,@Valid @RequestBody Cart cart) {
        try {
            cartService.minusQuantity(user,cart);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @GetMapping("/remove")
    public void removeCartByUserId(@AuthenticationPrincipal User user) {
        cartService.removeCartByUser(user);
    }
}
