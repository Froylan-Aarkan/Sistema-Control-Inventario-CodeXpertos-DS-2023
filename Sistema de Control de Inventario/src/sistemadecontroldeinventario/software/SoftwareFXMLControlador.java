/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadecontroldeinventario.software;

import Modelo.DAO.SoftwareDAO;
import Modelo.POJO.Software;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    }    

    @FXML
    private void cerrarVentana(ActionEvent event) {
        Stage stage = (Stage) tvSoftware.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void eliminarSoftware(ActionEvent event) {
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
    
    private void configurarTabla(){
        tcNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
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
}
