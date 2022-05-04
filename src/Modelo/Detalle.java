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
public class Detalle {
    
    private int idDetalle;
    private String cod_proDetalle;
    private int cantidadDetalle;
    private double precioDetalle;
    private int id_ventaDetalle;
    
    public Detalle(){
        
    }

    public Detalle(int idDetalle, String cod_proDetalle, int cantidadDetalle, double precioDetalle, int id_ventaDetalle) {
        this.idDetalle = idDetalle;
        this.cod_proDetalle = cod_proDetalle;
        this.cantidadDetalle = cantidadDetalle;
        this.precioDetalle = precioDetalle;
        this.id_ventaDetalle = id_ventaDetalle;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public String getCod_proDetalle() {
        return cod_proDetalle;
    }

    public void setCod_proDetalle(String cod_proDetalle) {
        this.cod_proDetalle = cod_proDetalle;
    }

    public int getCantidadDetalle() {
        return cantidadDetalle;
    }

    public void setCantidadDetalle(int cantidadDetalle) {
        this.cantidadDetalle = cantidadDetalle;
    }

    public double getPrecioDetalle() {
        return precioDetalle;
    }

    public void setPrecioDetalle(double precioDetalle) {
        this.precioDetalle = precioDetalle;
    }

    public int getId_ventaDetalle() {
        return id_ventaDetalle;
    }

    public void setId_ventaDetalle(int id_ventaDetalle) {
        this.id_ventaDetalle = id_ventaDetalle;
    }
    
    
}
