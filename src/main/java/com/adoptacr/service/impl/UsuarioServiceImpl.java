package com.adoptacr.service.impl;

import com.adoptacr.dao.UsuarioDao;
import com.adoptacr.domain.Usuario;
import com.adoptacr.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public Usuario login(String correo, String contrasena) {
        Usuario usuario = usuarioDao.findByCorreo(correo);
        if (usuario != null && passwordEncoder.matches(contrasena, usuario.getContrasena())) {
            return usuario;
        }
        return null;
    }

    @Override
    @Transactional
    public Usuario registrar(Usuario usuario) throws Exception {
        // Validaciones simples
        if (usuario.getCorreo() == null || usuario.getCorreo().isBlank()) {
            throw new Exception("El correo es obligatorio");
        }
        if (usuario.getContrasena() == null || usuario.getContrasena().isBlank()) {
            throw new Exception("La contraseña es obligatoria");
        }

        // Verificar si ya existe el correo
        Usuario existente = usuarioDao.findByCorreo(usuario.getCorreo());
        if (existente != null) {
            throw new Exception("Ya existe un usuario con ese correo");
        }

        // Encriptar la contraseña antes de guardar
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));

        return usuarioDao.save(usuario);
    }
}
