package com.adoptacr.controller;

import com.adoptacr.domain.Usuario;
import com.adoptacr.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping({"/", "/login"})
    public String mostrarLogin() {
        return "usuario/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String correo,
                        @RequestParam String contrasena,
                        Model model) {
        Usuario usuario = usuarioService.login(correo, contrasena);
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
            return "redirect:/mascotas/listado";
        } else {
            model.addAttribute("error", "Credenciales inválidas");
            return "usuario/login";
        }
    }
}
