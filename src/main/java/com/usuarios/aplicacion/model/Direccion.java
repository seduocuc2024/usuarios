package com.usuarios.aplicacion;

public class Direccion {
    private int id;
    private String calle;
    private String ciudad;
    private String codigoPostal;

    public Direccion (int id, String calle, String ciudad, String codigoPostal){
        this.id = id;
        this.calle = calle;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
    }

    //@Getters

    public int getIdDireccion(){
        return id;
    }

    public String getCalleDirecciones(){
        return calle;
    }

    public String getCiudadDirecciones(){
        return ciudad;
    }

    public String getCodigoPostal(){
        return codigoPostal;
    }
}
