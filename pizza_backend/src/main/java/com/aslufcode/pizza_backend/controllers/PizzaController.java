package com.aslufcode.pizza_backend.controllers;

import com.aslufcode.pizza_backend.dao.PizzaDao;
import com.aslufcode.pizza_backend.models.Pizza;
import com.aslufcode.pizza_backend.models.PizzaBuilder;
import com.aslufcode.pizza_backend.models.DTO.PizzaRequest;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    @PostMapping("/customize")
    public Pizza calculateCustomPizza(@RequestBody PizzaRequest pizzaRequest) {
        PizzaBuilder builder = new PizzaBuilder()
                .setName(pizzaRequest.getName())
                .setCrust(pizzaRequest.getCrust())
                .setSauce(pizzaRequest.getSauce())
                .setCheese(pizzaRequest.getCheese());

        for (String topping : pizzaRequest.getToppings()) {
            builder.addTopping(topping);
        }

        return builder.build();
    }

    @GetMapping("/all")
    public List<Pizza> getAllPizzas() {
        return PizzaDao.getAllPizzas();
    }

    @GetMapping("/offers")
    public List<Pizza> getAllOffers() {
        return PizzaDao.getAllOffers();
    }

}
