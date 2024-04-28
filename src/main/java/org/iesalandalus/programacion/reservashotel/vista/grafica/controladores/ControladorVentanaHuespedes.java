package org.iesalandalus.programacion.reservashotel.vista.grafica.controladores;


import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.stage.Modality;
import javafx.stage.Stage;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.reservashotel.vista.grafica.recursos.LocalizadorRecursos;

import java.io.IOException;
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
    private List<Huesped> coleccionHuesped;

    private void cargaDatosHuesped()
    {
        System.out.println(coleccionHuesped);

        //obsHuesped.setAll(coleccionHuesped);
        System.out.println(obsHuesped);

        tcNombre.setCellValueFactory(huesped-> new SimpleStringProperty(huesped.getValue().getNombre()));
        tcDni.setCellValueFactory(huesped-> new SimpleStringProperty(huesped.getValue().getDni()));
        tcCorreo.setCellValueFactory(huesped-> new SimpleStringProperty(huesped.getValue().getCorreo()));
        tcTelefono.setCellValueFactory(huesped-> new SimpleStringProperty(huesped.getValue().getTelefono()));
        tcFechaNacimiento.setCellValueFactory(huesped->new SimpleStringProperty(huesped.getValue().getFechaNacimiento().format(FORMATO_FECHA).toString()));
        //coleccionHuesped=VistaGrafica.getInstancia().getControlador().getHuespedes();


        tvListadoHuespedes.setItems(obsHuesped);

    }
    @FXML
    private void initialize(){


        cargaDatosHuesped();


    }

    public void inicializaDatos(ObservableList<Huesped> obs,List<Huesped> tvlistadoHuespedes)
    {
        obsHuesped=obs;
        this.coleccionHuesped=coleccionHuesped;
    }

    @FXML
    void agregarHuespedes(ActionEvent event){

        FXMLLoader fxmlLoader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/ventanaInsertaHuesped.fxml"));
        try
        {
            Parent raiz=fxmlLoader.load();

            //ControladorVentanaHuespedes controladorVentanaHuesped=fxmlLoader.getController();
            //controladorVentanaHuesped.inicializaDatos(obsHuesped,coleccionHuesped);

            Scene escenaVentanaHuesped=new Scene(raiz,600,400);
            Stage escenarioVentanaHuesped=new Stage();
            escenarioVentanaHuesped.setScene(escenaVentanaHuesped);
            escenarioVentanaHuesped.setTitle("Hotel Al-Andalus - Insertar Huesped" );
            escenarioVentanaHuesped.initModality(Modality.APPLICATION_MODAL);
            escenarioVentanaHuesped.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void borrarHuespedes(ActionEvent event) {

    }

    @FXML
    void buscarReservasHuespedes(ActionEvent event) {

    }

    @FXML
    void insertaHuesped(ActionEvent event){

            agregarHuespedes(event);
    }


    @FXML
    void eliminaHuesped(ActionEvent event) {

    }



}
