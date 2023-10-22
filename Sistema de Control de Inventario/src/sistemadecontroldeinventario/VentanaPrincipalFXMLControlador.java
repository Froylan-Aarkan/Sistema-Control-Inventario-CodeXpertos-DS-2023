/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadecontroldeinventario;

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
import sistemadecontroldeinventario.hardware.EquiposDeComputoFXMLControlador;

/**
 * FXML Controller class
 *
 * @author froyl
 */
public class VentanaPrincipalFXMLControlador implements Initializable {
    
    private String cargoUsuario;
    @FXML
    private Button btnHardware;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    public void inicializarVentana(String cargoUsuario){
        this.cargoUsuario = cargoUsuario;
    }

    @FXML
    private void desplegarVentanaEquiposComputo(ActionEvent event) {
        try {
            FXMLLoader loaderVentanaEquiposDeComputo = new FXMLLoader(getClass().getResource("hardware/EquiposDeComputoFXML.fxml"));
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
    private void desplegarVentanaSoftware(ActionEvent event) {
        try {
            FXMLLoader loaderVentanaSoftware = new FXMLLoader(getClass().getResource("software/SoftwareFXML.fxml"));
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
    private void desplegarVentanaUsuarios(ActionEvent event) {
        try {
            FXMLLoader loaderVentanaUsuarios = new FXMLLoader(getClass().getResource("usuario/UsuarioFXML.fxml"));
            Parent ventanaUsuario = loaderVentanaUsuarios.load();
            
            Scene escenarioUsuario = new Scene(ventanaUsuario);
            Stage stageSoftware = new Stage();
            stageSoftware.setScene(escenarioUsuario);
            stageSoftware.initModality(Modality.APPLICATION_MODAL);
            stageSoftware.showAndWait();
            
        } catch (IOException ex) {
            Logger.getLogger(InicioSesionFXMLControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
