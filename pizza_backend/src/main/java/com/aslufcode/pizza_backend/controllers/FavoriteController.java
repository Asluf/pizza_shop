package com.aslufcode.pizza_backend.controllers;

import com.aslufcode.pizza_backend.dao.FavoriteDao;
import com.aslufcode.pizza_backend.models.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteDao favoriteDAO;

    @PostMapping("/add")
    public boolean addFavorite(@RequestBody Favorite favorite) {
        return favoriteDAO.addFavorite(favorite);
    }

    @GetMapping("/{userEmail}")
    public List<Favorite> getFavorites(@PathVariable String userEmail) {
        return favoriteDAO.getFavoritesByUser(userEmail);
    }

    @DeleteMapping("/remove/{userEmail}/{pizzaName}")
    public boolean removeFavorite(@PathVariable String userEmail, @PathVariable String pizzaName) {
        return favoriteDAO.removeFavorite(userEmail, pizzaName);
    }
}
