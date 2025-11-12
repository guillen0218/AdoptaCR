package com.adoptacr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoController {

    @GetMapping("/nuestra-historia")
    public String historia(Model model) {
        model.addAttribute("title", "Nuestra Historia");
        return "informacion/historia";
    }
}
