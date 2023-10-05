package com.fromis.fromis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlaceController {

    @GetMapping("/place")
    public String placeView(){

        return "/place/place";
    }
}
