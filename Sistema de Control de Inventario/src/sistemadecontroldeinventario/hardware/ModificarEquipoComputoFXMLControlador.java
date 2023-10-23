/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadecontroldeinventario.hardware;

import Modelo.POJO.Hardware;
import Utilidades.Utilidades;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private ComboBox<?> cbArquitectura;
    @FXML
    private TextField tfProcesador;
    @FXML
    private TextField tfNumeroSerie;
    @FXML
    private TextField tfModelo;
    @FXML
    private TextField tfMarca;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @FXML
    private void cerrarVentana(ActionEvent event) {
        Stage stage = (Stage) tfAlmacenamiento.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancelarOperacion(ActionEvent event) {
        if(Utilidades.mostrarDialogoConfirmacion("Cancelar", "¿Seguro que desea cancelar los cambios?")){
            cargarDatos();
        }
    }

    @FXML
    private void modificarEquipoComputo(ActionEvent event) {
    }
    
    private void cargarDatos(){
        
    }

    public void inicializarVentana(Hardware hardwareModificacion){
        this.hardwareModificacion = hardwareModificacion;
        cargarDatos();
    }
    
}
