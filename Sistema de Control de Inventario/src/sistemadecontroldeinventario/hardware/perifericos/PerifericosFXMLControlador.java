/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadecontroldeinventario.hardware.perifericos;

import Modelo.ConexionBaseDeDatos;
import Modelo.DAO.PerifericoDAO;
import Modelo.POJO.Periferico;
import Utilidades.Utilidades;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author froyl
 */
public class PerifericosFXMLControlador implements Initializable {
    
    private String cargoUsuario;
    private ObservableList<Periferico> listaPerifericos; 

    @FXML
    private TableColumn tcMarca;
    @FXML
    private TableColumn tcModelo;
    @FXML
    private TableColumn tcNumeroSerie;
    @FXML
    private TableColumn tcEstado;
    @FXML
    private TextField tfBusqueda;
    @FXML
    private TableView<Periferico> tvPerifericos;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarTabla();
        inicializarBusquedaPerifericos();
    }    

    @FXML
    private void cerrarVentana(ActionEvent event) {
        Stage stage = (Stage) tvPerifericos.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void eliminarPeriferico(ActionEvent event) {
        Periferico perifericoEliminacion = verificarPerifericoSeleccionado();
        Connection conexionBD = ConexionBaseDeDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            if(perifericoEliminacion != null){
            boolean eliminar = Utilidades.mostrarDialogoConfirmacion("Eliminar registro de periferico", 
                    "¿Deseas eliminar la información del periferico?");
            if(eliminar)
                try{
                    boolean resultado = PerifericoDAO.eliminarPeriferico(perifericoEliminacion.getIdPeriferico());
                    if(resultado == true){
                        cargarTabla(); 
                    }else{
                        Utilidades.mostrarAlertaSimple("Operacion fallida", 
                                "La eliminación del periferico ha fallado",
                                Alert.AlertType.ERROR);
                    }
                }catch(SQLException sqlException){
                    Utilidades.mostrarAlertaSimple("ERROR", 
                            "Algo ocurrió mal al intentar recuperar los perifericos registrados: " + sqlException.getMessage(),
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
    private void consultarPeriferico(ActionEvent event) {
    }

    @FXML
    private void modificarPeriferico(ActionEvent event) {
    }

    @FXML
    private void registrarPeriferico(ActionEvent event) {
    }
    
    private void configurarTabla(){
        tcMarca.setCellValueFactory(new PropertyValueFactory("marca"));
        tcModelo.setCellValueFactory(new PropertyValueFactory("modelo"));
        tcNumeroSerie.setCellValueFactory(new PropertyValueFactory("numeroSerie"));
        tcEstado.setCellValueFactory(new PropertyValueFactory("estado"));
    }
    
    private void cargarTabla(){
        try{
            listaPerifericos = FXCollections.observableArrayList();
            ArrayList<Periferico> perifericosBD = PerifericoDAO.recuperarTodoPeriferico();            

            listaPerifericos.addAll(perifericosBD);
            tvPerifericos.setItems(listaPerifericos);

        }catch(SQLException e){
            Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private void inicializarBusquedaPerifericos(){
        if(listaPerifericos.size() > 0){
            FilteredList<Periferico> filtro = new FilteredList<>(listaPerifericos, p -> true);
            
            tfBusqueda.textProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    filtro.setPredicate(busqueda -> {
                        if(newValue == null || newValue.isEmpty()){
                            return true;
                        }
                        
                        String filtroMinusculas = newValue.toLowerCase();
                        if(busqueda.getNumeroSerie().toLowerCase().contains(filtroMinusculas)){
                            return true;
                        }
                        
                        if(busqueda.getEstado().toLowerCase().contains(filtroMinusculas)){
                            return true;
                        }
                        
                        if(busqueda.getMarca().toLowerCase().contains(filtroMinusculas)){
                            return true;
                        }
                        
                        if(busqueda.getModelo().toLowerCase().contains(filtroMinusculas)){
                            return true;
                        }
                        
                        return false;
                    });
                }
                
            });
            
            SortedList<Periferico> sortedRefaccion = new SortedList<>(filtro);
            sortedRefaccion.comparatorProperty().bind(tvPerifericos.comparatorProperty());
            tvPerifericos.setItems(sortedRefaccion);
        }
    }
    
    private Periferico verificarPerifericoSeleccionado(){
        int filaSeleccionada = tvPerifericos.getSelectionModel().getFocusedIndex();
        return filaSeleccionada >= 0 ? listaPerifericos.get(filaSeleccionada) : null;
    }
    
    public void inicializarVentana(String cargoUsuario){
        this.cargoUsuario = cargoUsuario;
    }
}
