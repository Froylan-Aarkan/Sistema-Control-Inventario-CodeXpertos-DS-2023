/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.ConexionBaseDeDatos;
import Modelo.POJO.Hardware;
import Utilidades.Utilidades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.Alert;

/**
 *
 * @author froyl
 */
public class HardwareDAO {
    public static ArrayList<Hardware> recuperarTodoHardware() throws SQLException{
        ArrayList<Hardware> hardwareBD = null;
        Connection conexionBD = ConexionBaseDeDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String consulta = "SELECT * FROM hardware";
                PreparedStatement consultaHardware = conexionBD.prepareStatement(consulta);
                ResultSet resultadoConsulta = consultaHardware.executeQuery();
                hardwareBD = new ArrayList<>();
                
                while(resultadoConsulta.next()){
                    Hardware hardwareTemporal = new Hardware();
                    hardwareTemporal.setIdHardware(resultadoConsulta.getInt("idHardware"));
                    hardwareTemporal.setMarca(resultadoConsulta.getString("marca"));
                    hardwareTemporal.setModelo(resultadoConsulta.getString("modelo"));
                    hardwareTemporal.setNumeroSerie(resultadoConsulta.getString("numeroSerie"));
                    hardwareTemporal.setEstado(resultadoConsulta.getString("estado"));
                    hardwareBD.add(hardwareTemporal);
                }
                
            }catch(SQLException e){
                Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal al intentar recuperar los hardware registrados: " + e.getMessage(), Alert.AlertType.ERROR);
            }finally{
                conexionBD.close();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", "No hay conexion con la base de datos.", Alert.AlertType.ERROR);
        }
        return hardwareBD;
    }
}
