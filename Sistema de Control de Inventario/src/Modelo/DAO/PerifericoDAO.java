/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.ConexionBaseDeDatos;
import Modelo.POJO.Periferico;
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
public class PerifericoDAO {
    public static ArrayList<Periferico> recuperarTodoPeriferico() throws SQLException{
        ArrayList<Periferico> perifericosBD = null;
        Connection conexionBD = ConexionBaseDeDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String consulta = "SELECT * FROM perifericos";
                PreparedStatement consultaPerifericos = conexionBD.prepareStatement(consulta);
                ResultSet resultadoConsulta = consultaPerifericos.executeQuery();
                perifericosBD = new ArrayList<>();
                
                while(resultadoConsulta.next()){
                    Periferico perifericoTemporal = new Periferico();
                    perifericoTemporal.setIdPeriferico(resultadoConsulta.getInt("idPerifericos"));
                    perifericoTemporal.setTipo(resultadoConsulta.getString("tipo"));
                    perifericoTemporal.setMarca(resultadoConsulta.getString("marca"));
                    perifericoTemporal.setModelo(resultadoConsulta.getString("modelo"));
                    perifericoTemporal.setEstado(resultadoConsulta.getString("estado"));
                    perifericoTemporal.setNumeroSerie(resultadoConsulta.getString("numeroSerie"));
                    perifericoTemporal.setIdCentroComputo(resultadoConsulta.getByte("inalambrico"));
                    
                    perifericosBD.add(perifericoTemporal);
                }
                
            }catch(SQLException e){
                Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal al intentar recuperar los perifericos registrados: " + e.getMessage(), Alert.AlertType.ERROR);
            }finally{
                conexionBD.close();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", "No hay conexión con la base de datos, inténtelo más tarde.", Alert.AlertType.ERROR);
        }
        
        return perifericosBD;
    }
    
    public static boolean eliminarPeriferico(int idPeriferico) throws SQLException{
        Utilidades mensajeRespuesta = null;
        Connection conexionBD = ConexionBaseDeDatos.abrirConexionBaseDatos();
        boolean resultado = false;
        if(conexionBD != null){
            try{
                String consultaEliminar = "DELETE FROM perifericos WHERE idPerifericos = ?;";
                PreparedStatement consultaEliminarPerifericos = conexionBD.prepareStatement(consultaEliminar);
                consultaEliminarPerifericos.setInt(1, idPeriferico);
                int filasAfectadas = consultaEliminarPerifericos.executeUpdate();
                
                if(filasAfectadas > 0){
                    mensajeRespuesta.mostrarAlertaSimple("Operación finalizada con éxito",
                            "Se eliminó el periferico correctamente.",
                            Alert.AlertType.INFORMATION);
                    resultado = true;
                }else{
                    mensajeRespuesta.mostrarAlertaSimple("Operación fallida",
                            "No se pudo eliminar el periferico.",
                            Alert.AlertType.ERROR);
                }
            }catch(SQLException sqlException){
                mensajeRespuesta.mostrarAlertaSimple("Error",
                        "Algo ocurrió mal al intentar recuperar los perifericos registrados: " + sqlException.getMessage(),
                        Alert.AlertType.ERROR);
            }finally{
                conexionBD.close();
            }
        }else{
            mensajeRespuesta.mostrarAlertaSimple("Error de conexion",
                    "No hay conexión con la base de datos.",
                    Alert.AlertType.ERROR);
        }
        return resultado;
    }
}