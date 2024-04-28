package org.iesalandalus.programacion.reservashotel.vista.grafica.controladores;


import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.vista.grafica.VistaGrafica;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ControladorVentanaHuespedes {
    private static final DateTimeFormatter FORMATO_FECHA=DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML
    private Button btAgregarHuespedes;

    @FXML
    private Button btBorrarHuespedes;

    @FXML
    private Button btBuscarReservasHuespedes;

    @FXML
    private TableView<Huesped> tvListadoHuespedes;

    @FXML
    private TableColumn<Huesped, String> tcCorreo;

    @FXML
    private TableColumn<Huesped, String> tcDni;

    @FXML
    private TableColumn<Huesped, String> tcFechaNacimiento;

    @FXML
    private TableColumn<Huesped, String> tcNombre;

    @FXML
    private TableColumn<Huesped, String> tcTelefono;

    @FXML
    private MenuItem mnEliminarHuesped;

    @FXML
    private MenuItem mnInsertarHuesped;

    private ObservableList<Huesped> obsHuesped = FXCollections.observableArrayList();
    private List<Huesped> coleccionHuesped=VistaGrafica.getInstancia().getControlador().getHuespedes();

    /*private void cargaDatosHuesped()
    {
        System.out.println(coleccionHuesped);

        obsHuesped.setAll(coleccionHuesped);
        System.out.println(obsHuesped);

        tcNombre.setCellValueFactory(huesped-> new SimpleStringProperty(huesped.getValue().getNombre()));
        tcDni.setCellValueFactory(huesped-> new SimpleStringProperty(huesped.getValue().getDni()));
        tcCorreo.setCellValueFactory(huesped-> new SimpleStringProperty(huesped.getValue().getCorreo()));
        tcTelefono.setCellValueFactory(huesped-> new SimpleStringProperty(huesped.getValue().getTelefono()));
        tcFechaNacimiento.setCellValueFactory(huesped->new SimpleStringProperty(huesped.getValue().getFechaNacimiento().format(FORMATO_FECHA).toString()));
        coleccionHuesped=VistaGrafica.getInstancia().getControlador().getHuespedes();


        tvListadoHuespedes.setItems(obsHuesped);

    }
    @FXML
    private void initialize(){


        cargaDatosHuesped();


    }*/

    @FXML
    void agregarHuespedes(ActionEvent event) {



    }

    @FXML
    void borrarHuespedes(ActionEvent event) {

    }

    @FXML
    void buscarReservasHuespedes(ActionEvent event) {

    }

    @FXML
    void insertaHuesped(ActionEvent event) {

    }


    @FXML
    void eliminaHuesped(ActionEvent event) {

    }



}
