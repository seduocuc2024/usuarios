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

    @Override
    public Usuario createUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario updateUsuario(Long id, Usuario usuario){
        if(usuarioRepository.existsById(id)){
            usuario.setId(id);
            return usuarioRepository.save(usuario);
        }else{
            return null;
        }
    }

    @Override
    public void deleteUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

}
