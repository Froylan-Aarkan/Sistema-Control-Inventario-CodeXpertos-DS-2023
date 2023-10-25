/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadecontroldeinventario.CentroComputo;

import Modelo.DAO.CentroComputoDAO;
import Modelo.POJO.CentroComputo;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sistemadecontroldeinventario.InicioSesionFXMLControlador;
import sistemadecontroldeinventario.VentanaPrincipalFXMLControlador;

/**
 * FXML Controller class
 *
 * @author Elotlan
 */
public class ConsultarCentroDeComputoController implements Initializable {

    @FXML
    private TableView tbCentroComputo;
    @FXML
    private TableColumn colAula;
    @FXML
    private TableColumn colFacultad;
    @FXML
    private TableColumn colEdificio;
    @FXML
    private Button btnSalir;

    /**
     * Initializes the controller class.
     */
    public void mostrarTabla() throws SQLException{
        colAula.setCellValueFactory(new PropertyValueFactory<CentroComputo, String>("aula"));
        colFacultad.setCellValueFactory(new PropertyValueFactory<CentroComputo, String>("facultad"));
        colEdificio.setCellValueFactory(new PropertyValueFactory<CentroComputo, String>("edificio"));
        
        CentroComputoDAO centroComputoDAO = new CentroComputoDAO();
        ArrayList<CentroComputo> centrosComputo;
        centrosComputo = centroComputoDAO.recuperarCentroComputo();
    
        ObservableList<CentroComputo> centrosComputoObservable = FXCollections.observableArrayList();
        for(CentroComputo centrocomputo : centrosComputo){
            centrosComputoObservable.add(centrocomputo);
        }
        
        tbCentroComputo.setItems(centrosComputoObservable);
        
        if(centrosComputo.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion");
            alert.setHeaderText("No hay problematicas registradas para consultar");
            alert.setContentText("Intentelo de nuevo más tarde");
            alert.showAndWait();
        }
    }
    
    private final ListChangeListener<CentroComputo> selectorProblematica = new ListChangeListener<CentroComputo>() {
        @Override
        public void onChanged(ListChangeListener.Change<? extends CentroComputo> c) {
                
            }
    };
    
    private void mostrarCentroComputo(){
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.mostrarTabla();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultarCentroDeComputoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        final ObservableList<CentroComputo> tablaCC = tbCentroComputo.getSelectionModel().getSelectedItems();
        tablaCC.addListener(selectorProblematica);
    }    

    @FXML
    private void clicSalir(ActionEvent event) throws SQLException, IOException {
        try {
            FXMLLoader loaderVentanaPrincipal = new FXMLLoader(getClass().getResource("/sistemadecontroldeinventario/VentanaPrincipalFXML.fxml"));
            Parent ventanaPrincipal = loaderVentanaPrincipal.load();
            VentanaPrincipalFXMLControlador controladorVentanaPrincipal = loaderVentanaPrincipal.getController();
            
            Scene escenaVentanaPrincipal = new Scene(ventanaPrincipal);
            Stage stageVentanaPrincipal = new Stage();
            stageVentanaPrincipal.setScene(escenaVentanaPrincipal);
            stageVentanaPrincipal.show();
        } catch (IOException ex) {
            Logger.getLogger(InicioSesionFXMLControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
