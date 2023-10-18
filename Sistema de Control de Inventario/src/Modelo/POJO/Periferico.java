/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.POJO;

/**
 *
 * @author froyl
 */
public class Periferico {
    private int idPeriferico;
    private String tipo;
    private String marca;
    private String modelo;
    private String estado;
    private String numeroSerie;
    private boolean inalambrico;
    private int idCentroComputo;

    public Periferico() {
    }

    public int getIdPeriferico() {
        return idPeriferico;
    }

    public String getTipo() {
        return tipo;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getEstado() {
        return estado;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public boolean isInalambrico() {
        return inalambrico;
    }

    public int getIdCentroComputo() {
        return idCentroComputo;
    }

    public void setIdPeriferico(int idPeriferico) {
        this.idPeriferico = idPeriferico;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public void setInalambrico(boolean inalambrico) {
        this.inalambrico = inalambrico;
    }

    public void setIdCentroComputo(int idCentroComputo) {
        this.idCentroComputo = idCentroComputo;
    }
    
}
