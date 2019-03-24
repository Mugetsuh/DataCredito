/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.datacredito;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author German
 */
public class Principal {

    private HashMap<Integer, Persona> listaPersonas = new HashMap<>();

    public Principal() {
        Archivo archivo = new Archivo();
        listaPersonas = archivo.obtenerListasActuales();
    }

    public void principal() {

        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            boolean valida = true;
            System.out.println("\n\n\n");
            System.out.println("DataCredito");
            System.out.println("1. Agregar personas.");
            System.out.println("2. Agregar reporte.");
            System.out.println("3. Eliminar reporte.");
            System.out.println("4. Listar personas y reportes.");

            //opcion = v.validaNumero(sc.next());
            while (valida) {
                try {
                    opcion = Integer.parseInt(sc.next());
                    valida = false;
                } catch (NumberFormatException e) {
                    System.err.println("Numero inválido");
                }
            }

            switch (opcion) {
                case 1:
                    agregarPersona();
                    break;
                case 2:
                    agregarReporte();
                    break;
                case 3:
                    eliminarReporte();
                    break;
                case 4:
                    listarPersonas();
                    break;
            }
        } while (opcion < 5);
        System.out.println("Opcion Incorrecta!!!!!");
    }

    private void agregarPersona() {

        Scanner sc = new Scanner(System.in);
        int documento = 0;
        String nombre, apellido, correo;
        Archivo archivo = new Archivo();
        boolean valida = true;

        while (valida) {
            System.out.println("Documento: ");
            try {
                documento = Integer.parseInt(sc.next());
                valida = false;
            } catch (NumberFormatException e) {
                System.err.println("Numero inválido");
            }
        }

        do {
            System.out.println("Nombre: ");
            nombre = sc.next();
        } while (validaLetras(nombre));

        do {
            System.out.println("Apellido: ");
            apellido = sc.next();
        } while (validaLetras(apellido));

        do {
            System.out.println("Correo: ");
            correo = sc.next();
        } while (validaCorreo(correo));

        Persona persona = new Persona(documento, nombre, apellido, correo);
        listaPersonas.put(documento, persona);
        archivo.guardarRegistro(listaPersonas);
    }

    private void agregarReporte() {
        Scanner sc = new Scanner(System.in);
        int documento = 0;
        boolean valida = true;
        while (valida) {
            System.out.println("Documento: ");
            try {
                documento = Integer.parseInt(sc.next());
                valida = false;
            } catch (NumberFormatException e) {
                System.err.println("Numero inválido");
            }
        }
        //documento = sc.nextInt();
        if (listaPersonas.containsKey(documento)) {
            agregar(documento);
        } else {
            System.out.println("Persona no encontrada.");
        }
    }

    private void eliminarReporte() {

        Scanner sc = new Scanner(System.in);
        Archivo archivo = new Archivo();
        int codUsuario = 0, codReporte = 0;
        boolean valida = true;
        while (valida) {
            System.out.println("Codigo del usuario:");
            try {
                codUsuario = Integer.parseInt(sc.next());
                valida = false;
            } catch (NumberFormatException e) {
                System.err.println("Numero inválido");
            }
        }
        if (listaPersonas.containsKey(codUsuario)) {
            valida = true;
            while (valida) {
                System.out.println("Codigo del reporte:");
                try {
                    codReporte = Integer.parseInt(sc.next());
                    valida = false;
                } catch (NumberFormatException e) {
                    System.err.println("Numero inválido");
                }
            }

            if (listaPersonas.get(codUsuario).getListaReportes().containsKey(codReporte)) {
                Record reporte = listaPersonas.get(codUsuario).getListaReportes().get(codReporte);
                if (!reporte.isEstado()) {
                    listaPersonas.get(codUsuario).getListaReportes().remove(codReporte);
                    archivo.guardarRegistro(listaPersonas);
                    System.out.println("El reporte ha sido eliminado del sistema.");
                } else {
                    System.out.println("No se puede eliminar un reporte positivo del sistema.");
                }
            } else {
                System.out.println("Reporte no existe.");
            }
        } else {
            System.out.println("El usuario no existe.");
        }
    }

    private void listarPersonas() {
        Archivo archivo = new Archivo();
        listaPersonas = archivo.obtenerListasActuales();
        listaPersonas.keySet().stream().map((persona) -> {
            System.out.println("\n" + listaPersonas.get(persona).getIdentificacion()
                    + " " + listaPersonas.get(persona).getNombre() + " "
                    + listaPersonas.get(persona).getApellido() + " " + listaPersonas.get(persona).getCorreo());
            return persona;
        }).map((persona) -> listaPersonas.get(persona).getListaReportes()).forEachOrdered((listaRecord) -> {
            if (listaRecord.size() > 0) {
                listaRecord.keySet().forEach((key) -> {
                    System.out.println("\t" + listaRecord.get(key).getCodigo() + " " + listaRecord.get(key).getEmpresa() + " "
                            + listaRecord.get(key).getDescripcion() + " " + listaRecord.get(key).isEstado() + " " + listaRecord.get(key).getValor());
                });
            } else {
                System.out.println("\t" + "No hay reportes de esta persona");
            }
        });
    }

    private void agregar(int documento) {
        Scanner sc = new Scanner(System.in);
        int codigo = 0;
        double valor = 0;
        boolean estado = true, valida = true;
        String descripcion, empresa;
        Archivo archivo = new Archivo();
        while (valida) {
            System.out.println("Codigo: ");
            try {
                codigo = Integer.parseInt(sc.next());
                valida = false;
            } catch (NumberFormatException e) {
                System.err.println("Numero inválido");
            }
        }

        do {
            System.out.println("Empresa: ");
            empresa = sc.next();
        } while (validaLetras(empresa));

        System.out.println("Descripcion: ");
        descripcion = sc.next();

        estado = boleano();

        valida = true;
        while (valida) {
            System.out.println("Valor: ");
            try {
                valor = Double.parseDouble(sc.next());
                valida = false;
            } catch (NumberFormatException e) {
                System.err.println("Numero inválido");
            }
        }

        Record record = new Record(codigo, empresa, descripcion, estado, valor);
        listaPersonas.get(documento).getListaReportes().put(codigo, record);
        archivo.guardarRegistro(listaPersonas);
    }

    private boolean validaLetras(String palabra) {
        return palabra.contains("1") || palabra.contains("2") || palabra.contains("3") || palabra.contains("4") || palabra.contains("5")
                || palabra.contains("6") || palabra.contains("7") || palabra.contains("8") || palabra.contains("9") || palabra.contains("0");
    }

    private boolean validaCorreo(String correo) {
        return !(correo.contains("@") && correo.contains(".com"));
    }

    private boolean boleano() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Estado: ");
            String estadoAux = sc.next();
            switch (estadoAux) {
                case "true":
                    return true;
                case "false":
                    return false;
                default:
                    System.out.println("Por favor escriba solo true o false.");
                    break;
            }
        }
    }
}
