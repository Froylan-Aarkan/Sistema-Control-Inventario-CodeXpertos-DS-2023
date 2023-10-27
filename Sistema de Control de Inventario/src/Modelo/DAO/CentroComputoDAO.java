/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.ConexionBaseDeDatos;
import Modelo.POJO.CentroComputo;
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
    
    public static ArrayList<CentroComputo> recuperarTodoCentroDeComputo() throws SQLException{
        ArrayList<CentroComputo> centrosComputo = null;
        Connection conexionBD = ConexionBaseDeDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String consulta = "SELECT * FROM centroComputo";
                PreparedStatement consultaCentroComputo = conexionBD.prepareStatement(consulta);
                ResultSet resultadoConsulta = consultaCentroComputo.executeQuery();
                centrosComputo = new ArrayList<>();
                
                while(resultadoConsulta.next()){
                    CentroComputo centroComputoTemporal = new CentroComputo();
                    centroComputoTemporal.setIdCentroComputo(resultadoConsulta.getInt("idCentroComputo"));
                    centroComputoTemporal.setEdificio(resultadoConsulta.getString("edificio"));
                    centroComputoTemporal.setDireccion(resultadoConsulta.getString("direccion"));
                    centroComputoTemporal.setFacultad(resultadoConsulta.getString("facultad"));
                    centroComputoTemporal.setAula(resultadoConsulta.getString("aula"));
                    centroComputoTemporal.setPiso(resultadoConsulta.getString("piso"));
                    centrosComputo.add(centroComputoTemporal);
                }               
            }catch(SQLException e){
                Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal al intentar recuperar los centros de cómputo: " + e.getMessage(), Alert.AlertType.ERROR);
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", "No hay conexion con la base de datos.", Alert.AlertType.ERROR);
        }
        return centrosComputo;
    }
    
    public static CentroComputo recuperarCentroComputoPorId(int idCentroComputo) throws SQLException{
        CentroComputo centroComputo = null;
        Connection conexionBD = ConexionBaseDeDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String consulta = "SELECT * FROM centroComputo WHERE idCentroComputo = ?";
                PreparedStatement consultaCentroComputo = conexionBD.prepareStatement(consulta);
                consultaCentroComputo.setInt(1, idCentroComputo);
                ResultSet resultadoConsulta = consultaCentroComputo.executeQuery();
                
                if(resultadoConsulta.next()){
                    centroComputo = new CentroComputo();
                    centroComputo.setIdCentroComputo(resultadoConsulta.getInt("idCentroComputo"));
                    centroComputo.setDireccion(resultadoConsulta.getString("direccion"));
                    centroComputo.setEdificio(resultadoConsulta.getString("edificio"));
                    centroComputo.setFacultad(resultadoConsulta.getString("facultad"));
                    centroComputo.setPiso(resultadoConsulta.getString("piso"));
                }
                
            }catch(SQLException e){
                Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal al intentar recuperar los centros de cómputo: " + e.getMessage(), Alert.AlertType.ERROR);
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", "No hay conexion con la base de datos.", Alert.AlertType.ERROR);
        }
        
        return centroComputo;
    }
    
    public static boolean registrarCentroDeComputo(CentroComputo centroComputo){
        boolean resultadoOperacion = false;
        Connection conexionBD = ConexionBaseDeDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String consulta = "";
                PreparedStatement consultaHardware = conexionBD.prepareStatement(consulta);
            }catch(SQLException e){
                Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal al intentar registrar el centro de cómputo: " + e.getMessage(), Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", "No hay conexion con la base de datos.", Alert.AlertType.ERROR);
        }
        
        return resultadoOperacion;
    }
}
