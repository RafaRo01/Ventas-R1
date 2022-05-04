package Modelo;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Rafael Rosas
 */
public class ClienteDao {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn= new Conexion();
    
    public boolean RegistrarCliente(Cliente cl){
        login l = new login();
        String sql= "INSERT INTO clientes (Dni, Nombre, Telefono, Direccion, Razon) VALUES (?,?,?,?,?)";
        try{
            con= cn.getConnection();
            ps= con.prepareStatement(sql);
            ps.setInt(1,cl.getDniCliente() );
            ps.setString(2, cl.getNombreCliente());
            ps.setInt(3, cl.getTelefonoCliente());
            ps.setString(4, cl.getDireccionCliente());
            ps.setString(5, cl.getRazonCliente());
            ps.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showInputDialog(null, e.toString());
            return false;
            
                   }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
                 }
        }
        
    }
    public List ListarCliente(){
        List<Cliente> ListaCL = new ArrayList();
        String sql = "SELECT * FROM clientes";
        try{
            con = cn.getConnection();
            ps= con.prepareStatement(sql);
          rs=  ps.executeQuery();
          while(rs.next()){
              Cliente cl= new Cliente();
              cl.setIdCliente(rs.getInt("id"));
              cl.setDniCliente(rs.getInt("Dni"));
              cl.setNombreCliente(rs.getString("Nombre"));
              cl.setTelefonoCliente(rs.getInt("Telefono"));
              cl.setDireccionCliente(rs.getString("Direccion"));
              cl.setRazonCliente(rs.getString("Razon"));
              ListaCL.add(cl);
          }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return ListaCL;
    }
    
    public boolean eliminarCliente(int id){
        String sql= "DELETE FROM clientes WHERE id = ?";
        try{
           ps= con.prepareStatement(sql);
           ps.setInt(1, id);
           ps.execute();
           return true;
        }catch(Exception e){
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
            
        }
    }
    public boolean ModificarCliente(Cliente cl){
       String sql = "UPDATE clientes SET Dni=?, Nombre=?, Telefono=?, Direccion=?, Razon=? WHERE id=?";
       try {
           ps = con.prepareStatement(sql);   
           ps.setInt(1, cl.getDniCliente());
           ps.setString(2, cl.getNombreCliente());
           ps.setInt(3, cl.getTelefonoCliente());
           ps.setString(4, cl.getDireccionCliente());
           ps.setString(5, cl.getRazonCliente());
           ps.setInt(6, cl.getIdCliente());
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
   
   public Cliente Buscarcliente(int dni){
       Cliente cl = new Cliente();
       String sql = "SELECT * FROM clientes WHERE Dni = ?";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setInt(1, dni);
           rs = ps.executeQuery();
           if (rs.next()) {
               //cl.setIdCliente(rs.getInt("id"));
               cl.setNombreCliente(rs.getString("Nombre"));
               cl.setTelefonoCliente(rs.getInt("Telefono"));
               cl.setDireccionCliente(rs.getString("Direccion"));
               cl.setRazonCliente(rs.getString("Razon"));
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return cl;
   }
}

