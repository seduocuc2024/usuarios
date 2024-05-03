package com.usuarios.aplicacion.repository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.usuarios.aplicacion.model.Usuario;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void guardarUsuarioTest(){
        //arrange
        Usuario usuario = new Usuario();
        usuario.setNombre("John Doe");

        //Act
        Usuario resultado = usuarioRepository.save(usuario);

        //Assert
        assertNotNull(resultado.getId());
        assertEquals("John Doe", resultado.getNombre());
    }
}
