package org.iesalandalus.programacion.reservashotel;

import org.iesalandalus.programacion.reservashotel.controlador.Controlador;
import org.iesalandalus.programacion.reservashotel.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.reservashotel.modelo.Modelo;
import org.iesalandalus.programacion.reservashotel.vista.Vista;

public class MainApp {

    public static void main(String[] args) {

        // Creo instancias de modelo, vista y controlador

        Modelo modelo = procesarArgumentosFuenteDatos(args);
        Vista vista = new Vista();
        Controlador controlador = new Controlador(modelo, vista);

        // Inicio la aplicación invocando el método comenzar del controlador

        controlador.comenzar();
        controlador.terminar();
        
    }

    public static Modelo procesarArgumentosFuenteDatos(String[] args){

        Modelo modelo= null;

        if(args[0].equalsIgnoreCase("-fdmemoria")){
            modelo = new Modelo(FactoriaFuenteDatos.MEMORIA);
        } else if (args[0].equalsIgnoreCase("-fdmongodb")) {
            modelo = new Modelo(FactoriaFuenteDatos.MONGODB);
        }

        return modelo;
    }

}
