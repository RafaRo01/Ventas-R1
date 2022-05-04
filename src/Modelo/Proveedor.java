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
public class Proveedor {
    
    private int idProveedor;
    private int rucProveedor;
    private String nombreProveedor;
    private int telefonoProveedor;
    private String direccionProveedor;
    private String razonProveedor;
    
    public Proveedor(){
        
    }

    public Proveedor(int idProveedor, int rucProveedor, String nombreProveedor, int telefonoProveedor, String direccionProveedor, String razonProveedor) {
        this.idProveedor = idProveedor;
        this.rucProveedor = rucProveedor;
        this.nombreProveedor = nombreProveedor;
        this.telefonoProveedor = telefonoProveedor;
        this.direccionProveedor = direccionProveedor;
        this.razonProveedor = razonProveedor;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getRucProveedor() {
        return rucProveedor;
    }

    public void setRucProveedor(int rucProveedor) {
        this.rucProveedor = rucProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public int getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public void setTelefonoProveedor(int telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }

    public String getDireccionProveedor() {
        return direccionProveedor;
    }

    public void setDireccionProveedor(String direccionProveedor) {
        this.direccionProveedor = direccionProveedor;
    }

    public String getRazonProveedor() {
        return razonProveedor;
    }

    public void setRazonProveedor(String razonProveedor) {
        this.razonProveedor = razonProveedor;
    }

    
    
    
    
    
    
    
}
