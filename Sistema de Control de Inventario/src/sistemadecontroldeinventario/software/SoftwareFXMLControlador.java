/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadecontroldeinventario.software;

import Modelo.ConexionBaseDeDatos;
import Modelo.DAO.SoftwareDAO;
import Modelo.POJO.Software;
import Utilidades.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sistemadecontroldeinventario.InicioSesionFXMLControlador;

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
    
    private String cargoUsuario;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
            if(!softwareBD.isEmpty()){
                listaSoftware.addAll(softwareBD);
                tvSoftware.setItems(listaSoftware);
            }else{
                Utilidades.mostrarAlertaSimple("No hay software", 
                        "Aun no hay software registrados.", 
                        Alert.AlertType.ERROR);
                
            }
        }catch(SQLException | NullPointerException e){
            Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal: " + e.getMessage(), Alert.AlertType.ERROR);
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
        if(!tvSoftware.getSelectionModel().isEmpty()){
            try {
                    int seleccionado = tvSoftware.getSelectionModel().getSelectedItem().getIdSoftware();
                    System.out.println(seleccionado);
                    FXMLLoader loaderVentanaConsultarUsuario = new FXMLLoader(getClass().getResource("ConsultarSoftwareEquiposFXML.fxml"));
                    Parent ventanaConsultarUsuario = loaderVentanaConsultarUsuario.load();

                    Scene escenarioUsuario = new Scene(ventanaConsultarUsuario);
                    Stage stageSoftware = new Stage();
                    stageSoftware.setScene(escenarioUsuario);
                    stageSoftware.initModality(Modality.APPLICATION_MODAL);

                    ConsultarSoftwareEquiposFXMLControlador controlador = (ConsultarSoftwareEquiposFXMLControlador) loaderVentanaConsultarUsuario.getController();
                    controlador.inicializarUsuario(seleccionado);

                    stageSoftware.showAndWait();


                } catch (IOException ex) {
                    Logger.getLogger(InicioSesionFXMLControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
        }else{
            Utilidades.mostrarAlertaSimple("Selecciona un software", "Se debe seleccionar un software", Alert.AlertType.WARNING);
        }
            
    }

    @FXML
    private void modificarSoftware(ActionEvent event) {
        Software softwareModificacion = verificarSoftwareSeleccionado();
        Connection conexionBD = ConexionBaseDeDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            if(softwareModificacion != null){
            boolean actualizar = Utilidades.mostrarDialogoConfirmacion("Modificar registro de software", 
                    "¿Deseas modificar la información del software?");
                if(actualizar){
                    abrirFormularioSoftwareModificar(softwareModificacion);
                    cargarDatosTabla();
                }else{
                    Utilidades.mostrarAlertaSimple("Seleccion obligatoria", 
                       "Necesita seleccionar un objeto a modificar", 
                        Alert.AlertType.WARNING);
                }
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion",
                    "No hay conexión con la base de datos.",
                    Alert.AlertType.ERROR);
        }
    }
   
    
    public void inicializarVentana(String cargoUsuario) {
        this.cargoUsuario = cargoUsuario;
    }
    
    private Software verificarSoftwareSeleccionado(){
        int filaSeleccionada = tvSoftware.getSelectionModel().getFocusedIndex();
        return filaSeleccionada >= 0 ? listaSoftware.get(filaSeleccionada) : null;
    }
    
    private void abrirFormularioSoftware(){
        try {
            FXMLLoader loaderVentanaRegistrarEquipoDeComputo = new FXMLLoader(getClass().getResource("RegistroSoftwareFXML.fxml"));
            Parent ventanaRegistrarEquipoDeComputo = loaderVentanaRegistrarEquipoDeComputo.load();
            
            Scene escenarioEquiposDeComputo = new Scene(ventanaRegistrarEquipoDeComputo);
            Stage stageEquiposDeComputo = new Stage();
            stageEquiposDeComputo.setScene(escenarioEquiposDeComputo);
            stageEquiposDeComputo.initModality(Modality.APPLICATION_MODAL);
            stageEquiposDeComputo.showAndWait();
            
        } catch (IOException ex) {
            Logger.getLogger(InicioSesionFXMLControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void abrirFormularioSoftwareModificar(Software softwareModificar){
        try {
            FXMLLoader loaderVentanaModificarSoftware = new FXMLLoader(getClass().getResource("ModificaSoftwareFXML.fxml"));
            Parent ventanaModificarSoftware = loaderVentanaModificarSoftware.load();
            
            ModificaSoftwareFXMLControlador formularioModificar = 
                    loaderVentanaModificarSoftware.getController();
            
            formularioModificar.inicializaValores(softwareModificar);
            
            Scene escenarioSoftware = new Scene(ventanaModificarSoftware);
            Stage stageSoftware = new Stage();
            stageSoftware.setScene(escenarioSoftware);
            stageSoftware.initModality(Modality.APPLICATION_MODAL);
            stageSoftware.showAndWait();
            
        } catch (IOException ex) {
            Logger.getLogger(InicioSesionFXMLControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void registrarSoftware(ActionEvent event) {
        abrirFormularioSoftware();
        cargarDatosTabla();
    }
}
