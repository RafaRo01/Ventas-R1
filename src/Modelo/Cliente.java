package Modelo;

/**
 *
 * @author Rafael Rosas
 */
public class Cliente {
    private int idCliente;
    private int dniCliente;
    private String nombreCliente;
    private int telefonoCliente;
    private String direccionCliente;
    private String razonCliente;
    
    

    public Cliente() {
    }

    public Cliente(int idCliente, int dniCliente, String nombreCliente, int telefonoCliente, String direccionCliente, String razonCliente) {
        this.idCliente = idCliente;
        this.dniCliente = dniCliente;
        this.nombreCliente = nombreCliente;
        this.telefonoCliente = telefonoCliente;
        this.direccionCliente = direccionCliente;
        this.razonCliente = razonCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(int dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public int getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(int telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getRazonCliente() {
        return razonCliente;
    }

    public void setRazonCliente(String razonCliente) {
        this.razonCliente = razonCliente;
    }
    
    
            
}
