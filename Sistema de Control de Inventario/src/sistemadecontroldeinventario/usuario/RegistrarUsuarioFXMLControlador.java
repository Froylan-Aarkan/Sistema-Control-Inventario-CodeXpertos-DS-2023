/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadecontroldeinventario.usuario;

import Modelo.DAO.UsuarioDAO;
import Modelo.POJO.Usuario;
import Utilidades.Utilidades;
import java.awt.FileDialog;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * FXML Controller class
 *
 * @author Elian
 */
public class RegistrarUsuarioFXMLControlador implements Initializable {
    @FXML
    private ImageView ivFoto;
    @FXML
    private TextField tfnombre;
    @FXML
    private TextField tfCargo;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfContrasenia;
    private File archivoFoto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    
    
    @FXML
    private void cancelarOperacion(ActionEvent event) {
    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
    }

    @FXML
    private void registrarUsuario(ActionEvent event) {
        
            Usuario usuarioRegistro = new Usuario();
            usuarioRegistro.setNombreCompleto(tfnombre.toString());
            usuarioRegistro.setCorreoInstitucional(tfCorreo.toString());
            usuarioRegistro.setCargo(tfCargo.toString());
            usuarioRegistro.setContrasenia(tfContrasenia.toString());
            if(archivoFoto != null){
                try{
                    usuarioRegistro.setFoto(Files.readAllBytes(archivoFoto.toPath()));

                }catch(IOException e){
                    e.getMessage();
                }
            }
            guardarRegistroUsuario(usuarioRegistro);    
    }
    
    private void guardarRegistroUsuario(Usuario usuario){
        try{
            if(UsuarioDAO.registrarUsuario(usuario, archivoFoto)){
                Utilidades.mostrarAlertaSimple("Registro exitoso", "El alumno se registró con exito", Alert.AlertType.CONFIRMATION);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RegistrarUsuarioFXMLControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void subirFoto(ActionEvent event) throws IOException {
        
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
    
}
