package com.usuarios.aplicacion.controller;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.usuarios.aplicacion.model.Usuario;
import com.usuarios.aplicacion.service.UsuarioServicelmpl;


@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioServicelmpl usuarioServicioMock;

    @Test
    public void obtenerTodosTest() throws Exception {
        //Arrange
        //Creacion de medicos
        Usuario  usuario1 = new Usuario();
        usuario1.setNombre("John");
        usuario1.setId(1L);
        Usuario usuario2 = new Usuario();
        usuario2.setNombre("Doe");
        usuario2.setId(2L);
        List<Usuario> usuarios = Arrays.asList(usuario1,usuario2);
        when(usuarioServicioMock.getAllUsuarios()).thenReturn(usuarios);
        
        //Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.aMapWithSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuarioList[0].nombre", Matchers.is("John")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuarioList[1].nombre", Matchers.is("Doe")));

    }
}
