/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadecontroldeinventario.software;

import Modelo.POJO.Hardware;
import Modelo.POJO.Software;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author johno
 */
public class AsignarHardwareSoftwareFXMLControlador implements Initializable {

    @FXML
    private TableView<Software> tvSoftware;
    @FXML
    private TableColumn<Software, String> tcNombre;
    @FXML
    private TableColumn<Software, String> tcPeso;
    @FXML
    private TableColumn<Software, String> tcArquitectura;
    @FXML
    private ComboBox<Hardware> cbHardware;
    @FXML
    private Label lbHardware;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cerrarVentana(ActionEvent event) {
        Stage stage = (Stage) tvSoftware.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void asignarHardwareSoftware(ActionEvent event) {
    }

    @FXML
    private void cbNumeroSerieHardware(ActionEvent event) {
        
    }
}
