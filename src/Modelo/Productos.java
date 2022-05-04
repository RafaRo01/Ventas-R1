
package Modelo;

/**
 *
 * @author Rafael Rosas
 */
public class Productos {
    private int idProductos; 
    private String codigoProductos;
    private String nombreProductos;
    private String proveedorProductos;
    private int stockProductos;
    private double precioProductos;
    
    public Productos(){
        
    }

    public Productos(int idProductos, String codigoProductos, String nombreProductos, String proveedorProductos, int stockProductos, double precioProductos) {
        this.idProductos = idProductos;
        this.codigoProductos = codigoProductos;
        this.nombreProductos = nombreProductos;
        this.proveedorProductos = proveedorProductos;
        this.stockProductos = stockProductos;
        this.precioProductos = precioProductos;
    }

    public int getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(int idProductos) {
        this.idProductos = idProductos;
    }

    public String getCodigoProductos() {
        return codigoProductos;
    }

    public void setCodigoProductos(String codigoProductos) {
        this.codigoProductos = codigoProductos;
    }

    public String getNombreProductos() {
        return nombreProductos;
    }

    public void setNombreProductos(String nombreProductos) {
        this.nombreProductos = nombreProductos;
    }

    public String getProveedorProductos() {
        return proveedorProductos;
    }

    public void setProveedorProductos(String proveedorProductos) {
        this.proveedorProductos = proveedorProductos;
    }

    public int getStockProductos() {
        return stockProductos;
    }

    public void setStockProductos(int stockProductos) {
        this.stockProductos = stockProductos;
    }

    public double getPrecioProductos() {
        return precioProductos;
    }

    public void setPrecioProductos(double precioProductos) {
        this.precioProductos = precioProductos;
    }
    
}
