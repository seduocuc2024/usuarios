package com.usuarios.aplicacion.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.usuarios.aplicacion.model.Usuario;
import com.usuarios.aplicacion.repository.UsuarioRepository;


@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioServicelmpl usuarioServicelmpl;

    @Mock
    private UsuarioRepository usuarioRepositorioMock;

    @Test
    public void guardarUsuarioTest(){
        // Arrange
        Usuario  usuario = new Usuario();
        usuario.setNombre("Jose Rondon");

        when(usuarioRepositorioMock.save(any())).thenReturn(usuario);

        // Act
        Usuario resultado = usuarioServicelmpl.createUsuario(usuario);

        // Assert
        assertEquals("Jose Rondon", resultado.getNombre());
    }

    //Otras pruebas para el servicio
}
