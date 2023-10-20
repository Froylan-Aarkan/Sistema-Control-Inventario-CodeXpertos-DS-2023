/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadecontroldeinventario.hardware;

import Modelo.POJO.Hardware;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sistemadecontroldeinventario.InicioSesionFXMLControlador;
import sistemadecontroldeinventario.VentanaPrincipalFXMLControlador;

/**
 * FXML Controller class
 *
 * @author froyl
 */
public class EquiposDeComputoFXMLControlador implements Initializable {

    private String cargoUsuario;
    @FXML
    private TableView<Hardware> tvEquiposComputo;
    @FXML
    private TextField tfBusqueda;
    @FXML
    private TableColumn<Hardware, String> tcMarca;
    @FXML
    private TableColumn<Hardware, String> tcModelo;
    @FXML
    private TableColumn<Hardware, String> tcNumeroSerie;
    @FXML
    private TableColumn<Hardware, String> tcEstado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cerrarVentana(ActionEvent event) {
        Stage stage = (Stage) tfBusqueda.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void registrarEquipoComputo(ActionEvent event) {
    }

    @FXML
    private void modificarEquipoComputo(ActionEvent event) {
    }

    @FXML
    private void consultarEquipoComputo(ActionEvent event) {
    }

    @FXML
    private void eliminarEquipoComputo(ActionEvent event) {
    }
    
    public void inicializarVentana(String cargoUsuario){
        this.cargoUsuario = cargoUsuario;
    }
    
}
