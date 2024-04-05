package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public enum TipoHabitacion {

    //Elimino el parámetro del nº max. de personas

    SUITE("Suite"),
    SIMPLE("Simple"),
    DOBLE("Doble"),
    TRIPLE("Triple");


    private String cadenaAMostrar;

    // Elimino numero máximo de personas


    TipoHabitacion(String cadenaAMostrar){ //Modifico el constructor del enumerado para no incluir el nº max. de personas

        this.cadenaAMostrar=cadenaAMostrar;
    }

    // Elimino el método getNumeroMaximoDePersonas

    @Override
    public String toString() {

        return ordinal() + " .- " + cadenaAMostrar;
    }

}
