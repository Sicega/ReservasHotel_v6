package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public class Simple extends Habitacion{

    private static final int NUM_MAXIMO_PERSONAS=1; // Declaro la constante privada que almacena el nº max. de personas, en este caso 1 al ser simple

    public Simple(int planta, int puerta, double precio) {
        super(planta, puerta, precio); // Llamo a la superclase con super
    }

    public Simple(Simple habitacionSimple) {
        super(habitacionSimple); // Utilizo super también para el constructor copia
    }

    public int getNumeroMaximoPersonas(){ // Implemento el método que debe tener la clase al heredar de Habitacion
        return NUM_MAXIMO_PERSONAS; // Devuelve el nº máximo de personas al ser habitacion simple, uso la constante para ello
    }

    @Override
    public String toString() { // Genero el toString con la cadena esperada por el test
        return String.format("%s, habitación simple, capacidad=%d personas",
                super.toString(), getNumeroMaximoPersonas());
    }
}
