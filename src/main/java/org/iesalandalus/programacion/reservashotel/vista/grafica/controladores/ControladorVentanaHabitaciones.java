package org.iesalandalus.programacion.reservashotel.vista.grafica.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.iesalandalus.programacion.reservashotel.vista.grafica.recursos.LocalizadorRecursos;

import java.io.IOException;

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
    private TableView<?> tvListadoHabitaciones;

    @FXML
    void agregarHabitaciones(ActionEvent event) {

        FXMLLoader fxmlLoader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/ventanaInsertaHabitacion.fxml"));
        try
        {
            Parent raiz=fxmlLoader.load();

            //ControladorVentanaHabitaciones controladorVentanaHabitacion=fxmlLoader.getController();
            //controladorVentanaHabitacion.inicializaDatos(obsHabitacion,coleccionHabitacion);

            Scene escenaVentanaHabitacion=new Scene(raiz,600,400);
            Stage escenarioVentanaHabitacion=new Stage();
            escenarioVentanaHabitacion.setScene(escenaVentanaHabitacion);
            escenarioVentanaHabitacion.setTitle("Hotel Al-Andalus - Insertar Habitacion" );
            escenarioVentanaHabitacion.initModality(Modality.APPLICATION_MODAL);
            escenarioVentanaHabitacion.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void borrarHabitaciones(ActionEvent event) {

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

    }


}

