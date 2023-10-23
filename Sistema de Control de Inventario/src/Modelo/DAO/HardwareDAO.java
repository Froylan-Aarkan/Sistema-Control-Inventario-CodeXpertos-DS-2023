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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    
    public static boolean registrarEquipoComputo(Hardware equipoComputoNuevo) throws SQLException{
        boolean resultadoOperacion = false;
        
        Connection conexionBD = ConexionBaseDeDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String consulta = "INSERT INTO hardware (modelo, marca, numeroSerie, procesador, almacenamiento, ram, direccionMac, direccionIp, sistemaOperativo, arquitectura, grafica, tarjetaMadre, estado, fechaIngreso) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                
                PreparedStatement sentenciaHardware = conexionBD.prepareStatement(consulta);
                sentenciaHardware.setString(1, equipoComputoNuevo.getModelo());
                sentenciaHardware.setString(2, equipoComputoNuevo.getMarca());
                sentenciaHardware.setString(3, equipoComputoNuevo.getNumeroSerie());
                sentenciaHardware.setString(4, equipoComputoNuevo.getProcesador());
                sentenciaHardware.setFloat(5, equipoComputoNuevo.getAlmacenamiento());
                sentenciaHardware.setFloat(6, equipoComputoNuevo.getRam());
                sentenciaHardware.setString(7, equipoComputoNuevo.getDireccionMac());
                sentenciaHardware.setString(8, equipoComputoNuevo.getDireccionIp());
                sentenciaHardware.setString(9, equipoComputoNuevo.getSistemaOperativo());
                sentenciaHardware.setInt(10, equipoComputoNuevo.getArquitectura());
                sentenciaHardware.setString(11, equipoComputoNuevo.getGrafica());
                sentenciaHardware.setString(12, equipoComputoNuevo.getTarjetaMadre());
                sentenciaHardware.setString(13, "Funcional");
                
                LocalDateTime fechaHoraActual = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                sentenciaHardware.setString(14, fechaHoraActual.format(formatter).toString());
                
                int filasAfectadas = sentenciaHardware.executeUpdate();
                
                if(filasAfectadas > 0){
                    resultadoOperacion = true;
                }
            }catch(SQLException e){
                Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal al intentar registrar el equipo de cómputo: " + e.getMessage(), Alert.AlertType.ERROR);
            }finally{
                conexionBD.close();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", "No hay conexion con la base de datos.", Alert.AlertType.ERROR);
        }
        
        return resultadoOperacion;
    }
    
    public static Hardware buscarHardwarePorNumeroSerie(String numeroSerie) throws SQLException{
        Hardware equipoComputoBusqueda = null;
        Connection conexionBD = ConexionBaseDeDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
            String consulta = "SELECT * FROM hardware WHERE numeroSerie = ?";
            PreparedStatement consultaHardware = conexionBD.prepareStatement(consulta);
            consultaHardware.setString(1, numeroSerie);
            ResultSet resultadoConsulta = consultaHardware.executeQuery();
                       
            if(resultadoConsulta.next()){
                equipoComputoBusqueda = new Hardware();
                equipoComputoBusqueda.setIdHardware(resultadoConsulta.getInt("idHardware"));
                equipoComputoBusqueda.setMarca(resultadoConsulta.getString("marca"));
                equipoComputoBusqueda.setModelo(resultadoConsulta.getString("modelo"));
                equipoComputoBusqueda.setNumeroSerie(resultadoConsulta.getString("numeroSerie"));
                equipoComputoBusqueda.setProcesador(resultadoConsulta.getString("procesador"));
                equipoComputoBusqueda.setArquitectura(resultadoConsulta.getInt("arquitectura"));
                equipoComputoBusqueda.setTarjetaMadre(resultadoConsulta.getString("tarjetaMadre"));
                equipoComputoBusqueda.setSistemaOperativo(resultadoConsulta.getString("sistemaOperativo"));
                equipoComputoBusqueda.setGrafica(resultadoConsulta.getString("grafica"));
                equipoComputoBusqueda.setRam(resultadoConsulta.getFloat("ram"));
                equipoComputoBusqueda.setDireccionMac(resultadoConsulta.getString("direccionMac"));
                equipoComputoBusqueda.setDireccionIp(resultadoConsulta.getString("direccionIp"));
                equipoComputoBusqueda.setAlmacenamiento(resultadoConsulta.getFloat("almacenamiento"));
                equipoComputoBusqueda.setEstado(resultadoConsulta.getString("estado"));
                equipoComputoBusqueda.setFechaIngreso(resultadoConsulta.getDate("fechaIngreso"));
                equipoComputoBusqueda.setPosicion(resultadoConsulta.getString("posicion"));
                equipoComputoBusqueda.setIdCentroComputo(resultadoConsulta.getInt("CentroComputo_idCentroComputo"));
                equipoComputoBusqueda.setCentroComputo(CentroComputoDAO.recuperarAulaCentroComputoPorIdCentroComputo(equipoComputoBusqueda.getIdCentroComputo()));
            }
            }catch(SQLException e){
                Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal al intentar recuperar el equipo de cómputo: " + e.getMessage(), Alert.AlertType.ERROR);
            }finally{
                conexionBD.close();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", "No hay conexion con la base de datos.", Alert.AlertType.ERROR);
        }
        
        return equipoComputoBusqueda;
    }
    
    public static boolean eliminarEquipoComputo(int idHardware) throws SQLException{
        boolean resultadoOperacion = false;
        Connection conexionBD = ConexionBaseDeDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String consulta = "DELETE FROM hardware WHERE idHardware = ?";
                PreparedStatement consultaHardware = conexionBD.prepareStatement(consulta);
                consultaHardware.setInt(1, idHardware);
                int filasAfectadas = consultaHardware.executeUpdate();
                
                if(filasAfectadas > 0){
                    resultadoOperacion = true;
                }
            }catch(SQLException e){
                Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal al intentar eliminar el equipo de cómputo: " + e.getMessage(), Alert.AlertType.ERROR);
            }finally{
                conexionBD.close();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", "No hay conexion con la base de datos.", Alert.AlertType.ERROR);
        }
        
        return resultadoOperacion;
    }
    
    public static boolean EliminarSoftwareHardware(int idSoftware, int idHardware){
        boolean resultadoOperacion = false;
        Connection conexionBD = ConexionBaseDeDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String consulta = "delete from hardwaresoftware where Hardware_idHardware = ? and Software_idSoftware = ?";
                PreparedStatement eliminarSoftwareHardware = conexionBD.prepareStatement(consulta);
                eliminarSoftwareHardware.setInt(1, idSoftware);
                eliminarSoftwareHardware.setInt(2, idHardware);
                int numFilas = eliminarSoftwareHardware.executeUpdate();
                
                if(numFilas > 0){
                    resultadoOperacion = true;
                }else{
                    Utilidades.mostrarAlertaSimple("Error", 
                        "Error al intentar eliminar el software del hardware ",
                        Alert.AlertType.ERROR);
                    conexionBD.close();
                }
            }catch(SQLException e){
                e.getMessage();
            }
           
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", 
                    "No hay conexion con la base de datos.", 
                    Alert.AlertType.ERROR);
        }
        return resultadoOperacion;
    }
    
    public static ArrayList<Hardware> recuperarHardwareSoftware(int idSoftware) throws SQLException{
        ArrayList<Hardware> hardwareBD = null;
        Connection conexionBD = ConexionBaseDeDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String consulta = "SELECT idHardware, modelo, estado, posicion, marca FROM hardware inner join hardwaresoftware where hardwaresoftware.Software_idSoftware = ? and hardwaresoftware.Hardware_idHardware = idHardware";
                PreparedStatement consultaHardware = conexionBD.prepareStatement(consulta);
                consultaHardware.setInt(1, idSoftware);
                ResultSet resultadoConsulta = consultaHardware.executeQuery();
                hardwareBD = new ArrayList<>();
                
                while(resultadoConsulta.next()){
                    Hardware hardwareTemporal = new Hardware();
                    hardwareTemporal.setIdHardware(resultadoConsulta.getInt("idHardware"));
                    hardwareTemporal.setMarca(resultadoConsulta.getString("marca"));
                    hardwareTemporal.setModelo(resultadoConsulta.getString("modelo"));
                    hardwareTemporal.setPosicion(resultadoConsulta.getString("posicion"));
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
