package com.adoptacr.controller;

import com.adoptacr.domain.Mascota;
import com.adoptacr.service.MascotaService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/mascotas")
public class MascotaController {

    private static final String UPLOAD_DIR = "uploads";

    @Autowired
    private MascotaService mascotaService;

    @GetMapping("/listado")
    public String listado(@RequestParam(required = false) String filtro, Model model) {
        if (filtro == null) filtro = "";
        model.addAttribute("filtro", filtro);
        model.addAttribute("mascotas", mascotaService.buscarMascotas(filtro));
        model.addAttribute("title", "Mascotas disponibles");
        return "mascotas/listado";
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("mascota", new Mascota());
        model.addAttribute("title", "Publicar Mascota");
        return "mascotas/nueva";
    }

    @PostMapping("/guardar")
    public String guardar(Mascota mascota, @RequestParam("foto") MultipartFile foto) throws IOException {
        if (foto != null && !foto.isEmpty()) {
            Files.createDirectories(Paths.get(UPLOAD_DIR));
            String original = foto.getOriginalFilename();
            String ext = "";
            if (original != null && original.contains(".")) {
                ext = original.substring(original.lastIndexOf('.'));
            }
            String filename = UUID.randomUUID().toString().replace("-", "") + ext;
            Path destino = Paths.get(UPLOAD_DIR, filename);
            Files.copy(foto.getInputStream(), destino);
            mascota.setFotoUrl("/uploads/" + filename);
        }
        if (mascota.getEstado() == null || mascota.getEstado().isBlank()) {
            mascota.setEstado("DISPONIBLE");
        }
        mascotaService.guardar(mascota);
        return "redirect:/mascotas/listado";
    }
}
