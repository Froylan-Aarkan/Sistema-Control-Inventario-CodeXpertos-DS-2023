/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.ConexionBaseDeDatos;
import Utilidades.Utilidades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;

/**
 *
 * @author froyl
 */
public class CentroComputoDAO {
    public static String recuperarAulaCentroComputoPorIdCentroComputo(int idCentroComputo) throws SQLException{
        String aula = "";
        Connection conexionBD = ConexionBaseDeDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String consulta = "SELECT aula FROM centroComputo WHERE idCentroComputo = ?";
                PreparedStatement consultaCentroComputo = conexionBD.prepareStatement(consulta);
                consultaCentroComputo.setInt(1, idCentroComputo);
                ResultSet resultadoConsulta = consultaCentroComputo.executeQuery();
                
                if(resultadoConsulta.next()){
                    aula = resultadoConsulta.getString("aula");
                }
            }catch(SQLException e){
                Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal al intentar recuperar el aula del centro de cómputo: " + e.getMessage(), Alert.AlertType.ERROR);
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", "No hay conexion con la base de datos.", Alert.AlertType.ERROR);
        }
        
        return aula;
    }
}
