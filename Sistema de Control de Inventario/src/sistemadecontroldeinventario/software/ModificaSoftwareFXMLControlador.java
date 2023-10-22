/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadecontroldeinventario.software;

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
 * @author johno
 */
public class ModificaSoftwareFXMLControlador implements Initializable {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfPeso;
    @FXML
    private ComboBox<?> cbArquitectura;
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbPeso;
    @FXML
    private Label lbArquitectura;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modificarSoftware(ActionEvent event) {
    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
        Stage escenarioPrincipal = (Stage) tfNombre.getScene().getWindow();
        escenarioPrincipal.close();
    }
    
}
