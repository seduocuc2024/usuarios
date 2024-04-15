package com.usuarios.aplicacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.usuarios.aplicacion.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
