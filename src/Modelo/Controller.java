
package Modelo;

import Vista.Sistema;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class Controller implements MouseListener{

    private Sistema Vista;

    public Controller(Sistema Vista) {
        this.Vista = Vista;
      //  this.Vista.btnUsuarios.addMouseListener(this);
        
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
      
    }

    @Override
    public void mousePressed(MouseEvent e) {
    
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    
    }

    @Override
    public void mouseEntered(MouseEvent e) {
   /*  if(e.getSource() == ){
         vie
     }*/
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
 
    }
    
}
