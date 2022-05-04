package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

public class UsuarioDao {
     Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn= new Conexion();
    
    
    public boolean  RegistrarUsuario(Usuario us){
        
        String sql= "INSERT INTO usuarios (Nombre, Correo, Pass, rol) VALUES(?,?,?,?)";
        try{
            con= cn.getConnection();
            ps= con.prepareStatement(sql);
            ps.setString(1,us.getNombreUsuario());
            ps.setString(2, us.getCorreoUsuario());
            ps.setString(3, us.getContraUsuario());
            ps.setString(4, us.getRolUsuario());
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
    public List ListarUsuario(){
        List<Usuario> Listaus = new ArrayList();
        String sql = "SELECT * FROM usuarios";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {  
                Usuario us= new Usuario();
                us.setIdUsuario(rs.getInt("id"));
                us.setNombreUsuario(rs.getString("Nombre"));
                us.setCorreoUsuario(rs.getString("Correo"));
                us.setContraUsuario(rs.getString("Pass"));
                us.setRolUsuario(rs.getString("rol"));
                Listaus.add(us);
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Listaus;
    }
    
    public boolean EliminarUsuarios(int id){
        String sql = "DELETE FROM usuarios WHERE id = ? ";
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
    //idUsuario  nombreUsuario  correoUsuario  contraUsuario  rolUsuario
    
    public boolean ModificarUsuarios(Usuario us){
        String sql = "UPDATE usuarios SET Nombre=?, Correo=?, Pass=?, rol=? WHERE id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, us.getNombreUsuario());
            ps.setString(2, us.getCorreoUsuario());
            ps.setString(3, us.getContraUsuario());
            ps.setString(4, us.getRolUsuario());
            ps.setInt(5, us.getIdUsuario());
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
