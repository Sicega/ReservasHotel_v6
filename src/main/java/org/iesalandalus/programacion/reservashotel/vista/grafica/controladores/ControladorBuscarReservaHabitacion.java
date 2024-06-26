package org.iesalandalus.programacion.reservashotel.vista.grafica.controladores;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Regimen;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.reservashotel.vista.grafica.utilidades.Dialogos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ControladorBuscarReservaHabitacion {

    @FXML
    private Label lbIdentificadorReservasHabitacion;

    @FXML
    private TextField tfIdentificadorHabReservas;

    @FXML
    private TableView<Reserva> tvListadoReservas;

    @FXML
    private TableColumn<Reserva, String> tvclDni;

    @FXML
    private TableColumn<Reserva, String> tvclFechaFin;

    @FXML
    private TableColumn<Reserva, String> tvclFechaInicio;

    @FXML
    private TableColumn<Reserva, String> tvclIdentificador;

    @FXML
    private TableColumn<Reserva, String> tvclNombre;

    @FXML
    private TableColumn<Reserva, String> tvclPrecio;

    @FXML
    private TableColumn<Reserva, String> tvclRegimen;

    @FXML
    private TableColumn<Reserva, String> tvclTipoHabitacion;

    private Reserva reserva;

    private List<Reserva> coleccionReserva=new ArrayList<>();
    private ObservableList<Reserva> obsReserva= FXCollections.observableArrayList();

    private FilteredList<Reserva> filtro;

    private Habitacion habitacion;


    public void preparar(Habitacion habitacion){
        if(habitacion==null){
            throw new NullPointerException("Error: La habitacion no puede ser nula.");
        }
        this.habitacion=habitacion;
        cargaDatosReserva();

    }

    private void cargaDatosReserva()
    {
        if(habitacion!=null){
        coleccionReserva = VistaGrafica.getInstancia().getControlador().getReservas(habitacion);
        obsReserva.setAll(coleccionReserva);
        filtro = new FilteredList<>(obsReserva);
        tvListadoReservas.setItems(filtro);}
    }

    @FXML
    private void initialize(){

        cargaDatosReserva();

        tvclIdentificador.setCellValueFactory(reserva-> new SimpleStringProperty(reserva.getValue().getHabitacion().getIdentificador()));;
        tvclDni.setCellValueFactory(reserva-> new SimpleStringProperty(reserva.getValue().getHuesped().getDni()));;
        tvclFechaInicio.setCellValueFactory(reserva-> new SimpleStringProperty(reserva.getValue().getFechaInicioReserva().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));;
        tvclFechaFin.setCellValueFactory(reserva-> new SimpleStringProperty(reserva.getValue().getFechaFinReserva().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));;
        tvclNombre.setCellValueFactory(reserva-> new SimpleStringProperty(reserva.getValue().getHuesped().getNombre()));;
        tvclPrecio.setCellValueFactory(reserva-> new SimpleStringProperty(Double.toString(reserva.getValue().getHabitacion().getPrecio())));;
        tvclRegimen.setCellValueFactory(reserva-> new SimpleStringProperty(reserva.getValue().getRegimen().toString()));;
        tvclTipoHabitacion.setCellValueFactory(reserva-> new SimpleStringProperty(reserva.getValue().getHabitacion().getClass().getSimpleName()));;

        tvListadoReservas.setItems(obsReserva);

        tfIdentificadorHabReservas.textProperty().addListener((observable, oldValue, newValue) -> {
            filtro.setPredicate(reserva -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                return reserva.getHabitacion().getIdentificador().contains(newValue);
            });
        });

    }

}


