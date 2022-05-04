
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Rafael Rosas
 */
public class Conexion {
    Connection con;
    public Connection getConnection(){
        try{
            String myDB= "jdbc:mysql://localhost:3307/ventas?serverTimezone=UTC";
            con= DriverManager.getConnection(myDB,"root","12345");
            return con;
        }catch(SQLException e){
            System.out.println(e.toString());
            
        }
        return null;
    }
    
}
