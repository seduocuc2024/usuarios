package com.usuarios.aplicacion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "direccion")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "calle")
    private String calle;
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "codigoPostal")
    private String codigoPostal;

    //@Getters

    public int getId(){
        return id;
    }

    public String getCalle(){
        return calle;
    }

    public String getCiudad(){
        return ciudad;
    }

    public String getCodigoPostal(){
        return codigoPostal;
    }
}
