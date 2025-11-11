package com.adoptacr.service.impl;

import com.adoptacr.dao.UsuarioDao;
import com.adoptacr.domain.Usuario;
import com.adoptacr.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly = true)
    public Usuario login(String correo, String contrasena) {
        Usuario usuario = usuarioDao.findByCorreo(correo);
        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
            return usuario;
        }
        return null;
    }
}
