package com.usuarios.aplicacion;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class UsuarioController {

    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioController(){

        // Datos de prueba para Usuarios con Roles y Direcciones
        usuarios.add(new Usuario(1, "sepenap", "se.penap@duocuc.cl",  Arrays.asList(new Rol(1, "ADMIN")), Arrays.asList(new Direccion(1, "Calle 123", "Ciudad A", "12345"))));
        usuarios.add(new Usuario(2, "roferg", "ro.ferg@duocuc.cl",  Arrays.asList(new Rol(2, "USER")), Arrays.asList(new Direccion(2, "Avenida 456", "Ciudad B", "54321"))));
        usuarios.add(new Usuario(3, "mimellaa", "mi.mellaa@duocuc.cl",  Arrays.asList(new Rol(1, "ADMIN"),new Rol(2, "USER")), Arrays.asList(new Direccion(2, "Avenida 456", "Ciudad B", "54321"))));

        
    }
    //muestra todos los usuarios
    @GetMapping("/usuarios")
    public List<Usuario> obtenerUsuarios() {
        return usuarios;
    }

    //busca a un usuario por id
    @GetMapping("/usuarios/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    // filtro para mostrar todos los usuarios de la misma Ciudad, para reconocerlos por zona
    @GetMapping("/usuarios/direccion/{ciudad}")
    public List<Usuario> obtenerUsuariosPorDireccion(@PathVariable String ciudad) {
    List<Usuario> usuariosPorDireccion = new ArrayList<>();
    for (Usuario usuario : usuarios) {
        for (Direccion direccion : usuario.getDirecciones()) {
            if (direccion.getCiudadDirecciones().equalsIgnoreCase(ciudad)) {
                usuariosPorDireccion.add(usuario);
                break; // No es necesario seguir buscando en las direcciones del usuario
            }
        }
    }
    return usuariosPorDireccion;
    }

}
