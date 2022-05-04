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
public class registro {
    private int idRegistro;
    private int DniRegistro;
    private String nombreRegistro;
    private String correoRegistro;
    private String passRegistro;
    private int telefonoRegistro;
    
    public registro(){
        
    }

    public registro(int idRegistro, int DniRegistro, String nombreRegistro, String correoRegistro, String passRegistro, int telefonoRegistro) {
        this.idRegistro = idRegistro;
        this.DniRegistro = DniRegistro;
        this.nombreRegistro = nombreRegistro;
        this.correoRegistro = correoRegistro;
        this.passRegistro = passRegistro;
        this.telefonoRegistro = telefonoRegistro;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int getDniRegistro() {
        return DniRegistro;
    }

    public void setDniRegistro(int DniRegistro) {
        this.DniRegistro = DniRegistro;
    }

    public String getNombreRegistro() {
        return nombreRegistro;
    }

    public void setNombreRegistro(String nombreRegistro) {
        this.nombreRegistro = nombreRegistro;
    }

    public String getCorreoRegistro() {
        return correoRegistro;
    }

    public void setCorreoRegistro(String correoRegistro) {
        this.correoRegistro = correoRegistro;
    }

    public String getPassRegistro() {
        return passRegistro;
    }

    public void setPassRegistro(String passRegistro) {
        this.passRegistro = passRegistro;
    }

    public int getTelefonoRegistro() {
        return telefonoRegistro;
    }

    public void setTelefonoRegistro(int telefonoRegistro) {
        this.telefonoRegistro = telefonoRegistro;
    }
    
    
}
