package Modelo;

/**
 *
 * @author Rafael Rosas
 */
public class Venta {
    private int idVenta;
    private String clienteVenta;
    private String vendedorVenta;
    private double totalVenta;
    //private String fechaVenta;
    
    public Venta(){
        
    }
    

    public Venta(int idVenta, String clienteVenta, String vendedorVenta, double totalVenta) {
        this.idVenta = idVenta;
        this.clienteVenta = clienteVenta;
        this.vendedorVenta = vendedorVenta;
        this.totalVenta = totalVenta;
        
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getClienteVenta() {
        return clienteVenta;
    }

    public void setClienteVenta(String clienteVenta) {
        this.clienteVenta = clienteVenta;
    }

    public String getVendedorVenta() {
        return vendedorVenta;
    }

    public void setVendedorVenta(String vendedorVenta) {
        this.vendedorVenta = vendedorVenta;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

/*    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }*/

   
    
    
}
