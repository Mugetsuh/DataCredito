/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.datacredito;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author German
 */
public class Archivo {
    public HashMap<Integer,Persona> listaPersona;
    private static final String ruta = "archivo.txt";

    
    public Archivo() {
       validarArchivo();
    }

    private void validarArchivo(){
     try {
            
            File file = new File(ruta);
           
            if (!file.exists()) {
                file.createNewFile();
                listaPersona=new HashMap<>();
                guardarRegistro(listaPersona);
               
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
     
    
    }
 
    public HashMap<Integer,Persona> obtenerListasActuales() {
        listaPersona=new HashMap<>();
       
        try {
            ObjectInputStream entrada =  new ObjectInputStream(new FileInputStream(ruta));
            listaPersona = (HashMap<Integer,Persona>) entrada.readObject();
  
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } 
        
        return listaPersona;
    }
 
    
       public void guardarRegistro(HashMap<Integer,Persona> lista) {
           
        try {
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ruta));
            salida.writeObject(lista);
            salida.close();
        } catch (IOException ex) {
            System.err.println("Error!!!!!");
            ex.printStackTrace();
        }
    }
}
