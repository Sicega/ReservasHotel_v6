package org.iesalandalus.programacion.reservashotel.vista.grafica.controladores;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import javafx.scene.control.ToggleGroup;
import org.iesalandalus.programacion.reservashotel.vista.grafica.utilidades.Dialogos;

public class ControladorVentanaInsertaHabitacion {

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private ComboBox<TipoHabitacion> cbTipoHabitacion;

    @FXML
    private ToggleGroup grupoBanios;

    @FXML
    private ToggleGroup grupoCamasDobles;

    @FXML
    private ToggleGroup grupoCamasIndividuales;

    @FXML
    private ToggleGroup grupoJacuzzi;

    @FXML
    private RadioButton rbCeroCamasDobles;

    @FXML
    private RadioButton rbDosBanios;

    @FXML
    private RadioButton rbDosCamasIndividuales;

    @FXML
    private RadioButton rbJacuzziNo;

    @FXML
    private RadioButton rbJacuzziSi;

    @FXML
    private RadioButton rbTresCamasIndividuales;

    @FXML
    private RadioButton rbUnBanio;

    @FXML
    private RadioButton rbUnaCamaDoble;

    @FXML
    private RadioButton rbUnaCamaIndividual;

    @FXML
    private RadioButton rbCeroCamaIndividual;

    @FXML
    private TextField tfPlanta;

    @FXML
    private TextField tfPrecio;

    @FXML
    private TextField tfPuerta;

    @FXML
    void initialize(){
        cbTipoHabitacion.setItems(FXCollections.observableArrayList(TipoHabitacion.values()));
    }

    @FXML
    void aceptar(ActionEvent event) {

        int numCamasInd=0, numCamasDobles=0, numBanios=1;
        boolean jacuzzi=false;

        RadioButton rbSeleccionado=(RadioButton) grupoCamasIndividuales.getSelectedToggle();
        if (rbSeleccionado==rbCeroCamaIndividual)
        {
            numCamasInd=0;
        }
        else if (rbSeleccionado==rbUnaCamaIndividual)
        {
            numCamasInd=1;
        }
        else if (rbSeleccionado==rbDosCamasIndividuales)
        {
            numCamasInd=2;

        }else if(rbSeleccionado==rbTresCamasIndividuales){

            numCamasInd=3;
        }

        RadioButton rbSeleccionado2=(RadioButton) grupoCamasDobles.getSelectedToggle();
        if (rbSeleccionado2==rbCeroCamasDobles){
            numCamasDobles=0;
        } else if(rbSeleccionado2==rbUnaCamaDoble){
            numCamasDobles=1;
        }

        RadioButton rbSeleccionado3=(RadioButton) grupoBanios.getSelectedToggle();

        if(rbSeleccionado3==rbUnBanio){
            numBanios=1;
        }else if(rbSeleccionado3==rbDosBanios){
            numBanios=2;
        }

        RadioButton rbSeleccionado4=(RadioButton) grupoJacuzzi.getSelectedToggle();

        if(rbSeleccionado4==rbJacuzziSi){
            jacuzzi=true;
        }else if(rbSeleccionado4==rbJacuzziNo){
            jacuzzi=false;
        }

        try{
        if(cbTipoHabitacion.getSelectionModel().isSelected(0)){
            System.out.println(new Suite(Integer.parseInt(tfPlanta.getText()), Integer.parseInt(tfPuerta.getText()),Double.parseDouble(tfPrecio.getText()), numBanios, jacuzzi));
        }
        else if (cbTipoHabitacion.getSelectionModel().isSelected(1)) {
            System.out.println(new Simple(Integer.parseInt(tfPlanta.getText()), Integer.parseInt(tfPuerta.getText()),Double.parseDouble(tfPrecio.getText())));
        }
        else if(cbTipoHabitacion.getSelectionModel().isSelected(2)){
            System.out.println(new Doble(Integer.parseInt(tfPlanta.getText()),Integer.parseInt(tfPuerta.getText()),Double.parseDouble(tfPrecio.getText()),numCamasInd,numCamasDobles));
        }
        else if (cbTipoHabitacion.getSelectionModel().isSelected(3)){
            System.out.println(new Triple(Integer.parseInt(tfPlanta.getText()), Integer.parseInt(tfPuerta.getText()), Double.parseDouble(tfPrecio.getText()), numBanios,numCamasInd,numCamasDobles));
        }}catch (NullPointerException | IllegalArgumentException e){
            Dialogos.mostrarDialogoError("Error",e.getMessage());
        }

    }

    @FXML
    void cancelar(ActionEvent event) {

        ((Stage)btnCancelar.getScene().getWindow()).close();

    }


}

