/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadecontroldeinventario.hardware;

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
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sistemadecontroldeinventario.InicioSesionFXMLControlador;

/**
 * FXML Controller class
 *
 * @author froyl
 */
public class VentanaHardwareFXMLControlador implements Initializable {

    @FXML
    private Button btEquiposComputo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void desplegarVentanaEquiposComputo(ActionEvent event) {
        try {
            FXMLLoader loaderVentanaEquiposDeComputo = new FXMLLoader(getClass().getResource("equiposComputo/EquiposDeComputoFXML.fxml"));
            Parent ventanaEquiposDeComputo = loaderVentanaEquiposDeComputo.load();
            
            Scene escenarioEquiposDeComputo = new Scene(ventanaEquiposDeComputo);
            Stage stageEquiposDeComputo = new Stage();
            stageEquiposDeComputo.setScene(escenarioEquiposDeComputo);
            stageEquiposDeComputo.initModality(Modality.APPLICATION_MODAL);
            stageEquiposDeComputo.showAndWait();
            
        } catch (IOException ex) {
            Logger.getLogger(InicioSesionFXMLControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void desplegarVentanaPerifericos(ActionEvent event) {
        
    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
        Stage stage = (Stage) btEquiposComputo.getScene().getWindow();
        stage.close();
    }
    
}
