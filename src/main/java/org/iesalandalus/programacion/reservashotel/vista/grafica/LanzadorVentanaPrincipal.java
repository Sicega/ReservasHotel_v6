package org.iesalandalus.programacion.reservashotel.vista.grafica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.iesalandalus.programacion.reservashotel.vista.grafica.recursos.LocalizadorRecursos;

import java.io.IOException;

public class LanzadorVentanaPrincipal extends Application {
    public static void comenzar(){
        launch();
    }

    @Override
    public void start(Stage escenarioPrincipal) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/ventanaPrincipal.fxml"));
        Parent raiz=fxmlLoader.load();
        Scene scene = new Scene(raiz, 600, 400);
        escenarioPrincipal.setTitle("Hotel Al-Andalus");
        escenarioPrincipal.setScene(scene);
        escenarioPrincipal.show();
    }

    private void confirmarSalida(Stage escenarioPrincipal, WindowEvent e){
        //todo pendiente crear ventana y escena y crear pestaña Archivo y Acerca de
    }

}
