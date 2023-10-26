/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadecontroldeinventario.CentroComputo;

import Modelo.DAO.CentroComputoDAO;
import Modelo.POJO.CentroComputo;
import Modelo.POJO.Usuario;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
        
        CentroComputoDAO centroDAO = new CentroComputoDAO();
        centroDAO.RegistrarCentroComputo(centroComputo);
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
    }
    
}
