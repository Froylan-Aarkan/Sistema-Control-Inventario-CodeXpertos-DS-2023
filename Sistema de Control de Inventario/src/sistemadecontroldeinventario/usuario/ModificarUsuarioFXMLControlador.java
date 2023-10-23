/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadecontroldeinventario.usuario;

import Modelo.DAO.UsuarioDAO;
import Modelo.POJO.Usuario;
import Utilidades.Utilidades;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Elian
 */
public class ModificarUsuarioFXMLControlador implements Initializable {

    @FXML
    private TextField tfnombre;
    @FXML
    private TextField tfCargo;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfContrasenia;
    @FXML
    private ImageView ivFoto;
    
    private File archivoFoto;
    private String usuarioModificar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modificarUsuario(ActionEvent event) {
        Usuario usuarioRegistro = new Usuario();
            usuarioRegistro.setNombreCompleto(tfnombre.getText());
            usuarioRegistro.setCorreoInstitucional(tfCorreo.getText());
            usuarioRegistro.setCargo(tfCargo.getText());
            usuarioRegistro.setContrasenia(tfContrasenia.getText());

            guardarModificaciónUsuario(usuarioRegistro); 
    }

    private void guardarModificaciónUsuario(Usuario usuario){
        try{
            if(UsuarioDAO.modificarUsuario(usuario, usuarioModificar)){
                Utilidades.mostrarAlertaSimple("Registro exitoso", "El usuario se modificó con exito", Alert.AlertType.CONFIRMATION);
                Stage stage = (Stage) tfCargo.getScene().getWindow();
                stage.close();
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RegistrarUsuarioFXMLControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void cancelarOperacion(ActionEvent event) {
    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
        Stage stage = (Stage) tfCargo.getScene().getWindow();
        stage.close();
    }

    private void subirFoto(ActionEvent event) {
        FileChooser dialogoImagen = new FileChooser();
        dialogoImagen.setTitle("Selecciona una foto");
        FileChooser.ExtensionFilter filtroImg = new FileChooser.ExtensionFilter("Archivos JPG (*.jpg)", "*.JPG");
        dialogoImagen.getExtensionFilters().add(filtroImg);
        Stage escenarioActual = (Stage) tfCargo.getScene().getWindow();
        archivoFoto = dialogoImagen.showOpenDialog(escenarioActual);
        
        if(archivoFoto != null){
            try {
                BufferedImage bufferImg = ImageIO.read(archivoFoto);
                Image imagenFoto = SwingFXUtils.toFXImage(bufferImg, null);
                ivFoto.setImage(imagenFoto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void inicializarUsuario(String usuarioSeleccionado) throws IOException{
        usuarioModificar = usuarioSeleccionado;
        try{
            Usuario usuario = UsuarioDAO.recuperarTodoUsuarioPorCorreo(usuarioSeleccionado);
            tfnombre.setText(usuario.getNombreCompleto());
            tfCorreo.setText(usuario.getCorreoInstitucional());
            tfContrasenia.setText(usuario.getContrasenia());
            tfCargo.setText(usuario.getCargo());
            
            if(usuario.getFoto()!=null){
                Image img = new Image(new ByteArrayInputStream(usuario.getFoto()));
                ivFoto.setImage(img);
            }
        }catch(SQLException e){
            e.getMessage();
        }
        
    }
    
}
