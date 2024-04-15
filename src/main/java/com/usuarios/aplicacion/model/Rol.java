package com.usuarios.aplicacion;

public class Rol {
    private int id;
    private String nombre;

    public Rol (int id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

    //@Getters

    public int getIdRol(){
        return id;
    }

    public String getNombreRol(){
        return nombre;
    }
}
