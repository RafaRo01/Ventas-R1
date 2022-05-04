package Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.*;
import javax.swing.JComboBox;
import javax.swing.JTextField;
/**
 *
 * @author Rafael Rosas
 */
public class ProductosDao {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarProductos(Productos pro){
        String sql = "INSERT INTO productos (Codigo, Nombre, Proveedor, Stock, Precio) VALUES (?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigoProductos());
            ps.setString(2, pro.getNombreProductos());
            ps.setString(3, pro.getProveedorProductos());
            ps.setInt(4, pro.getStockProductos());
            ps.setDouble(5, pro.getPrecioProductos());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    /*
    productos  codigo
    ac = new TextAutoCompleter(txtCodigoVenta);
    Clientes
        bb= new TextAutoCompleter(txtRucVenta);
    */
     
    
    public void ConsultarProveedor(JComboBox pro){
        String sql = "Select Nombre from proveedor";
        try{
            con = cn.getConnection();
            ps=con.prepareStatement(sql);
            rs =ps.executeQuery();
            while(rs.next()){
                pro.addItem(rs.getString("Nombre"));
            }
        }catch(Exception e){
            System.out.println(e.toString());
            
        }
    }
    
    public List ListarProductos(){
       List<Productos> Listapro = new ArrayList();
  String sql="select * from productos";      
// String sql = "SELECT pr.id AS id_proveedor, pr.nombre AS nombre_proveedor, p.* FROM proveedor pr INNER JOIN productos p ON pr.id = p.proveedor ORDER BY p.id DESC";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()) {               
               Productos pro = new Productos();
               pro.setIdProductos(rs.getInt("Id"));
               pro.setCodigoProductos(rs.getString("Codigo"));
               pro.setNombreProductos(rs.getString("Nombre"));
               //pro.setProveedorProductos(rs.getInt("id_proveedor"));
               pro.setProveedorProductos(rs.getString("Proveedor"));
               pro.setStockProductos(rs.getInt("Stock"));
               pro.setPrecioProductos(rs.getDouble("Precio"));
               Listapro.add(pro);
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return Listapro;
   }
    
    public boolean EliminarProductos(int id){
       String sql = "DELETE FROM productos WHERE Id = ?";
       try {
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
           } catch (SQLException ex) {
               System.out.println(ex.toString());
           }
       }
   }
    
    public boolean ModificarProductos(Productos pro){
       String sql = "UPDATE productos SET Codigo=?, Nombre=?, Proveedor=?, Stock=?, Precio=? WHERE Id=?";
       try {
           ps = con.prepareStatement(sql);
           ps.setString(1, pro.getCodigoProductos());
           ps.setString(2, pro.getNombreProductos());
           ps.setString(3, pro.getProveedorProductos());
           ps.setInt(4, pro.getStockProductos());
           ps.setDouble(5, pro.getPrecioProductos());
           ps.setInt(6, pro.getIdProductos());
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
    
    public Productos BuscarPro(String cod){
        Productos producto = new Productos();
        String sql = "SELECT * FROM productos WHERE Codigo = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            if (rs.next()) {
             //   producto.setIdProductos(rs.getInt("Id"));
                producto.setNombreProductos(rs.getString("Nombre"));
                producto.setPrecioProductos(rs.getDouble("Precio"));
                producto.setStockProductos(rs.getInt("Stock"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return producto;
    }
    public Productos BuscarId(int id){
        Productos pro = new Productos();
        String sql = "SELECT pr.id AS id_proveedor, pr.nombre AS nombre_proveedor, p.* FROM proveedor pr INNER JOIN productos p ON p.proveedor = pr.id WHERE p.id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                pro.setIdProductos(rs.getInt("Id"));
                pro.setCodigoProductos(rs.getString("Codigo"));
                pro.setNombreProductos(rs.getString("Nombre"));
               // pro.setProveedorProductos(rs.getInt("proveedor"));
                pro.setProveedorProductos(rs.getString("Proveedor"));
                pro.setStockProductos(rs.getInt("Stock"));
                pro.setPrecioProductos(rs.getDouble("Precio"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return pro;
    }
    public Proveedor BuscarProveedor(String nombre){
        Proveedor pr = new Proveedor();
        String sql = "SELECT * FROM proveedor WHERE Nombre = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            if (rs.next()) {
               
               // pr.setIdProductos(rs.getInt("Id"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return pr;
    }
    public Config BuscarDatos(){
        Config conf = new Config();
        String sql = "SELECT * FROM configuracion";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                //conf.setIdConfig(0);
               conf.setIdConfig(rs.getInt("Id"));
                conf.setRucConfig(rs.getInt("Ruc"));
                conf.setNombreConfig(rs.getString("Nombre"));
                conf.setTelefonoConfig(rs.getInt("Telefono"));
                conf.setDireccionConfig(rs.getString("Direccion"));
                conf.setRazonConfig(rs.getString("Razon"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return conf;
    } 
    
    public boolean ModificarDatos(Config conf){
       String sql = "UPDATE configuracion SET Ruc=?, Nombre=?, Telefono=?, Direccion=?, Razon=? WHERE Id=?";
       try {
           ps = con.prepareStatement(sql);
           ps.setInt(1, conf.getRucConfig());
           ps.setString(2, conf.getNombreConfig());
           ps.setInt(3, conf.getTelefonoConfig());
           ps.setString(4, conf.getDireccionConfig());
           ps.setString(5, conf.getRazonConfig());
           ps.setInt(6, conf.getIdConfig());
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
