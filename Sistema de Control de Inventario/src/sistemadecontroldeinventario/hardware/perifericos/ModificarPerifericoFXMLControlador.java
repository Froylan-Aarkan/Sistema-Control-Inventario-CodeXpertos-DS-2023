/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadecontroldeinventario.hardware.perifericos;

import Modelo.DAO.CentroComputoDAO;
import Modelo.DAO.PerifericoDAO;
import Modelo.POJO.CentroComputo;
import Modelo.POJO.Periferico;
import Utilidades.Utilidades;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
 * @author froyl
 */
public class ModificarPerifericoFXMLControlador implements Initializable {
    
    private Periferico perifericoModificacion;

    @FXML
    private Label lbErrorTipoConexion;
    @FXML
    private Label lbErrorTipo;
    @FXML
    private Label lbErrorNumeroSerie;
    @FXML
    private Label lbErrorModelo;
    @FXML
    private Label lbErrorMarca;
    @FXML
    private ComboBox<String> cbTipoConexion;
    @FXML
    private TextField tfTipo;
    @FXML
    private TextField tfNumeroSerie;
    @FXML
    private TextField tfModelo;
    @FXML
    private TextField tfMarca;
    @FXML
    private ComboBox<String> cbEstado;
    @FXML
    private Label lbErrorUbicacion;
    @FXML
    private Label lbErrorEstado;
    @FXML
    private ComboBox<CentroComputo> cbUbicacion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarComboBoxes();
    }    

    @FXML
    private void modificarPeriferico(ActionEvent event) {
        //TODO verificacion de cambios
        if(camposValidos()){
            
        }
    }

    @FXML
    private void cancelarOperacion(ActionEvent event) {
        if(Utilidades.mostrarDialogoConfirmacion("Cancelar", "¿Desea cancelar la operación?")){
            cargarDatos();
        }
    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
        if(Utilidades.mostrarDialogoConfirmacion("Regresar", "¿Seguro que desea cancelar la operación y volver a la ventana anterior?")){
            Stage stage = (Stage) tfMarca.getScene().getWindow();
            stage.close();
        }    
    }
    
    private void cargarDatos(){
        tfMarca.setText(perifericoModificacion.getMarca());
        tfModelo.setText(perifericoModificacion.getModelo());
        tfNumeroSerie.setText(perifericoModificacion.getNumeroSerie());
        tfTipo.setText(perifericoModificacion.getTipo());
        cbEstado.getSelectionModel().select(perifericoModificacion.getEstado());
        
        if(perifericoModificacion.isInalambrico()){
            cbTipoConexion.getSelectionModel().select(1);
        }else{
            cbTipoConexion.getSelectionModel().select(0);
        }
        
        try {
            CentroComputo centroSeleccion = CentroComputoDAO.recuperarCentroComputoPorId(perifericoModificacion.getIdCentroComputo());
            for (CentroComputo centroComboBox : cbUbicacion.getItems()) {
                if(centroComboBox.getIdCentroComputo() == centroSeleccion.getIdCentroComputo()){
                    cbUbicacion.getSelectionModel().select(centroComboBox);
                }
            }
        }catch (SQLException e) {
            Utilidades.mostrarAlertaSimple("Error", "Algo salió mal: " + e.getMessage(), Alert.AlertType.ERROR);
        }catch(NullPointerException e){
            e.printStackTrace();
        }

    }
    
    private void cargarComboBoxes(){
        cbTipoConexion.getItems().addAll("Alambrico", "Inalambrico");
        cbEstado.getItems().addAll("Funcional", "Dañado");
        try {
            ArrayList<CentroComputo> centrosComputoBD = CentroComputoDAO.recuperarTodoCentroDeComputo();
            cbUbicacion.getItems().addAll(centrosComputoBD);
        } catch (SQLException e) {
            Utilidades.mostrarAlertaSimple("Error", "Algo salió mal: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private boolean camposValidos(){
        boolean sonValidos = true;
        try {           
            if(tfMarca.getText().equals("")){
                lbErrorMarca.setText("No se puede dejar vacío.");
                tfMarca.setStyle("-fx-border-color: red");
                sonValidos = false;
            }else{
                lbErrorMarca.setText("");
                tfMarca.setStyle("");
            }
            
            if(tfModelo.getText().equals("")){
                lbErrorModelo.setText("No se puede dejar vacío.");
                tfModelo.setStyle("-fx-border-color: red");
                sonValidos = false;
            }else{
                lbErrorModelo.setText("");
                tfModelo.setStyle("");
            }
            
            if(tfNumeroSerie.getText().equals("")){
                lbErrorNumeroSerie.setText("No se puede dejar vacío.");
                tfNumeroSerie.setStyle("-fx-border-color: red");
                sonValidos = false;
            }else if(PerifericoDAO.buscarPerifericoPorNumeroSerie(tfNumeroSerie.getText()) != null && PerifericoDAO.buscarPerifericoPorNumeroSerie(tfNumeroSerie.getText()).getIdPeriferico() != perifericoModificacion.getIdPeriferico()){
                lbErrorNumeroSerie.setText("Este número de serie ya existe.");
                tfNumeroSerie.setStyle("-fx-border-color: red");
                sonValidos = false;
            }else{
                lbErrorNumeroSerie.setText("");
                tfNumeroSerie.setStyle("");
            }
            
            if(tfTipo.getText().equals("")){
                lbErrorTipo.setText("No se puede dejar vacío.");
                tfTipo.setStyle("-fx-border-color: red");
                sonValidos = false;
            }else{
                lbErrorTipo.setText("");
                tfTipo.setStyle("");
            }
            
            if(cbTipoConexion.getSelectionModel().isEmpty()){
                lbErrorTipoConexion.setText("Debe seleccionar una opción.");
                cbTipoConexion.setStyle("-fx-border-color: red");
                sonValidos = false;
            }else{
                lbErrorTipoConexion.setText("");
                cbTipoConexion.setStyle("");
            }
        } catch (SQLException e) {
            Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal: " + e.getMessage(), Alert.AlertType.ERROR);
        }
        
        return sonValidos;
    }
    
    public void inicializarVentana(Periferico perifericoModificacion){
        this.perifericoModificacion = perifericoModificacion;
        cargarDatos();
    }
    
}
