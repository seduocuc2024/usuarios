package com.usuarios.aplicacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usuarios.aplicacion.model.Usuario;
import com.usuarios.aplicacion.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicelmpl implements UsuarioService{
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }
}
