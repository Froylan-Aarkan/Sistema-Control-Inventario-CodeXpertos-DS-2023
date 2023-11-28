/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.POJO;

/**
 *
 * @author Elotlan
 */
public class VistaBitacora {
    private int idHardware;
    private String numeroSerie;
    private String fecha;
    
    public VistaBitacora(){
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }
    
    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public int getIdHardware() {
        return idHardware;
    }
    
    public void setIdHardware(int idHardware) {
        this.idHardware = idHardware;
    }

    
}
