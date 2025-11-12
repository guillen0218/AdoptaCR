package com.adoptacr.service;

import com.adoptacr.domain.Usuario;

public interface UsuarioService {
    Usuario login(String correo, String contrasena);
    Usuario registrar(Usuario usuario) throws Exception;
}
