package com.adoptacr.controller;

import com.adoptacr.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @GetMapping("/listado")
    public String listado(@RequestParam(required = false) String filtro, Model model) {
        if (filtro == null) filtro = "";
        model.addAttribute("filtro", filtro);
        model.addAttribute("mascotas", mascotaService.buscarMascotas(filtro));
        return "mascotas/listado";
    }
}
