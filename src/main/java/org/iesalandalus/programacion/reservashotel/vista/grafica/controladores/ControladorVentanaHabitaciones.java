package org.iesalandalus.programacion.reservashotel.vista.grafica.controladores;

import javafx.beans.property.SimpleIntegerProperty;
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
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Suite;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Triple;
import org.iesalandalus.programacion.reservashotel.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.reservashotel.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.reservashotel.vista.grafica.utilidades.Dialogos;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControladorVentanaHabitaciones {

    @FXML
    private Button btAgregarHabitaciones;

    @FXML
    private Button btBorrarHabitaciones;

    @FXML
    private Button btBuscarReservasHabitaciones;

    @FXML
    private MenuItem mnEliminarHabitacion;

    @FXML
    private MenuItem mnInsertarHabitacion;

    @FXML
    private TableView<Habitacion> tvListadoHabitaciones;

    @FXML
    private TableColumn<Habitacion, Integer> tcBanio;

    @FXML
    private TableColumn<Habitacion, Integer> tcCamas;

    @FXML
    private TableColumn<Habitacion, String> tcIdentificador;

    @FXML
    private TableColumn<Habitacion, Boolean> tcJacuzzi;

    @FXML
    private TableColumn<Habitacion, String> tcPlanta;

    @FXML
    private TableColumn<Habitacion, String> tcPrecio;

    @FXML
    private TableColumn<Habitacion, String> tcPuerta;

    @FXML
    private TableColumn<Habitacion, String> tcTipoHabitacion;

    private List<Habitacion> coleccionHabitacion=new ArrayList<>();
    private ObservableList<Habitacion> obsHabitacion= FXCollections.observableArrayList();


    private void cargaDatosHabitacion()
    {

        coleccionHabitacion = VistaGrafica.getInstancia().getControlador().getHabitaciones();
        obsHabitacion.setAll(coleccionHabitacion);


    }

    @FXML
    private void initialize(){

        cargaDatosHabitacion();

        tcIdentificador.setCellValueFactory(habitacion-> new SimpleStringProperty(habitacion.getValue().getIdentificador()));;
        tcPlanta.setCellValueFactory(habitacion-> new SimpleStringProperty(Integer.toString(habitacion.getValue().getPlanta())));;
        tcPuerta.setCellValueFactory(habitacion-> new SimpleStringProperty(Integer.toString(habitacion.getValue().getPuerta())));;
        tcPrecio.setCellValueFactory(habitacion-> new SimpleStringProperty(Double.toString(habitacion.getValue().getPrecio())));;
        //tcCamas.setCellValueFactory(habitacion-> new SimpleStringProperty(habitacion.getValue().getCamas()));;
        //tcBanio.setCellValueFactory(habitacion-> new SimpleStringProperty(habitacion.getValue().get()));;
        //tcJacuzzi.setCellValueFactory(habitacion-> new SimpleStringProperty();;
        tcTipoHabitacion.setCellValueFactory(habitacion-> new SimpleStringProperty(habitacion.getValue().getClass().getSimpleName()));;

        tvListadoHabitaciones.setItems(obsHabitacion);


    }

    @FXML
    void agregarHabitaciones(ActionEvent event) {

        FXMLLoader fxmlLoader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/ventanaInsertaHabitacion.fxml"));
        try
        {
            Parent raiz=fxmlLoader.load();

            Scene escenaVentanaHabitacion=new Scene(raiz,600,400);
            Stage escenarioVentanaHabitacion=new Stage();
            escenarioVentanaHabitacion.setScene(escenaVentanaHabitacion);
            escenarioVentanaHabitacion.setTitle("Hotel Al-Andalus - Insertar Habitacion" );
            escenarioVentanaHabitacion.initModality(Modality.APPLICATION_MODAL);
            escenarioVentanaHabitacion.showAndWait();
            cargaDatosHabitacion();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void borrarHabitaciones(ActionEvent event) {

        Habitacion habitacion=tvListadoHabitaciones.getSelectionModel().getSelectedItem();

         if (habitacion!=null &&
                Dialogos.mostrarDialogoConfirmacion("Hotel Al Andalus - Eliminar Habitacion", "Desea borrar la habitacion seleccionada"))
        {

            try {
                VistaGrafica.getInstancia().getControlador().borrar(habitacion);
            }catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e){
                Dialogos.mostrarDialogoError("Error borrar habitacion",e.getMessage());
            }
            cargaDatosHabitacion();
            Dialogos.mostrarDialogoInformacion("Hotel Al Andalus - Eliminar Habitacion", "Habitacion borrada correctamente");
        }

        if (habitacion==null)
            Dialogos.mostrarDialogoAdvertencia("Hotel Al Andalus - Eliminar Habitacion","Debes seleccionar una habitacion para borrarla");

    }

    @FXML
    void buscarReservasHabitaciones(ActionEvent event) {

    }

    @FXML
    void insertaHabitacion(ActionEvent event) {

        agregarHabitaciones(event);

    }

    @FXML
    void eliminaHabitacion(ActionEvent event) {

        borrarHabitaciones(event);

    }


}

