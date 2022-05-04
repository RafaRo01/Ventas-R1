/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;


public class ProveedorDao {
  Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn= new Conexion();
    
    public boolean  RegistrarProveedor(Proveedor pr){
        
        String sql= "INSERT INTO proveedor (ruc, Nombre, Telefono, Direccion, Razon) VALUES(?,?,?,?,?)";
        try{
            con= cn.getConnection();
            ps= con.prepareStatement(sql);
            ps.setInt(1, pr.getRucProveedor());
            ps.setString(2, pr.getNombreProveedor());
            ps.setInt(3, pr.getTelefonoProveedor());
            ps.setString(4, pr.getDireccionProveedor());
            ps.setString(5, pr.getRazonProveedor());
            ps.execute();
            return true;            
        }catch(SQLException e){
            JOptionPane.showInputDialog(null, e.toString());
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.out.println(e.toString());
            }
        }
        
    }
    public List ListarProveedor(){
        List<Proveedor> Listapr = new ArrayList();
        String sql = "SELECT * FROM proveedor";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Proveedor pr = new Proveedor();
                pr.setIdProveedor(rs.getInt("id"));
                pr.setRucProveedor(rs.getInt("ruc"));
                pr.setNombreProveedor(rs.getString("Nombre"));
                pr.setTelefonoProveedor(rs.getInt("Telefono"));
                pr.setDireccionProveedor(rs.getString("Direccion"));
                pr.setRazonProveedor(rs.getString("Razon"));
                Listapr.add(pr);
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Listapr;
    }
    
    public boolean EliminarProveedor(int id){
        String sql = "DELETE FROM proveedor WHERE id = ? ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public boolean ModificarProveedor(Proveedor pr){
        String sql = "UPDATE proveedor SET ruc=?, Nombre=?, Telefono=?, Direccion=? WHERE id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pr.getRucProveedor());
            ps.setString(2, pr.getNombreProveedor());
            ps.setInt(3, pr.getTelefonoProveedor());
            ps.setString(4, pr.getDireccionProveedor());
            ps.setInt(5, pr.getIdProveedor());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
}
