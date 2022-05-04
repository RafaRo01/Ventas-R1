/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Rafael Rosas
 */
public class Config {
    private int idConfig;
    private int rucConfig;
    private String nombreConfig;
    private int telefonoConfig;
    private String direccionConfig;
    private String razonConfig;
    
    public Config(){
        
    }

    public Config(int idConfig, int rucConfig, String nombreConfig, int telefonoConfig, String direccionConfig, String razonConfig) {
        this.idConfig = idConfig;
        this.rucConfig = rucConfig;
        this.nombreConfig = nombreConfig;
        this.telefonoConfig = telefonoConfig;
        this.direccionConfig = direccionConfig;
        this.razonConfig = razonConfig;
    }

    public int getIdConfig() {
        return idConfig;
    }

    public void setIdConfig(int idConfig) {
        this.idConfig = idConfig;
    }

    public int getRucConfig() {
        return rucConfig;
    }

    public void setRucConfig(int rucConfig) {
        this.rucConfig = rucConfig;
    }

    public String getNombreConfig() {
        return nombreConfig;
    }

    public void setNombreConfig(String nombreConfig) {
        this.nombreConfig = nombreConfig;
    }

    public int getTelefonoConfig() {
        return telefonoConfig;
    }

    public void setTelefonoConfig(int telefonoConfig) {
        this.telefonoConfig = telefonoConfig;
    }

    public String getDireccionConfig() {
        return direccionConfig;
    }

    public void setDireccionConfig(String direccionConfig) {
        this.direccionConfig = direccionConfig;
    }

    public String getRazonConfig() {
        return razonConfig;
    }

    public void setRazonConfig(String razonConfig) {
        this.razonConfig = razonConfig;
    }
    

    
    
}
