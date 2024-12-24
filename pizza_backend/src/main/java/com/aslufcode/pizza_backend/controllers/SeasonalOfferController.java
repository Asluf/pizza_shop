package com.aslufcode.pizza_backend.controllers;

import com.aslufcode.pizza_backend.dao.SeasonalOfferDao;
import com.aslufcode.pizza_backend.models.SeasonalOffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/seasonalOffers")
public class SeasonalOfferController {

    @Autowired
    private SeasonalOfferDao seasonalOfferDAO;

    @GetMapping("/all")
    public List<SeasonalOffer> getAllOffers() {
        return seasonalOfferDAO.getAllOffers();
    }
}
