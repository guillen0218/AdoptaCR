package com.adoptacr.controller;

import com.adoptacr.domain.Usuario;
import com.adoptacr.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario/registro";
    }

    @PostMapping("/registro")
    public String registrar(Usuario usuario, String confirmarContrasena, Model model) {
        try {
            // Validación básica: contraseñas coinciden
            if (confirmarContrasena == null || !confirmarContrasena.equals(usuario.getContrasena())) {
                model.addAttribute("error", "Las contraseñas no coinciden");
                model.addAttribute("usuario", usuario);
                return "usuario/registro";
            }

            usuarioService.registrar(usuario);
            model.addAttribute("success", "Registro exitoso. Ya puedes iniciar sesión.");
            return "usuario/login"; // mostrar login con mensaje de éxito

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("usuario", usuario);
            return "usuario/registro";
        }
    }
}
