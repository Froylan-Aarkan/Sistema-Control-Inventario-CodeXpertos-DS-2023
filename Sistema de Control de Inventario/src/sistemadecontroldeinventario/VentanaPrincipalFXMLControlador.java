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

/**
 * FXML Controller class
 *
 * @author froyl
 */
public class VentanaPrincipalFXMLControlador implements Initializable {
    
    private String cargoUsuario;
    @FXML
    private Button btnHardware;
    @FXML
    private Button btnCentrosComputo;

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
    private void desplegarVentanaHardware(ActionEvent event) {
        try {
            FXMLLoader loaderVentanaHardware = new FXMLLoader(getClass().getResource("hardware/VentanaHardwareFXML.fxml"));
            Parent ventanaHardware = loaderVentanaHardware.load();
            
            Scene escenarioHardware = new Scene(ventanaHardware);
            Stage stageHardware = new Stage();
            stageHardware.setScene(escenarioHardware);
            stageHardware.initModality(Modality.APPLICATION_MODAL);
            stageHardware.showAndWait();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void desplegarVentanaSoftware(ActionEvent event) {
        try {
            FXMLLoader loaderVentanaSoftware = new FXMLLoader(getClass().getResource("software/MainSoftwareFXML.fxml"));
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
            FXMLLoader loaderVentanaUsuario = new FXMLLoader(getClass().getResource("usuario/UsuarioFXML.fxml"));
            Parent ventanaUsuario = loaderVentanaUsuario.load();
            
            Scene escenarioUsuario = new Scene(ventanaUsuario);
            Stage stageUsuario = new Stage();
            stageUsuario.setScene(escenarioUsuario);
            stageUsuario.initModality(Modality.APPLICATION_MODAL);
            stageUsuario.showAndWait();
            
        } catch (IOException ex) {
            Logger.getLogger(InicioSesionFXMLControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void desplegarVentanaCentrosComputo(ActionEvent event) {
        try {
            FXMLLoader loaderVentanaCentrosDeComputo = new FXMLLoader(getClass().getResource("CentroComputo/ConsultarCentroDeComputo.fxml"));
            Parent ventanaCentrosDeComputo = loaderVentanaCentrosDeComputo.load();
            
            Scene escenarioCentrosDeComputo = new Scene(ventanaCentrosDeComputo);
            Stage stageCentrosDeComputo = new Stage();
            stageCentrosDeComputo.setScene(escenarioCentrosDeComputo);
            stageCentrosDeComputo.initModality(Modality.APPLICATION_MODAL);
            stageCentrosDeComputo.showAndWait();
            
            Stage stage = (Stage) btnCentrosComputo.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            Logger.getLogger(InicioSesionFXMLControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
    }
    
}
