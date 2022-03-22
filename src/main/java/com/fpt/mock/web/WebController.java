package com.fpt.mock.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping(value = {"/home", "/", ""})
    public String index() {
        return "index";
    }

}
