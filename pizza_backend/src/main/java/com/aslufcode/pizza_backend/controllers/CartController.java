package com.aslufcode.pizza_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aslufcode.pizza_backend.dao.CartDao;
import com.aslufcode.pizza_backend.models.ApiResponse;
import com.aslufcode.pizza_backend.models.Cart;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartDao cartDAO;

    @PostMapping("/add")
    public boolean addToCart(@RequestBody Cart cart) {
        return cartDAO.addToCart(cart);
    }

    @GetMapping("/get/{userEmail}")
    public ResponseEntity<ApiResponse> getCartByUserEmail(@PathVariable String userEmail) {
        List<Cart> cartItems = cartDAO.getCartByUserEmail(userEmail);
        if (cartItems != null && !cartItems.isEmpty()) {
            return ResponseEntity.ok(new ApiResponse(true, "Cart items retrieved successfully!", cartItems));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse(false, "No cart items found for the user!"));
        }
    }

    @PutMapping("/update/{userEmail}/{pizzaName}")
    public ResponseEntity<ApiResponse> updateCartQuantity(@PathVariable String userEmail, @PathVariable String pizzaName, @RequestBody Cart cart) {
        boolean updated = cartDAO.updateCartQuantity(userEmail, pizzaName, cart.getQuantity());
        if (updated) {
            return ResponseEntity.ok(new ApiResponse(true, "Quantity updated successfully!"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse(false, "Failed to update quantity!"));
        }
    }

    @DeleteMapping("/remove/{userEmail}/{pizzaName}")
    public ResponseEntity<ApiResponse> removeCartItem(@PathVariable String userEmail, @PathVariable String pizzaName) {
        boolean removed = cartDAO.removeCartItem(userEmail, pizzaName);
        if (removed) {
            return ResponseEntity.ok(new ApiResponse(true, "Pizza removed from cart successfully!"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse(false, "Failed to remove pizza from cart!"));
        }
    }

}
