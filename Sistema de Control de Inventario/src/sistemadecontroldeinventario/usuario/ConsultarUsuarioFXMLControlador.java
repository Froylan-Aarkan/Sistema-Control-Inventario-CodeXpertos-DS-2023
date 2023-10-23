/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadecontroldeinventario.usuario;

import Modelo.DAO.UsuarioDAO;
import Modelo.POJO.Usuario;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Elian
 */
public class ConsultarUsuarioFXMLControlador implements Initializable {

    @FXML
    private ImageView ivFoto;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblCorreo;
    @FXML
    private Label lblContrasenia;
    @FXML
    private Label lblCargo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cerrarVentana(ActionEvent event) {
    Stage stage = (Stage) ivFoto.getScene().getWindow();
    stage.close();
    }
    
    public void inicializarUsuario(String usuarioSeleccionado) throws IOException{
        try{
            Usuario usuario = UsuarioDAO.recuperarTodoUsuarioPorCorreo(usuarioSeleccionado);
            lblNombre.setText(usuario.getNombreCompleto());
            lblCorreo.setText(usuario.getCorreoInstitucional());
            lblContrasenia.setText(usuario.getContrasenia());
            lblCargo.setText(usuario.getCargo());
            
            if(usuario.getFoto()!=null){
                Image img = new Image(new ByteArrayInputStream(usuario.getFoto()));
                ivFoto.setImage(img);
            }
        }catch(SQLException e){
            e.getMessage();
        }
        
    }
}
