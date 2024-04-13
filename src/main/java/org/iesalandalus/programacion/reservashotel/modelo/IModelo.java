package org.iesalandalus.programacion.reservashotel.modelo;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import java.time.LocalDateTime;
import java.util.List;

public interface IModelo {
    void comenzar();
    void terminar();
    void insertar(Huesped huesped);
    Huesped buscar(Huesped huesped);
    void borrar(Huesped huesped);
    List<Huesped> getHuespedes();
    void insertar(Habitacion habitacion);
    Habitacion buscar(Habitacion habitacion);
    void borrar(Habitacion habitacion);
    List<Habitacion> getHabitaciones();
    List<Habitacion>getHabitaciones(TipoHabitacion tipoHabitacion);
    void insertar(Reserva reserva);
    Reserva buscar(Reserva reserva);
    void borrar(Reserva reserva);
    List<Reserva> getReservas();
    List<Reserva> getReservas(Huesped huesped);
    List<Reserva> getReservas(TipoHabitacion tipoHabitacion);
    List<Reserva> getReservas(Habitacion habitacion);
    List<Reserva> getReservasFuturas(Habitacion habitacion);
    void realizarCheckIn(Reserva reserva, LocalDateTime fecha);
    void realizarCheckOut(Reserva reserva, LocalDateTime fecha);



}
