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
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author johno
 */
public class SoftwareFXMLControlador implements Initializable {

    @FXML
    private TableView<Software> tvSoftware;
    @FXML
    private TableColumn<Software, String> tcNombre;
    @FXML
    private TableColumn<Software, String> tcArquitectura;
    @FXML
    private TableColumn<Software, String> tcPeso;
    public ObservableList<Software> listaSoftware;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurarTabla();
        cargarDatosTabla();
    }  
    
    private void configurarTabla(){
        tcNombre.setCellValueFactory(new PropertyValueFactory ("nombre"));
        tcArquitectura.setCellValueFactory(new PropertyValueFactory("arquitectura"));
        tcPeso.setCellValueFactory(new PropertyValueFactory("peso"));
    }
    
    private void cargarDatosTabla(){
        try{
            listaSoftware = FXCollections.observableArrayList();
            ArrayList<Software> softwareBD = SoftwareDAO.recuperarTodoSoftware();
            listaSoftware.addAll(softwareBD);
            tvSoftware.setItems(listaSoftware);
        }catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
    }
      

    @FXML
    private void cerrarVentana(ActionEvent event) {
        Stage stage = (Stage) tvSoftware.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void eliminarSoftware(ActionEvent event) {
        Software softwareEliminacion = verificarSoftwareSeleccionado();
        Connection conexionBD = ConexionBaseDeDatos.abrirConexionBaseDatos();
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
        }
    }

    @FXML
    private void consultarPorEquipoComputo(ActionEvent event) {
    }

    @FXML
    private void modificarSoftware(ActionEvent event) {
    }

    @FXML
    private void registrarSoftware(ActionEvent event) {
    }
   
    private Software verificarSoftwareSeleccionado(){
        int filaSeleccionada = tvSoftware.getSelectionModel().getFocusedIndex();
        return filaSeleccionada >= 0 ? listaSoftware.get(filaSeleccionada) : null;
    }
}
