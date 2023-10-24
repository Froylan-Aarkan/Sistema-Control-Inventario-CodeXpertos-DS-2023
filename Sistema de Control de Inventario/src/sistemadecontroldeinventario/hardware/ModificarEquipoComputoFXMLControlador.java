/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadecontroldeinventario.hardware;

import Modelo.DAO.CentroComputoDAO;
import Modelo.POJO.CentroComputo;
import Modelo.POJO.Hardware;
import Utilidades.Utilidades;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author froyl
 */
public class ModificarEquipoComputoFXMLControlador implements Initializable {

    private Hardware hardwareModificacion;
    
    @FXML
    private Label lbErrorAlmacenamiento;
    @FXML
    private Label lbErrorRam;
    @FXML
    private TextField tfRam;
    @FXML
    private TextField tfAlmacenamiento;
    @FXML
    private Label lbErrorDireccionIp;
    @FXML
    private Label lbErrorDireccionMac;
    @FXML
    private Label lbErrorGrafica;
    @FXML
    private Label lbErrorSistemaOperativo;
    @FXML
    private Label lbErrorTarjetaMadre;
    @FXML
    private Label lbErrorArquitectura;
    @FXML
    private Label lbErrorProcesador;
    @FXML
    private Label lbErrorNumeroSerie;
    @FXML
    private Label lbErrorModelo;
    @FXML
    private Label lbErrorMarca;
    @FXML
    private TextField tfDireccionIp;
    @FXML
    private TextField tfDireccionMac;
    @FXML
    private TextField tfGrafica;
    @FXML
    private TextField tfSistemaOperativo;
    @FXML
    private TextField tfTarjetaMadre;
    @FXML
    private ComboBox<String> cbArquitectura;
    @FXML
    private TextField tfProcesador;
    @FXML
    private TextField tfNumeroSerie;
    @FXML
    private TextField tfModelo;
    @FXML
    private TextField tfMarca;
    @FXML
    private ComboBox<CentroComputo> cbUbicacion;
    @FXML
    private ComboBox<String> cbLetra;
    @FXML
    private ComboBox<String> cbNumero;
    @FXML
    private Label lbErrorUbicacion;
    @FXML
    private Label lbErrorLetra;
    @FXML
    private Label lbErrorNumero;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           cargarCentrosComputo();
           cargarComboBoxes();
    }    

    @FXML
    private void cerrarVentana(ActionEvent event) {
        if(Utilidades.mostrarDialogoConfirmacion("Cancelar", "¿Seguro que desea cancelar los cambios y volver a la ventana anterior?")){
            Stage stage = (Stage) tfAlmacenamiento.getScene().getWindow();
            stage.close();
        }        
    }

    @FXML
    private void cancelarOperacion(ActionEvent event) {
        if(Utilidades.mostrarDialogoConfirmacion("Cancelar", "¿Seguro que desea descartar los cambios?")){
            cargarDatos();
        }
    }

    @FXML
    private void modificarEquipoComputo(ActionEvent event) {
    }
    
    private void cargarDatos(){
        tfMarca.setText(hardwareModificacion.getMarca());
        tfModelo.setText(hardwareModificacion.getModelo());
        tfNumeroSerie.setText(hardwareModificacion.getNumeroSerie());
        tfProcesador.setText(hardwareModificacion.getProcesador());
        tfTarjetaMadre.setText(hardwareModificacion.getTarjetaMadre());
        tfSistemaOperativo.setText(hardwareModificacion.getSistemaOperativo());
        tfGrafica.setText(hardwareModificacion.getGrafica());
        tfRam.setText(hardwareModificacion.getRam() + "");
        tfDireccionMac.setText(hardwareModificacion.getDireccionMac());
        tfDireccionIp.setText(hardwareModificacion.getDireccionIp());
        tfAlmacenamiento.setText(hardwareModificacion.getAlmacenamiento() + "");
        
    }
    
    private void cargarCentrosComputo(){
        try {
            ArrayList<CentroComputo> centrosComputoBD = CentroComputoDAO.recuperarTodoCentroDeComputo();
            cbUbicacion.getItems().addAll(centrosComputoBD);
        } catch (SQLException e) {
            Utilidades.mostrarAlertaSimple("Error", "Algo salió mal: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private void cargarComboBoxes(){
        cbArquitectura.getItems().addAll("x32 bits", "x64 bits", "x86 bits");
        
        for(int i = 65; i < 91; i++){
            cbLetra.getItems().add(String.valueOf((char) i));
        }
        
        for(int i = 1; i < 11; i++){
            cbNumero.getItems().add(String.valueOf(i));
        }
    }

    public void inicializarVentana(Hardware hardwareModificacion){
        this.hardwareModificacion = hardwareModificacion;
        cargarDatos();
    }
    
}
