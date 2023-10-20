/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.ConexionBaseDeDatos;
import Modelo.POJO.Usuario;
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
public class UsuarioDAO {
    public static Usuario verificarUsuario(String correoInstitucional, String contrasenia) throws SQLException{
        Usuario usuarioSesion = null;
        Connection conexionBD = ConexionBaseDeDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try {
                String consulta = "SELECT * FROM usuario WHERE correoInstitucional = ? AND contrasenia = ?";
                
                PreparedStatement consultaLogin = conexionBD.prepareStatement(consulta);
                consultaLogin.setString(1, correoInstitucional);
                consultaLogin.setString(2, contrasenia);
                ResultSet resultadoConsulta = consultaLogin.executeQuery();
                usuarioSesion = new Usuario();
                
                if(resultadoConsulta.next()){
                    usuarioSesion.setIdUsuario(resultadoConsulta.getInt("idUsuario"));
                    usuarioSesion.setCargo(resultadoConsulta.getString("cargo"));
                    usuarioSesion.setNombreCompleto(resultadoConsulta.getString("nombreCompleto"));
                    usuarioSesion.setCorreoInstitucional(resultadoConsulta.getString("correoInstitucional"));
                    usuarioSesion.setContrasenia(resultadoConsulta.getString("contrasenia"));
                    usuarioSesion.setIdCentroComputo(resultadoConsulta.getInt("CentroComputo_idCentroComputo"));                 
                }else{
                    usuarioSesion.setIdUsuario(0);
                }
            } catch (SQLException e) {
                Utilidades.mostrarAlertaSimple("Error", "Algo ocurrió mal al intentar iniciar sesión: " + e.getMessage(), Alert.AlertType.ERROR);
            } finally {
                conexionBD.close();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", "No hay conexion con la base de datos.", Alert.AlertType.ERROR);
        }
        
        return usuarioSesion;
    }
}
