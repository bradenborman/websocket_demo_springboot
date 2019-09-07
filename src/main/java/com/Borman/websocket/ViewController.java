package com.Borman.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class ViewController {

    @GetMapping("")
    public String greeting() {
        return "home";
    }

}
