/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Rafael Rosas
 */
public class registroDao {
     Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn= new Conexion();
    
    public registro reg(String correo,String pass){
        registro l = new registro();
        String sql= "SELECT * FROM usuarios WHERE correo = ? AND pass = ?";
        try{
            con= cn.getConnection();
            ps= con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, pass);
           rs= ps.executeQuery();
            if(rs.next()){
                l.setIdRegistro(rs.getInt("id"));
                l.setNombreRegistro(rs.getString("nombre"));
                l.setCorreoRegistro(rs.getString("correo"));
                l.setPassRegistro(rs.getString("pass"));
            }
            
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return l;
    }
}
