/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.ConexionBaseDeDatos;
import Modelo.POJO.Software;
import Utilidades.Utilidades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.Alert;

/**
 *
 * @author johno
 */
public class SoftwareDAO {
    public static ArrayList<Software> recuperarTodoSoftware() throws SQLException{
        ArrayList<Software> softwareBD = null;
        Connection conexionBD = ConexionBaseDeDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String consulta = "SELECT idSoftware, nombre, arquitectura, peso FROM software";
                PreparedStatement consultaSoftware = conexionBD.prepareStatement(consulta);
                ResultSet resultadoConsulta = consultaSoftware.executeQuery();
                softwareBD = new ArrayList<>();
                
                while(resultadoConsulta.next()){
                    Software softwareTemporal = new Software();
                    softwareTemporal.setIdSoftware(resultadoConsulta.getInt("idSoftware"));
                    softwareTemporal.setNombre(resultadoConsulta.getString("nombre"));
                    softwareTemporal.setPeso(resultadoConsulta.getString("peso"));
                    softwareTemporal.setArquitectura(resultadoConsulta.getInt("arquitectura"));
                    softwareBD.add(softwareTemporal);
                }
                
            }catch(SQLException e){
                Utilidades.mostrarAlertaSimple("Error", 
                        "Algo ocurrió mal al intentar recuperar los software registrados: " + e.getMessage(),
                        Alert.AlertType.ERROR);
            }finally{
                conexionBD.close();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", 
                    "No hay conexion con la base de datos.", 
                    Alert.AlertType.ERROR);
        }
        return softwareBD;
    }
    
    public static boolean eliminarSoftware(int idSoftware) throws SQLException{
        Utilidades mensajeRespuesta = null;
        Connection conexionBD = ConexionBaseDeDatos.abrirConexionBaseDatos();
        boolean resultado = true;
        if(conexionBD != null){
            try{
                String consultaEliminar = "DELETE FROM software WHERE idSoftware = ?";
                PreparedStatement consultaEliminarSoftware = conexionBD.prepareStatement(consultaEliminar);
                consultaEliminarSoftware.setInt(1, idSoftware);
                int filasAfectadas = consultaEliminarSoftware.executeUpdate();
                
                if(filasAfectadas > 0){
                    mensajeRespuesta.mostrarAlertaSimple("Operación finalizada con éxito",
                            "Se eliminó el software correctamente.",
                            Alert.AlertType.INFORMATION);
                    resultado = true;
                }else{
                    mensajeRespuesta.mostrarAlertaSimple("Operación fallida",
                            "No se pudo eliminar el software.",
                            Alert.AlertType.ERROR);
                    resultado = false;
                }
            }catch(SQLException sqlException){
                mensajeRespuesta.mostrarAlertaSimple("Error",
                        "Algo ocurrió mal al intentar recuperar los software registrados: " + sqlException.getMessage(),
                        Alert.AlertType.ERROR);
                resultado = false;
            }finally{
                conexionBD.close();
            }
        }else{
            mensajeRespuesta.mostrarAlertaSimple("Error de conexion",
                    "No hay conexión con la base de datos.",
                    Alert.AlertType.ERROR);
            resultado = false;
        }
        return resultado;
    }
}
