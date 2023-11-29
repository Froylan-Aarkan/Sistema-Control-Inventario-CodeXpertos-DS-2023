/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadecontroldeinventario.bitacora;

import Modelo.DAO.BitacoraDAO;
import Modelo.POJO.Bitacora;
import Modelo.POJO.VistaBitacora;
import Utilidades.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Elotlan
 */
public class ConsultarBitacoraFXMLController implements Initializable {

    @FXML
    private Button btnSalir;
    @FXML
    private Button btnConsultar;
    @FXML
    private TableView<VistaBitacora> tbBitacora;
    @FXML
    private TableColumn colNumSerie;
    @FXML
    private TableColumn colFecha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            mostrarTabla();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultarBitacoraFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ConsultarBitacoraFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void clicCancelar(ActionEvent event) {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clicRegistrar(ActionEvent event) throws SQLException, ParseException, IOException {
        if(verificarSeleccion()){
            BitacoraDAO bitacoraDAO = new BitacoraDAO();
            Bitacora bitacora = new Bitacora();
            bitacora = bitacoraDAO.recuperarBitacoraPorId(tbBitacora.getSelectionModel().getSelectedItem().getIdHardware());
            String num = tbBitacora.getSelectionModel().getSelectedItem().getNumeroSerie();
            try{
                FXMLLoader loaderBitacoraConsultada = new FXMLLoader(getClass().getResource("BitacoraConsultadaFXML.fxml"));
                Parent ventanaInformacionCentroDeComputo = loaderBitacoraConsultada.load();
                
                BitacoraConsultadaFXMLController controlador = loaderBitacoraConsultada.getController();
                controlador.recibirInfo(bitacora, num);
                
                Scene escenarioInformacionCentroDeComputo = new Scene(ventanaInformacionCentroDeComputo);
                Stage stageInformacionCentroDeComputo = new Stage();
                stageInformacionCentroDeComputo.setScene(escenarioInformacionCentroDeComputo);
                stageInformacionCentroDeComputo.initModality(Modality.APPLICATION_MODAL);
                stageInformacionCentroDeComputo.showAndWait();

                Stage stage = (Stage) btnConsultar.getScene().getWindow();
                stage.close();
            }catch (IOException ex) {
                Logger.getLogger(BitacoraConsultadaFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            Utilidades.mostrarAlertaSimple("Equipo no seleccionado", "No se ha seleccionado el equipo de cómputo a consultar.", Alert.AlertType.WARNING);
        }
    }
    
    public void mostrarTabla() throws SQLException, ParseException{
        colNumSerie.setCellValueFactory(new PropertyValueFactory<VistaBitacora, String>("numeroSerie"));
        colFecha.setCellValueFactory(new PropertyValueFactory<VistaBitacora, String>("fecha"));
        
        BitacoraDAO bitacoraDAO = new BitacoraDAO();
        ArrayList<VistaBitacora> bitacorasDisponibles;
        bitacorasDisponibles = bitacoraDAO.recuperarTodoHardware();
        
        ObservableList<VistaBitacora> bitacorasObservables = FXCollections.observableArrayList();
        for(VistaBitacora bitacoras : bitacorasDisponibles){
            bitacorasObservables.add(bitacoras);
        }
        
        tbBitacora.setItems(bitacorasObservables);
        
        if(bitacorasDisponibles.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion");
            alert.setHeaderText("No hay bitacoras registradas para consultar");
            alert.setContentText("Intentelo de nuevo más tarde");
            alert.showAndWait();
        }
    }
    
    private boolean verificarSeleccion(){
        return tbBitacora.getSelectionModel().getSelectedItem() != null;
    }
    
    
}
