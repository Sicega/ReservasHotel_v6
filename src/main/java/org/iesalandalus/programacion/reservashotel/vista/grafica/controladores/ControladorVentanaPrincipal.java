package org.iesalandalus.programacion.reservashotel.vista.grafica.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.iesalandalus.programacion.reservashotel.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.reservashotel.vista.grafica.utilidades.Dialogos;

import java.io.IOException;

public class ControladorVentanaPrincipal {

    @FXML
    private Button btHabitacionesPrincipal;

    @FXML
    private Button btHuespedesPrincipal;

    @FXML
    private Button btReservasPrincipal;

    @FXML
    private Menu mnAcercaDePrincipal;

    @FXML
    private Menu mnArchivoPrincipal;

    @FXML
    private MenuBar mnVentanaPrincipal;


    @FXML
    void abrirVentanaHabitaciones(ActionEvent event) {

        FXMLLoader fxmlLoader=new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/ventanaHabitaciones.fxml"));
        ControladorVentanaHabitaciones controladorVentanaHabitaciones=fxmlLoader.getController();
        try {
            Parent raiz=fxmlLoader.load();

            Scene escena=new Scene(raiz,600,400);
            Stage escenario=new Stage();
            escenario.setScene(escena);
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.setTitle("Hotel Al-Andalus - Habitaciones");
            escenario.setResizable(false);
            escenario.showAndWait();

        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void abrirVentanaHuespedes(ActionEvent event) {

        FXMLLoader fxmlLoader=new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/ventanaHuespedes.fxml"));
        ControladorVentanaHuespedes c=fxmlLoader.getController();
        try {
            Parent raiz=fxmlLoader.load();

            Scene escena=new Scene(raiz,600,400);
            Stage escenario=new Stage();
            escenario.setScene(escena);
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.setTitle("Hotel Al-Andalus - Huespedes");
            escenario.setResizable(false);
            escenario.showAndWait();

        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void abrirVentanaReservas(ActionEvent event) {

        FXMLLoader fxmlLoader=new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/ventanaReservas.fxml"));
        ControladorVentanaReservas c=fxmlLoader.getController();
        try {
            Parent raiz=fxmlLoader.load();

            Scene escena=new Scene(raiz,600,400);
            Stage escenario=new Stage();
            escenario.setScene(escena);
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.setTitle("Hotel Al-Andalus - Reservas");
            escenario.setResizable(false);
            escenario.showAndWait();

        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void clickSalirAplicacion(ActionEvent event) {

        if (Dialogos.mostrarDialogoConfirmacion("Hotel Al-Andalus", "Estas seguro que quieres salir de la aplicacion"))
        {
            System.exit(0);
        }
        else
            event.consume();
    }

}

