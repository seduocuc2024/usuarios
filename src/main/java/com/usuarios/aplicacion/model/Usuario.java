package com.usuarios.aplicacion;

import java.util.List;


public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private List<Rol> roles;
    private List<Direccion> direcciones;

    public Usuario(int id ,String nombre ,String email,List<Rol> roles,List<Direccion> direcciones ){
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.roles = roles;
        this.direcciones = direcciones;
    }

    //@Getter

    public int getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public String getEmail(){
        return email;
    }

    public List<Rol> getRoles(){
        return roles;
    }

    public List<Direccion> getDirecciones(){
        return direcciones;
    }

}
