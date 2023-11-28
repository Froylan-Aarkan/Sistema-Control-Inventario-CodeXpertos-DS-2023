/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.ConexionBaseDeDatos;
import Modelo.POJO.Bitacora;
import Modelo.POJO.VistaBitacora;
import Modelo.POJO.CentroComputo;
import Modelo.POJO.VistaBitacora;
import Utilidades.Utilidades;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.scene.control.Alert;

/**
 *
 * @author Elotlan
 */
public class BitacoraDAO {
    public int RegistrarCentroComputo(Bitacora bitacora) throws SQLException, ParseException{
        Connection conexionBD = ConexionBaseDeDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String query = "INSERT INTO bitacora (Hardware_idHardware, Mantenimiento_idBitacora, fecha, descripcion) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = conexionBD.prepareStatement(query);
                statement.setInt(1, bitacora.getIdHardware());
                statement.setInt(2, bitacora.getIdMantenimiento());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
                LocalDateTime localDateTime = LocalDateTime.parse(bitacora.getFecha(), formatter);

            // Convirtiendo LocalDateTime a Timestamp
                Timestamp timestamp = Timestamp.valueOf(localDateTime);
                statement.setTimestamp(3, timestamp);
                statement.setString(4, bitacora.getDescripcion());
                statement.executeUpdate();
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmación");
                alert.setHeaderText("Registro exitoso");
                alert.setContentText("El centro de cómputo fue creado exitosamente");
                alert.showAndWait();
            }catch (SQLException e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }    
        return 0;
    }
    
    public int buscarHardwareBitacora(int idHardware) throws SQLException, ParseException{
        Connection conexionBD = ConexionBaseDeDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String query = "SELECT COUNT(*) FROM bitacora WHERE Hardware_idHardware = ?";
                PreparedStatement statement = conexionBD.prepareStatement(query);
                statement.setInt(1, idHardware);
                ResultSet resultSet = statement.executeQuery();
                
                if(resultSet.next()){
                    int conteo = resultSet.getInt(1);
                    
                    if(conteo>0){
                        return 1;
                    }else{
                        return 0;
                    }
                }                
            }catch (SQLException e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }    
        return 0;
    }
    
    public static ArrayList<VistaBitacora> recuperarTodoHardware() throws SQLException, ParseException{
        ArrayList<VistaBitacora> bitacoraBD = null;
        Connection conexionBD = ConexionBaseDeDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String consulta = "SELECT h.idHardware, h.numeroSerie, b.fecha FROM hardware h JOIN bitacora b ON h.idHardware = b.Hardware_idHardware";
                PreparedStatement consultaHardware = conexionBD.prepareStatement(consulta);
                ResultSet resultadoConsulta = consultaHardware.executeQuery();
                bitacoraBD = new ArrayList<>();
                
                while(resultadoConsulta.next()){
                    VistaBitacora bitacoraTempo = new VistaBitacora();
                    bitacoraTempo.setIdHardware(resultadoConsulta.getInt("idHardware"));
                    bitacoraTempo.setNumeroSerie(resultadoConsulta.getString("numeroSerie"));

                    String fechaString = resultadoConsulta.getString("fecha");

                    // Utilizando SimpleDateFormat para convertir la cadena a java.util.Date
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    java.util.Date fechaDate = dateFormat.parse(fechaString);

                    // Creando un nuevo objeto java.sql.Date utilizando el timestamp de java.util.Date
                    java.sql.Date fechaSqlDate = new java.sql.Date(fechaDate.getTime());

                    // Almacenando la fecha como String en tu objeto Bitacora
                    bitacoraTempo.setFecha(fechaSqlDate.toString());
                    
                    bitacoraBD.add(bitacoraTempo);
                    
                }
                
            }catch(SQLException e){
                Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal al intentar recuperar las bitacoras registradas: " + e.getMessage(), Alert.AlertType.ERROR);
            }finally{
                conexionBD.close();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", "No hay conexión con la base de datos, inténtelo más tarde.", Alert.AlertType.ERROR);
        }
        return bitacoraBD;
    }
    
    
    public static Bitacora recuperarBitacoraPorId(int idHardware) throws SQLException, ParseException{
        Bitacora bitacoraConsultada = null;
        Connection conexionBD = ConexionBaseDeDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String consulta = "select * from bitacora where Hardware_idHardware = ?";
                PreparedStatement consultaCentroComputo = conexionBD.prepareStatement(consulta);
                consultaCentroComputo.setInt(1, idHardware);
                ResultSet resultadoConsulta = consultaCentroComputo.executeQuery();
                
                if(resultadoConsulta.next()){
                    bitacoraConsultada = new Bitacora();
                    bitacoraConsultada.setIdHardware(resultadoConsulta.getInt("Hardware_idHardware"));
                    bitacoraConsultada.setIdMantenimiento(resultadoConsulta.getInt("Mantenimiento_idBitacora"));
                    bitacoraConsultada.setDescripcion(resultadoConsulta.getString("descripcion"));
                    
                    String fechaString = resultadoConsulta.getString("fecha");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    java.util.Date fechaDate = dateFormat.parse(fechaString);
                    java.sql.Date fechaSqlDate = new java.sql.Date(fechaDate.getTime());
                    bitacoraConsultada.setFecha(fechaSqlDate.toString());
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
        
        return bitacoraConsultada;
    }
    
}
