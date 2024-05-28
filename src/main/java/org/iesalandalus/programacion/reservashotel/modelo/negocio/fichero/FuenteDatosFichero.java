package org.iesalandalus.programacion.reservashotel.modelo.negocio.fichero;

import org.iesalandalus.programacion.reservashotel.modelo.negocio.*;

public class FuenteDatosFichero implements IFuenteDatos {
    @Override
    public IHuespedes crearHuespedes() {
        return Huespedes.getInstancia();
    }

    @Override
    public IHabitaciones crearHabitaciones() {
        return Habitaciones.getInstancia();
    }

    @Override
    public IReservas crearReservas() {
        return Reservas.getInstancia();
    }
}
