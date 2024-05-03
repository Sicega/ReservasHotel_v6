package org.iesalandalus.programacion.reservashotel.vista.grafica.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableView;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.iesalandalus.programacion.reservashotel.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.reservashotel.vista.grafica.utilidades.Dialogos;

import java.io.IOException;

public class ControladorVentanaReservas {

    @FXML
    private Button btAgregarReservas;

    @FXML
    private Button btBorrarReservas;

    @FXML
    private Button btCheckIn;

    @FXML
    private Button btCheckOut;

    @FXML
    private MenuItem mnEliminaReserva;

    @FXML
    private MenuItem mnInsertarReserva;

    @FXML
    private MenuBar mnVentanaPrincipal;

    @FXML
    private TableView<?> tvListadoReservas;

    @FXML
    void agregarReservas(ActionEvent event) {

        FXMLLoader fxmlLoader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/ventanaInsertaReserva.fxml"));
        try
        {
            Parent raiz=fxmlLoader.load();

            //ControladorVentanaReservas controladorVentanaReserva=fxmlLoader.getController();
            //controladorVentanaReservas.inicializaDatos(obsReserva,coleccionReserva);

            Scene escenaVentanaReserva=new Scene(raiz,600,400);
            Stage escenarioVentanaReserva=new Stage();
            escenarioVentanaReserva.setScene(escenaVentanaReserva);
            escenarioVentanaReserva.setTitle("Hotel Al-Andalus - Insertar Reserva" );
            escenarioVentanaReserva.initModality(Modality.APPLICATION_MODAL);
            escenarioVentanaReserva.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void borrarReservas(ActionEvent event) {

        /*if (reserva!=null &&
                Dialogos.mostrarDialogoConfirmacion("Hotel Al Andalus - Eliminar Reserva", "Desea borrar la reserva seleccionada"))
        {

            coleccionReserva.remove(reserva);
            obsReserva.setAll(coleccionReserva);
            Dialogos.mostrarDialogoInformacion("Hotel Al Andalus - Eliminar Reserva", "Reserva borrada correctamente");
        }

        if (reserva==null)
            Dialogos.mostrarDialogoAdvertencia("Hotel Al Andalus - Eliminar Reserva","Debes seleccionar una reserva para borrarla");*/

    }

    @FXML
    void checkInReservas(ActionEvent event) {

    }

    @FXML
    void checkOutReservas(ActionEvent event) {

    }

    @FXML
    void eliminaReserva(ActionEvent event) {

        borrarReservas(event);

    }

    @FXML
    void insertaReserva(ActionEvent event) {

        agregarReservas(event);

    }

}
