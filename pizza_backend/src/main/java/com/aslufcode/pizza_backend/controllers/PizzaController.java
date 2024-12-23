package com.aslufcode.pizza_backend.controllers;

import com.aslufcode.pizza_backend.models.Pizza;
import com.aslufcode.pizza_backend.models.PizzaBuilder;
import com.aslufcode.pizza_backend.models.DTO.PizzaRequest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    @PostMapping("/customize")
    public Pizza createCustomPizza(@RequestBody PizzaRequest pizzaRequest) {
        PizzaBuilder builder = new PizzaBuilder()
                .setName(pizzaRequest.getName())
                .setCrust(pizzaRequest.getCrust())
                .setSauce(pizzaRequest.getSauce());

        for (String topping : pizzaRequest.getToppings()) {
            builder.addTopping(topping);
        }

        return builder.build();
    }
}
