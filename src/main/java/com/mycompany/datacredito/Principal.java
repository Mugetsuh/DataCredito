/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.datacredito;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author German
 */
public class Principal implements Serializable {

    private HashMap<Integer, Persona> listaPersonas = new HashMap<>();

    public Principal() {
        Archivo archivo = new Archivo();
        listaPersonas = archivo.obtenerListasActuales();
    }

    public void principal() {

        Scanner sc = new Scanner(System.in);
        boolean valida = true;
        int opcion = 0;
        do {
            System.out.println("DataCredito");
            System.out.println("1. Agregar personas.");
            System.out.println("2. Agregar reporte.");
            System.out.println("3. Eliminar reporte.");
            System.out.println("4. Listar.");

            do {
                try {
                    opcion = sc.nextInt();
                } catch (NumberFormatException e) {
                    System.err.println("Numero inválido");
                    valida = false;
                }
            } while (!valida);

            switch (opcion) {
                case 1:
                    agregarPersonas();
                    break;
                case 2:
                    agregarReporte();
                case 3:
                    eliminarReporte();
                case 4:
                    listarPersonas();
            }
        } while (opcion < 5);
        System.out.println("Opcion Incorrecta!!!!!");
    }

    private void agregarPersonas() {

        Scanner sc = new Scanner(System.in);
        int documento;
        String nombre, apellido, correo;
        Archivo archivo = new Archivo();

        System.out.println("Documento: ");
        documento = sc.nextInt();
        System.out.println("Nombre: ");
        nombre = sc.next();
        System.out.println("Apellido: ");
        apellido = sc.next();
        System.out.println("Correo: ");
        correo = sc.next();
        Persona persona = new Persona(documento, nombre, apellido, correo);
        listaPersonas.put(documento, persona);
        archivo.guardarRegistro(listaPersonas);
    }

    private void agregarReporte() {
        Scanner sc = new Scanner(System.in);
        int documento;
        System.out.println("Documento: ");
        documento = sc.nextInt();
        if (listaPersonas.containsKey(documento)) {
            agregar(documento);
        } else {
            System.out.println("Persona no encontrada.");
        }
    }

    private void eliminarReporte() {
        
    }

    private void listarPersonas() {
        Archivo archivo = new Archivo();
        listaPersonas = archivo.obtenerListasActuales();
        for (Integer persona : listaPersonas.keySet()) {
            System.out.println(listaPersonas.get(persona).getIdentificacion()
                    + " " + listaPersonas.get(persona).getNombre() + " "
                    + listaPersonas.get(persona).getApellido() + " " + listaPersonas.get(persona).getCorreo());
            try {
                HashMap<Integer, Record> listaRecord = listaPersonas.get(persona).getListaReportes();
                for (Integer key : listaRecord.keySet()) {
                    System.out.println(listaRecord.get(key).getCodigo() + " " + listaRecord.get(key).getEmpresa() + " "
                            + listaRecord.get(key).getDescripcion() + " " + listaRecord.get(key).isEstado() + " " + listaRecord.get(key).getValor());
                }
            } catch (Exception e) {
                System.err.println("No hay reportes de esta persona.");
            }
        }
    }

    private void agregar(int documento) {
        Scanner sc = new Scanner(System.in);
        int codigo;
        double valor;
        boolean estado;
        String descripcion, empresa;
        Archivo archivo = new Archivo();
        System.out.println("Codigo: ");
        codigo = sc.nextInt();
        System.out.println("Empresa: ");
        empresa = sc.next();
        System.out.println("Descripcion: ");
        descripcion = sc.next();
        System.out.println("Estado: ");
        estado = sc.nextBoolean();
        System.out.println("Valor: ");
        valor = sc.nextDouble();

        Record record = new Record(codigo, empresa, descripcion, estado, valor);
        listaPersonas.get(documento).getListaReportes().put(codigo, record);        
        archivo.guardarRegistro(listaPersonas);
    }

}
