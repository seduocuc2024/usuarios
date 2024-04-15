package com.usuarios.aplicacion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "email")
    private String email;

    @Column(name = "rol_nombre")
    private String rolNombre;

    @Column(name = "rol_id")
    private int rolId;

    @Column(name = "direccion_calle")
    private String direccionCalle;

    @Column(name = "direccion_ciudad")
    private String direccionCiudad;

    @Column(name = "direccion_codigo_postal")
    private String direccionCodigoPostal;

    //@Getters

    public Long getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public String getEmail(){
        return email;
    }

    public String getRolNombre(){
        return rolNombre;
    }

    public int getRolId(){
        return rolId;
    }

    public String getDireccionCalle(){
        return direccionCalle;
    }

    public String getDireccionCiudad(){
        return direccionCiudad;
    }

    public String getDireccionCodigoPostal(){
        return direccionCodigoPostal;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setRolNombre(String rolNombre){
        this.rolNombre = rolNombre;
    }

    public void setRolId(int rolId){
        this.rolId = rolId;
    }

    public void setDireccionCalle(String direccionCalle){
        this.direccionCalle = direccionCalle;
    }

    public void setDireccionCiudad(String direccionCiudad){
        this.direccionCiudad = direccionCiudad;
    }

    public void setDireccionCodigoPostal(String direccionCodigoPostal){
        this.direccionCodigoPostal = direccionCodigoPostal;
    }
}
