/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadecontroldeinventario.software;

import Modelo.ConexionBaseDeDatos;
import Modelo.DAO.SoftwareDAO;
import Modelo.POJO.Software;
import Utilidades.Utilidades;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
public class ModificaSoftwareFXMLControlador implements Initializable {

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
    
    private boolean esEdicion;

    private Software softwareModificado;
    ObservableList<String> ListaArquitecturas = FXCollections
            .observableArrayList("32", "64", "86");
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarComponentesCombo();
    }   

    private void inicializarComponentesCombo(){
        cbArquitectura.setItems(ListaArquitecturas);
    }    

    @FXML
    private void modificarSoftware(ActionEvent event) {
        /*Connection conexionBD = ConexionBaseDeDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            if(softwareEliminacion != null){
            boolean eliminar = Utilidades.mostrarDialogoConfirmacion("Eliminar registro de software", 
                    "¿Deseas eliminar la información del software?");
            if(eliminar)
                try{
                    boolean resultado = SoftwareDAO.eliminarSoftware(softwareEliminacion.getIdSoftware());
                    if(resultado == true){
                        cargarDatosTabla(); 
                    }else{
                        Utilidades.mostrarAlertaSimple("Operacion fallida", 
                                "La eliminación del software ha fallado",
                                Alert.AlertType.ERROR);
                    }
                }catch(SQLException sqlException){
                    Utilidades.mostrarAlertaSimple("ERROR", 
                            "Algo ocurrió mal al intentar recuperar los software registrados: " + sqlException.getMessage(),
                            Alert.AlertType.ERROR);
                }
            }else{
                Utilidades.mostrarAlertaSimple("Seleccion obligatoria", 
                   "Necesita seleccionar un objeto a eliminar", 
                    Alert.AlertType.WARNING);
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion",
                    "No hay conexión con la base de datos.",
                    Alert.AlertType.ERROR);
        }*/
    }
    
    private void mostrarDatosSoftwareModificar(Software softwareEdicion){
        
    }
    
    

    @FXML
    private void cerrarVentana(ActionEvent event) {
        if(Utilidades.mostrarDialogoConfirmacion("Regresar",  
                "¿Seguro que desea borrar todos los datos de registro?")){
            Stage escenarioPrincipal = (Stage) tfNombre.getScene().getWindow();
            escenarioPrincipal.close();
        }
    }
    
    private boolean camposValidos(){
        boolean sonValidos = true;
        
        if(tfNombre.getText().equals("")){
            lbNombre.setText("No se puede dejar vacío.");
            tfNombre.setStyle("-fx-border-color: red");
            sonValidos = false;
        }else{
            lbNombre.setText("");
            tfNombre.setStyle("");
        }
   
        if(tfPeso.getText().equals("")){
            lbPeso.setText("No se puede dejar vacío.");
            tfPeso.setStyle("-fx-border-color: red");
            sonValidos = false;
        }else if(!tfPeso.getText().toLowerCase().endsWith("b")){
            lbPeso.setText("Ingrese una unidad correcta. Ej. 2Kb, 1Mb, 3Gb, etc.");
            tfPeso.setStyle("-fx-border-color: red");
            sonValidos = false;
        }else{
            lbPeso.setText("");
            tfPeso.setStyle("");
        }
        

        if(cbArquitectura.getSelectionModel().isEmpty()){
            lbArquitectura.setText("No se puede dejar vacío.");
            cbArquitectura.setStyle("-fx-border-color: red");
            sonValidos = false;
        }else{
            lbArquitectura.setText("");
            cbArquitectura.setStyle("");
        }
        
        return sonValidos;
    }
    
    private boolean esNumerico(String cadena){
        try {
            Float.parseFloat(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    void inicializaValores(boolean b, Software softwareModificar) {
        esEdicion = softwareModificar != null;
        if(esEdicion){
            cargarSoftwareModificar(softwareModificar);
        }
    }
    
    private void cargarSoftwareModificar(Software softwareEditado){
        tfNombre.setText(softwareEditado.getNombre());
        tfPeso.setText(softwareEditado.getPeso());
        int arquitectura = softwareEditado.getArquitectura();
        int index = 0;
        
        switch (arquitectura){
            case 32: 
                break;
            case 64:
                index = 1;
                break;
            case 86:
                index = 2;
                break;
        }        
        cbArquitectura.getSelectionModel().select(index);
    }
    
}
