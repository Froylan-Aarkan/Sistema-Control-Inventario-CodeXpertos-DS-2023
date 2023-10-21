/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadecontroldeinventario.software;

import Modelo.DAO.SoftwareDAO;
import Modelo.POJO.Software;
import Utilidades.Utilidades;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author johno
 */
public class FormularioSoftwareFXMLControlador implements Initializable {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfPeso;
    @FXML
    private ComboBox<String> cbArquitectura;
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbPeso;
    @FXML
    private Label lbArquitectura;
    
    private Software softwareEditado;
    private boolean esEdicion;

    ObservableList<String> ListaArquitecturas = FXCollections
            .observableArrayList("32", "64", "86");
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inicializarComponentesCombo();
    }    

    private void inicializarComponentesCombo(){
        cbArquitectura.setItems(ListaArquitecturas);
    }
    
    @FXML
    private void registrarSoftware(ActionEvent event) {
        String nombre = tfNombre.getText();
        String peso = tfPeso.getText();
        String arquitectura = cbArquitectura.getSelectionModel().getSelectedItem();
        
            Software softwareNuevo = new Software();
            softwareNuevo.setNombre(nombre);
            softwareNuevo.setPeso(peso);
            softwareNuevo.setArquitectura(Integer.parseInt(arquitectura));
        
            guardarRegistroNuevo(softwareNuevo);
            
        if(esEdicion){
            softwareNuevo.setIdSoftware(softwareEditado.getIdSoftware());
            //guardarRegistroEditado(softwareNuevo);
        }else{
            guardarRegistroNuevo(softwareNuevo);
        }
    }
    
    private void guardarRegistroNuevo(Software softwareNuevo){
        try {
            boolean resultadoGuardar = 
                    SoftwareDAO.registrarSoftware(softwareNuevo);
            
            if (resultadoGuardar == true) {
                Utilidades.mostrarAlertaSimple("Software registrado", 
                        "El software ha sido registrado exitosamente",
                        Alert.AlertType.INFORMATION);
                tfNombre.clear();
                tfPeso.clear();
                cbArquitectura.setValue(ListaArquitecturas.get(0));
            } else {
                Utilidades.mostrarAlertaSimple("Error al guardar", 
                        "El software no ha podido ser registrado",
                        Alert.AlertType.ERROR);
            }
        } catch (SQLException sqlException) {
            Utilidades.mostrarAlertaSimple("Error de conexión", 
                        "Algo ocurrió mal al intentar recuperar los software registrados: " + sqlException.getMessage(),
                        Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
        Stage escenarioPrincipal = (Stage) tfNombre.getScene().getWindow();
        escenarioPrincipal.close();
    }

    public void inicializarValores(Software softwareEditado){
        this.softwareEditado = softwareEditado;
        esEdicion = softwareEditado != null;
        if(esEdicion){
            cargarSoftwareModificar();
        }
    }
    
    private void cargarSoftwareModificar(){
        tfNombre.setText(softwareEditado.getNombre());
        tfPeso.setText(softwareEditado.getPeso());
        cbArquitectura.setItems(ListaArquitecturas);
        ObservableList<String> itemsArquitectura = cbArquitectura.getItems();
        String arquitecturaSeleccionada = Integer.toString(softwareEditado.getArquitectura());
        cbArquitectura.getSelectionModel().select(itemsArquitectura.indexOf(arquitecturaSeleccionada));
    }
}
