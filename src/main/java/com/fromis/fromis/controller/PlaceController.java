package com.fromis.fromis.controller;

import com.fromis.fromis.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlaceController {
    @Autowired
    private PlaceService placeService;

    @GetMapping("/place")
    public String placeView(){

        return "/place/place";
    }
}
