/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.datacredito;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author German
 */
public class Persona implements Serializable {
    
    private int identificacion;
    private String nombre;
    private String apellido;
    private String correo;
    private HashMap<Integer, Record> listaReportes;

    public Persona(int identificacion, String nombre, String apellido, String correo) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        listaReportes = new HashMap<>();
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public HashMap<Integer, Record> getListaReportes() {
        return listaReportes;
    }

    public void setListaReportes(HashMap<Integer, Record> listaReportes) {
        this.listaReportes = listaReportes;
    }
    
}
