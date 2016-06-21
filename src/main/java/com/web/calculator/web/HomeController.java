package com.web.calculator.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by oleg.bezkorovaynyi on 17 Jun 2016.
 */

@Controller
public class HomeController {
    @RequestMapping("/home")
    public String home() {
        return "index";
    }
}
