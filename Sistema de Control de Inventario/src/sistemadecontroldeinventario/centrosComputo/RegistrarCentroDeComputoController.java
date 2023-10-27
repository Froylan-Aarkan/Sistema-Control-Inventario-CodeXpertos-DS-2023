/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadecontroldeinventario.CentroComputo;

import Modelo.DAO.CentroComputoDAO;
import Modelo.POJO.CentroComputo;
import Modelo.POJO.Usuario;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sistemadecontroldeinventario.InicioSesionFXMLControlador;
import sistemadecontroldeinventario.VentanaPrincipalFXMLControlador;

/**
 * FXML Controller class
 *
 * @author Elotlan
 */
public class RegistrarCentroDeComputoController implements Initializable {

    @FXML
    private TextField tfDireccion;
    @FXML
    private TextField tfFacultad;
    @FXML
    private TextField tfEdificio;
    @FXML
    private TextField tfAula;
    @FXML
    private TextField tfPiso;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnCancelar;

    Usuario usuarioActivo = new Usuario();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void RecibirUsuario(Usuario usuario){
        usuarioActivo = usuario;
    }

    @FXML
    private void clicRegistrar(ActionEvent event) throws SQLException {
        CentroComputo centroComputo = new CentroComputo();
        
        centroComputo.setDireccion(tfDireccion.getText());
        centroComputo.setFacultad(tfFacultad.getText());
        centroComputo.setEdificio(tfEdificio.getText());
        centroComputo.setAula(tfAula.getText());
        centroComputo.setPiso(tfPiso.getText());
        
        CentroComputoDAO.registrarCentroDeComputo(centroComputo);
        limpiarCampos();
    }

    private void limpiarCampos(){
        tfDireccion.setText("");
        tfFacultad.setText("");
        tfEdificio.setText("");
        tfAula.setText("");
        tfPiso.setText("");
    }
    
    @FXML
    private void clicCancelar(ActionEvent event) {
        try {
            FXMLLoader loaderVentanaPrincipal = new FXMLLoader(getClass().getResource("/sistemadecontroldeinventario/VentanaPrincipalFXML.fxml"));
            Parent ventanaPrincipal = loaderVentanaPrincipal.load();
            VentanaPrincipalFXMLControlador controladorVentanaPrincipal = loaderVentanaPrincipal.getController();
            
            Scene escenaVentanaPrincipal = new Scene(ventanaPrincipal);
            Stage stageVentanaPrincipal = new Stage();
            stageVentanaPrincipal.setScene(escenaVentanaPrincipal);
            stageVentanaPrincipal.show();
        } catch (IOException ex) {
            Logger.getLogger(InicioSesionFXMLControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
