package org.iesalandalus.programacion.reservashotel.modelo.negocio.memoria;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IHabitaciones;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Habitaciones implements IHabitaciones {

    private List<Habitacion> coleccionHabitaciones;

    // MÉTODOS

    public Habitaciones() {
        this.coleccionHabitaciones = new ArrayList<>();
    }

    // Método para obtener una copia profunda de las habitaciones
    public List<Habitacion> get() {
        return copiaProfundaHabitaciones();
    }

    // Método privado para realizar una copia profunda de las habitaciones
    private List<Habitacion> copiaProfundaHabitaciones() {
        List<Habitacion> miHabitacion = new ArrayList<>();

        // Utilizo un iterador para recorrer la lista de habitaciones
        Iterator<Habitacion> iterator = coleccionHabitaciones.iterator();
        while (iterator.hasNext()) {
            Habitacion habitacion = iterator.next();
            if (habitacion instanceof Simple) {
                miHabitacion.add(new Simple((Simple) habitacion));
            } else if (habitacion instanceof Doble) {
                miHabitacion.add(new Doble((Doble) habitacion));
            } else if (habitacion instanceof Triple) {
                miHabitacion.add(new Triple((Triple) habitacion));
            } else if (habitacion instanceof Suite) {
                miHabitacion.add(new Suite((Suite) habitacion));
            }
        }

        // Devuelvo una nueva lista con las habitaciones copiadas
        return new ArrayList<>(miHabitacion);
    }

    // Método para obtener las habitaciones de un tipo específico
    public List<Habitacion> get(TipoHabitacion tipoHabitacion) {
        List<Habitacion> habitacionesTipo = new ArrayList<>();

        for(Habitacion habitacion : coleccionHabitaciones) {
            if(tipoHabitacion.equals(TipoHabitacion.SIMPLE)) {
                if (habitacion instanceof Simple) {
                    habitacionesTipo.add(new Simple((Simple) habitacion));
                }
            } else if (tipoHabitacion.equals(TipoHabitacion.DOBLE)) {
                if (habitacion instanceof Doble) {
                    habitacionesTipo.add(new Doble((Doble) habitacion));
                }
            } else if (tipoHabitacion.equals(TipoHabitacion.TRIPLE)) {
                if (habitacion instanceof Triple) {
                    habitacionesTipo.add(new Triple((Triple) habitacion));
                }
            } else if (tipoHabitacion.equals(TipoHabitacion.SUITE)) {
                if (habitacion instanceof Suite) {
                    habitacionesTipo.add(new Suite((Suite) habitacion));
                }
            }
        }

        // Devuelvo una nueva lista con las habitaciones del tipo especificado copiadas
        return new ArrayList<>(habitacionesTipo);
    }

    // Método para obtener el tamaño actual de la colección
    public int getTamano() {
        return coleccionHabitaciones.size();
    }

    // Método para insertar una nueva habitación en la colección
    public void insertar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se puede insertar una habitación nula.");
        }

        if (buscar(habitacion) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una habitación con ese identificador.");
        }

        // Utilizo instanceof según el tipo de habitacion para añadir a la colección
        if (habitacion instanceof Simple) {
            coleccionHabitaciones.add(new Simple((Simple) habitacion));
        } else if (habitacion instanceof Doble) {
            coleccionHabitaciones.add(new Doble((Doble) habitacion));
        } else if (habitacion instanceof Triple) {
            coleccionHabitaciones.add(new Triple((Triple) habitacion));
        } else if (habitacion instanceof Suite) {
            coleccionHabitaciones.add(new Suite((Suite) habitacion));
        }
    }

    // Método privado para buscar el índice de una habitación en la colección
    private int buscarIndice(Habitacion habitacion) {
        return coleccionHabitaciones.indexOf(habitacion);
    }

    // Método para buscar una habitación en la colección
    public Habitacion buscar(Habitacion habitacion) {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se puede buscar una habitación nula.");
        }

        // Busco el índice de la habitación en la colección
        int indice = buscarIndice(habitacion);

        // Devuelvo la habitación encontrada o null si no se encontró
        if (indice != -1) {
            if (coleccionHabitaciones.get(indice) instanceof Simple) {
                return new Simple((Simple) coleccionHabitaciones.get(indice));
            } else if (coleccionHabitaciones.get(indice) instanceof Doble) {
                return new Doble((Doble) coleccionHabitaciones.get(indice));
            } else if (coleccionHabitaciones.get(indice) instanceof Triple) {
                return new Triple((Triple) coleccionHabitaciones.get(indice));
            } else if (coleccionHabitaciones.get(indice) instanceof Suite) {
                return new Suite((Suite) coleccionHabitaciones.get(indice));
            }
        } return null;
    }

    // Método para borrar una habitación de la colección
    public void borrar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se puede borrar una habitación nula.");
        }

        // Busco el índice de la habitación en la colección
        int indice = buscarIndice(habitacion);

        // Compruebo si encuentra la habitación
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna habitación como la indicada.");
        }

        // Elimino la habitación de la colección
        coleccionHabitaciones.remove(indice);
    }
}
