package org.iesalandalus.programacion.reservashotel.vista.grafica.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.iesalandalus.programacion.reservashotel.vista.grafica.recursos.LocalizadorRecursos;

import java.io.IOException;

public class ControladorVentanaPrincipal {

    @FXML private Button btHabitaciones;
    @FXML private Button btHuespedes;
    @FXML private Button btReservas;

    @FXML
    void clickAbrirVentanaHabitaciones(ActionEvent event) {
        FXMLLoader fxmlLoader=new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/ventanaHabitaciones.fxml"));

        try {
            Parent raiz=fxmlLoader.load();

            Scene escena=new Scene(raiz,600,400);
            Stage escenario=new Stage();
            escenario.setScene(escena);
            escenario.setTitle("Habitaciones");
            escenario.show();

        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void clickAbrirVentanaHuespedes(ActionEvent event) {
        FXMLLoader fxmlLoader=new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/ventanaHuespedes.fxml"));

        try {
            Parent raiz=fxmlLoader.load();

            Scene escena=new Scene(raiz,600,400);
            Stage escenario=new Stage();
            escenario.setScene(escena);
            escenario.setTitle("Huespedes");
            escenario.show();

        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void clickAbrirVentanaReservas(ActionEvent event) {
        FXMLLoader fxmlLoader=new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/ventanaReservas.fxml"));

        try {
            Parent raiz=fxmlLoader.load();

            Scene escena=new Scene(raiz,600,400);
            Stage escenario=new Stage();
            escenario.setScene(escena);
            escenario.setTitle("Reservas");
            escenario.show();

        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

}
