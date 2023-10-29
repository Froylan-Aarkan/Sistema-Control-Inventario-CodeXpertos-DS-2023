/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadecontroldeinventario.software;

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
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sistemadecontroldeinventario.InicioSesionFXMLControlador;

/**
 * FXML Controller class
 *
 * @author johno
 */
public class MainSoftwareFXMLControlador implements Initializable {

    @FXML
    private Label lbFondo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cerrarVentana(ActionEvent event) {
        Stage stage = (Stage) lbFondo.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void desplegarVentanaConsultarSoftware(ActionEvent event) {
        try {
            FXMLLoader loaderVentanaSoftware = new FXMLLoader(getClass().getResource("SoftwareFXML.fxml"));
            Parent ventanaSoftware = loaderVentanaSoftware.load();
            
            Scene escenarioSoftware = new Scene(ventanaSoftware);
            Stage stageSoftware = new Stage();
            stageSoftware.setScene(escenarioSoftware);
            stageSoftware.initModality(Modality.APPLICATION_MODAL);
            stageSoftware.showAndWait();
            
        } catch (IOException ex) {
            Logger.getLogger(InicioSesionFXMLControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void desplegarVentanaAsignarHardwareSoftware(ActionEvent event) {
        try {
            FXMLLoader loaderVentanaSoftware = new FXMLLoader(getClass().getResource("AsignarHardwareSoftwareFXML.fxml"));
            Parent ventanaSoftware = loaderVentanaSoftware.load();
            
            Scene escenarioSoftware = new Scene(ventanaSoftware);
            Stage stageSoftware = new Stage();
            stageSoftware.setScene(escenarioSoftware);
            stageSoftware.initModality(Modality.APPLICATION_MODAL);
            stageSoftware.showAndWait();
            
        } catch (IOException ex) {
            Logger.getLogger(InicioSesionFXMLControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
