package org.iesalandalus.programacion.reservashotel.vista.grafica.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import java.time.LocalDate;

public class ControladorBuscarReservaHabitacion {

    @FXML
    private Label lbIdentificadorReservasHabitacion;

    @FXML
    private TableView<Reserva> tvListadoReservas;

    @FXML
    private TableColumn<Reserva, String> tvclDni;

    @FXML
    private TableColumn<Reserva, LocalDate> tvclFechaFin;

    @FXML
    private TableColumn<Reserva, LocalDate> tvclFechaInicio;

    @FXML
    private TableColumn<Reserva, String> tvclIdentificador;

    @FXML
    private TableColumn<Reserva, String> tvclNombre;

    @FXML
    private TableColumn<Reserva, TipoHabitacion> tvclTipoHabitacion;

}


