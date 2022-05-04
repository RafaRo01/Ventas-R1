/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 

 */
package Vista;

import Modelo.Cliente;
import Modelo.ClienteDao;
import Modelo.Detalle;
import Modelo.Eventos;
import Modelo.Productos;
import Modelo.ProductosDao;
import Modelo.Proveedor;
import Modelo.ProveedorDao;
import Modelo.Venta;
import Modelo.VentaDao;
import Modelo.login;
import Modelo.Config;
import Modelo.LoginDao;
import Modelo.Usuario;
import Modelo.UsuarioDao;
import Reportes.Excel;
import Reportes.Grafico;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Rafael Rosas
 */
public class Sistema extends javax.swing.JFrame {

    /**
     * Creates new form Sistema
     */
    Date fechaVenta= new Date();
    String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(fechaVenta);
    
    Cliente cl = new Cliente();
    ClienteDao clien= new ClienteDao();
    
    Proveedor pr= new Proveedor();
    ProveedorDao prdao= new ProveedorDao();
    
    Usuario us= new Usuario();
    UsuarioDao usdao = new UsuarioDao();
    
    Productos pro = new Productos();
    ProductosDao proDao = new ProductosDao();
    
    Venta v = new Venta();
    VentaDao Vdao = new VentaDao();
    
    Detalle Dv = new Detalle();
    Config conf = new Config();
    
    Eventos event = new Eventos();
    
    login lg = new login();
    LoginDao login = new LoginDao();
    
   TextAutoCompleter ac;
   TextAutoCompleter bb;
    
    DefaultTableModel modelo= new DefaultTableModel();
    DefaultTableModel tmp = new DefaultTableModel();
    int item;
    double Totalpagar = 0.00;
    
    public Sistema() {
        //ac = new TextAutoCompleter(txtCodigoVenta);
        //bb= new TextAutoCompleter(txtRucVenta);
        initComponents();
       
        //ListarConfig();
        //int row = 0;
        
        
        
        
       // ac = TextAutoCompleter(txtCodigoVenta);
      //  AutoCompleteDecorator.decorate(txtCodigoVenta);
        
                
    }
    public Sistema(login priv){
       initComponents();
      setLocationRelativeTo(null);		
     
       
        
        txtIdVenta.setVisible(false);
        txtIdCliente.setVisible(false);
        txtIdproducto.setVisible(false);
        txtIdProveedor.setVisible(false);
        AutoCompleteDecorator.decorate(cbxProveedorPro);
        proDao.ConsultarProveedor(cbxProveedorPro);
        txtIdConfig.setVisible(false);
        ListarConfig();
        if(priv.getRol().equals("Asistente")){
           
            btnUsuarios.setEnabled(false);
            
            btnUsuarios.setVisible(false);
            
            btnGuardarCliente.setVisible(false);
            laGuardarcliente.setVisible(false);
            btnEditarCliente.setVisible(false);
            laActualizarcliente.setVisible(false);
            btnEliminarCliente.setVisible(false);
            laEliminarcliente.setVisible(false);
            
            btnGuardarProveedor.setVisible(false);
            laGuardarproveedor.setVisible(false);
            btnEliminarProveedor.setVisible(false);
            laEliminarproveedor.setVisible(false);
            btnEditarProveedor.setVisible(false);
            laActualizarproveedor.setVisible(false);
            
            btnActualizarConfig.setVisible(false);
            // prueba2@gmail.com
            
           
            btnGuardarPro.setVisible(false);
            laGuardarproductos.setVisible(false);
            btnEliminarPro.setVisible(false);
            laEliminarproductos.setVisible(false);
            btnEditarPro.setVisible(false);
            laActualizarproductos.setVisible(false);
            
            
            btnGuardarUsuario.setVisible(false);
            laGuardarUsuarios.setVisible(false);
            btnActualizarUsuario.setVisible(false);
            laActualizarUsuarios.setVisible(false);
            btnEliminarUsuario.setVisible(false);
            laEliminarUsuarios.setVisible(false);
            btnNuevoUsuario.setVisible(false);
            laVaciarUsuarios.setVisible(false);
            TableUsuario.setVisible(false);
            paUsu.setVisible(false);
            
            btnGuardarCliente.setEnabled(false);
            btnEditarCliente.setEnabled(false);
            btnEliminarCliente.setEnabled(false);
            btnGuardarProveedor.setEnabled(false);
            btnEliminarProveedor.setEnabled(false);
            btnEditarProveedor.setEnabled(false);
            btnActualizarConfig.setEnabled(false);
            btnGuardarPro.setEnabled(false);
            btnEliminarPro.setEnabled(false);
            btnEditarPro.setEnabled(false);
            btnGuardarUsuario.setEnabled(false);
            btnActualizarUsuario.setEnabled(false);
            btnEliminarUsuario.setEnabled(false);
            LabelVendedor.setText(priv.getNombre());
        }else{
            LabelVendedor.setText(priv.getNombre());
        }
    }
   
    public void ListaCliente(){
        List<Cliente> ListarCL = clien.ListarCliente();
        modelo = (DefaultTableModel)  tableCliente.getModel();
        Object[] ob= new Object[7];
        for(int i=0; i<ListarCL.size();i++){
            ob[0]= ListarCL.get(i).getIdCliente();
            ob[1]= ListarCL.get(i).getDniCliente();
            ob[2]= ListarCL.get(i).getNombreCliente();
            ob[3]= ListarCL.get( i).getTelefonoCliente();
            ob[4]= ListarCL.get(i).getDireccionCliente();
            ob[5]= ListarCL.get(i).getRazonCliente();
            modelo.addRow(ob);
                    }
        tableCliente.setModel(modelo);
            }
        public void ListarProveedor() {
        List<Proveedor> ListarPr = prdao.ListarProveedor();
        modelo = (DefaultTableModel) TableProveedor.getModel();
        Object[] ob = new Object[7];
        for (int i = 0; i < ListarPr.size(); i++) {
            ob[0] = ListarPr.get(i).getIdProveedor();
            ob[1] = ListarPr.get(i).getRucProveedor();
            ob[2] = ListarPr.get(i).getNombreProveedor();
            ob[3] = ListarPr.get(i).getTelefonoProveedor();
            ob[4] = ListarPr.get(i).getDireccionProveedor();
            ob[5] = ListarPr.get(i).getRazonProveedor();
            modelo.addRow(ob);
        }
       TableProveedor.setModel(modelo);
    }
        public void ListarUsuario() {
        List<Usuario> ListarUs = usdao.ListarUsuario();
        modelo = (DefaultTableModel) TableUsuario.getModel();
        Object[] ob = new Object[7];
        for (int i = 0; i < ListarUs.size(); i++) {
            ob[0] = ListarUs.get(i).getIdUsuario();
            ob[1] = ListarUs.get(i).getNombreUsuario();
            ob[2] = ListarUs.get(i).getCorreoUsuario();
            ob[3] = ListarUs.get(i).getContraUsuario();
            ob[4] = ListarUs.get(i).getRolUsuario();
            
            modelo.addRow(ob);
        }
       TableUsuario.setModel(modelo);
    }
        
   /* public void ListarUsuarios() {
        List<login> Listar = login.ListarUsuarios();
        modelo = (DefaultTableModel) TableUsuarios.getModel();
        Object[] ob = new Object[4];
        for (int i = 0; i < Listar.size(); i++) {
            ob[0] = Listar.get(i).getId();
            ob[1] = Listar.get(i).getNombre();
            ob[2] = Listar.get(i).getCorreo();
            ob[3] = Listar.get(i).getRol();
            modelo.addRow(ob);
        }
        TableUsuarios.setModel(modelo);

    } */
    public void ListarProductos() {
        List<Productos> ListarPro = proDao.ListarProductos();
        modelo = (DefaultTableModel) TableProducto.getModel();
        Object[] ob = new Object[7];
        for (int i = 0; i < ListarPro.size(); i++) {
            ob[0] = ListarPro.get(i).getIdProductos();
            ob[1] = ListarPro.get(i).getCodigoProductos();
            ob[2] = ListarPro.get(i).getNombreProductos();
            ob[3] = ListarPro.get(i).getProveedorProductos();
            ob[4] = ListarPro.get(i).getStockProductos();
            ob[5] = ListarPro.get(i).getPrecioProductos();
            modelo.addRow(ob);
        }
        TableProducto.setModel(modelo);

    }
    private void llenarProveedor(){
        List<Proveedor> lista = prdao.ListarProveedor();
        for (int i = 0; i < lista.size(); i++) {
            int id = lista.get(i).getIdProveedor();
            String nombre = lista.get(i).getNombreProveedor();
            //cbxProveedorPro.addItem(new Combo(id, nombre));
        }
    }
    
    private void llenarUsuario(){
        List<Usuario> lista = usdao.ListarUsuario();
        for (int i = 0; i < lista.size(); i++) {
            int id = lista.get(i).getIdUsuario();
            String nombre = lista.get(i).getNombreUsuario();
            //cbxProveedorPro.addItem(new Combo(id, nombre));
        }
    }

        
    public void ListarConfig() {
        conf = proDao.BuscarDatos();
        txtIdConfig.setText("" + conf.getIdConfig());
        txtRucConfig.setText("" + conf.getRucConfig());
        txtNombreConfig.setText("" + conf.getNombreConfig());
        txtTelefonoConfig.setText("" + conf.getTelefonoConfig());
        txtDireccionConfig.setText("" + conf.getDireccionConfig());
        txtRazonConfig.setText(""+conf.getRazonConfig());
    }

    public void ListarVentas() {
        List<Venta> ListarVenta = Vdao.Listarventas();
        modelo = (DefaultTableModel) tablaVentas.getModel();
        Object[] ob = new Object[5];
        for (int i = 0; i < ListarVenta.size(); i++) {
            ob[0] = ListarVenta.get(i).getIdVenta();
            ob[1] = ListarVenta.get(i).getClienteVenta();
            ob[2] = ListarVenta.get(i).getVendedorVenta();
            ob[3] = ListarVenta.get(i).getTotalVenta();
            modelo.addRow(ob);
        }
        tablaVentas.setModel(modelo);

    }
    
    public void LimpiarTable(){
        for(int i=0;i<modelo.getRowCount();i++){
            modelo.removeRow(i);
            i= i-1;
        }
    }
    private void LimpiarUsuario(){
        txtIdUsuario.setText("");
        txtNombreUsuario.setText("");
        txtCorreoUsuario.setText("");
        txtContraUsuario.setText("");
        txtRolUsuario.setText("");
    }
    private void LimpiarVentas(){
        txtIdVentas.setText("");
    }
    
    private void LimpiarProveedor() {
        txtIdProveedor.setText("");
        txtRucProveedor.setText("");
        txtNombreProveedor.setText("");
        txtTelefonoProveedor.setText("");
        txtDireccionProveedor.setText("");
        txtRazonProveedor.setText("");
    }
    
    private void LimpiarProductos() {
        txtIdproducto.setText("");
        txtCodigoPro.setText("");
        txtDesPro.setText("");
        cbxProveedorPro.setSelectedItem(null);
        txtCantPro.setText("");
        txtPrecioPro.setText("");
      }
     private void TotalPagar() {
        Totalpagar = 0.00;
        int numFila = TableVenta.getRowCount();
        for (int i = 0; i < numFila; i++) {
            double cal = Double.parseDouble(String.valueOf(TableVenta.getModel().getValueAt(i, 4)));
            Totalpagar = Totalpagar + cal;
        }
        LabelTotal.setText(String.format("%.2f", Totalpagar));
    }

    private void LimpiarVenta() {
        txtCodigoVenta.setText("");
        txtDescripcionVenta.setText("");
        txtCantidadVenta.setText("");
        txtStockVenta.setText("");
        txtPrecioVenta.setText("");
        txtIdVenta.setText("");
    }
    
private void RegistrarVenta() {
        String cliente =txtNombreClienteventa.getText();
        String vendedor = LabelVendedor.getText();
        double monto = Totalpagar;
        v.setClienteVenta(cliente);
        v.setVendedorVenta(vendedor);
        v.setTotalVenta(monto);
       
        Vdao.RegistrarVenta(v);
    }

    private void RegistrarDetalle() {
        int id = Vdao.IdVenta();
        for (int i = 0; i < TableVenta.getRowCount(); i++) {
            
            //int id_pro = Integer.parseInt(TableVenta.getValueAt(i, 0).toString());
            String cod= TableVenta.getValueAt(i,0).toString();
            int cant = Integer.parseInt(TableVenta.getValueAt(i, 2).toString());
            double precio = Double.parseDouble(TableVenta.getValueAt(i, 3).toString());
            Dv.setCod_proDetalle(cod);
            Dv.setCantidadDetalle(cant);
            Dv.setPrecioDetalle(precio);
            Dv.setIdDetalle(id);
            Vdao.RegistrarDetalle(Dv);

        }
        //int cliente = Integer.parseInt(txtIdCV.getText());
        //Vdao.pdfV(id, cliente, Totalpagar, LabelVendedor.getText());
    } 
     private void ActualizarStock() {
        for (int i = 0; i < TableVenta.getRowCount(); i++) {
           // int id = Integer.parseInt(TableVenta.getValueAt(i, 0).toString());
            String cod = TableVenta.getValueAt(i,0).toString();
            int cant = Integer.parseInt(TableVenta.getValueAt(i, 2).toString());
            pro = proDao.BuscarPro(cod);
            int StockActual = pro.getStockProductos() - cant;
            Vdao.ActualizarStock(StockActual, cod);

        }
    }

    private void LimpiarTableVenta() {
        tmp = (DefaultTableModel) TableVenta.getModel();
        int fila = TableVenta.getRowCount();
        for (int i = 0; i < fila; i++) {
            tmp.removeRow(0);
        }
    }
     private void LimpiarClienteventa() {
        txtRucVenta.setText("");
        txtNombreClienteventa.setText("");
        txtIdVenta.setText("");
    }
    /*
    private void nuevoUsuario(){
        txtNombre.setText("");
        txtCorreo.setText("");
        txtPass.setText("");
    }
    private void llenarProveedor(){
        List<Proveedor> lista = PrDao.ListarProveedor();
        for (int i = 0; i < lista.size(); i++) {
            int id = lista.get(i).getId();
            String nombre = lista.get(i).getNombre();
            cbxProveedorPro.addItem(new Combo(id, nombre));
        }
    }*/

private void pdf(){
    try{
        int id = Vdao.IdVenta();
      FileOutputStream archivo;
      File file = new File("src/pdf/venta"+id+".pdf");
      archivo = new FileOutputStream(file);
      Document doc = new Document();
      PdfWriter.getInstance(doc, archivo);
      doc.open();
      Image img= Image.getInstance("src/img/rafa.png");
      
      //doc.close();
      //archivo.close();
      Paragraph fecha = new Paragraph();
      Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
      fecha.add(Chunk.NEWLINE);
      Date date= new Date();
      fecha.add("Factura: "+id+"\n"+"Fecha: "+new SimpleDateFormat("dd/MM/yyyy").format(date)+"\n\n");
        System.out.println("Fecha: "+fecha);
      PdfPTable encabezado = new PdfPTable(4);
      encabezado.setWidthPercentage(100);
      encabezado.getDefaultCell().setBorder(0);
      //tamaño de cada celda
      float[] ColumnaEncabezado = new float[]{20f, 30f, 70f, 40f};
      encabezado.setWidths(ColumnaEncabezado);
      encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
      
      encabezado.addCell(img);
      
      String ruc = txtRucConfig.getText();
      String nom = txtNombreConfig.getText();
      String tel = txtTelefonoConfig.getText();
      String dir= txtDireccionConfig.getText();
      String ra = txtRazonConfig.getText();
      
      encabezado.addCell("");
      encabezado.addCell("Ruc: "+ruc+"\nNombre: "+nom+"\nTelefono: "+tel+"\nDirección: "+dir+"\nRazón: "+ra);
        System.out.println(ruc+nom+tel+dir+ra);
      encabezado.addCell(fecha);
      doc.add(encabezado);
      
      
      Paragraph cli= new Paragraph();
      cli.add(Chunk.NEWLINE);
      cli.add("Datos de los clientes"+"\n\n");
      doc.add(cli);
      
      PdfPTable tablecli = new PdfPTable(4);
      tablecli.setWidthPercentage(100);
     tablecli.getDefaultCell().setBorder(0);
      //tamaño de cada celda
      float[] ColumnaCli = new float[]{20f, 50f, 30f, 40f};
      tablecli.setWidths(ColumnaCli);
      tablecli.setHorizontalAlignment(Element.ALIGN_LEFT);
      PdfPCell cl1= new PdfPCell(new Phrase("Dni/Ruc",negrita));
      PdfPCell cl2= new PdfPCell(new Phrase("Nombre",negrita));
      PdfPCell cl3= new PdfPCell(new Phrase("Telefono",negrita));
      PdfPCell cl4= new PdfPCell(new Phrase("Dirección",negrita));
      cl1.setBorder(0);
      cl2.setBorder(0);
      cl3.setBorder(0);
      cl4.setBorder(0);
      tablecli.addCell(cl1);
      tablecli.addCell(cl2);
      tablecli.addCell(cl3);
      tablecli.addCell(cl4);
      tablecli.addCell(txtRucVenta.getText());
      tablecli.addCell(txtNombreClienteventa.getText());
      tablecli.addCell(txtTelefonoCV.getText());
      tablecli.addCell(txtDireccionCV.getText());
      
      doc.add(tablecli);

      //productos 
           
      PdfPTable tableprod = new PdfPTable(4);
      tableprod.setWidthPercentage(100);
     tableprod.getDefaultCell().setBorder(0);
      //tamaño de cada celda
      float[] ColumnaProd = new float[]{10f, 50f, 15f, 20f};
      tableprod.setWidths(ColumnaProd);
      tableprod.setHorizontalAlignment(Element.ALIGN_LEFT);
      PdfPCell prod1= new PdfPCell(new Phrase("Cantidad: ",negrita));
      PdfPCell prod2= new PdfPCell(new Phrase("Descripción: ",negrita));
      PdfPCell prod3= new PdfPCell(new Phrase("Precio Unitario: ",negrita));
      PdfPCell prod4= new PdfPCell(new Phrase("Precio Total: ",negrita));
      prod1.setBorder(0);
      prod2.setBorder(0);
      prod3.setBorder(0);
      prod4.setBorder(0);
      
      prod1.setBackgroundColor(BaseColor.DARK_GRAY);
      prod2.setBackgroundColor(BaseColor.DARK_GRAY);
      prod3.setBackgroundColor(BaseColor.DARK_GRAY);
      prod4.setBackgroundColor(BaseColor.DARK_GRAY);
      tableprod.addCell(prod1);
      tableprod.addCell(prod2);
      tableprod.addCell(prod3);
      tableprod.addCell(prod4);
      
      for(int i=0;i<TableVenta.getRowCount();i++){
        String producto = TableVenta.getValueAt(i, 1).toString();
        String cantidad = TableVenta.getValueAt(i, 2).toString();
        String precio = TableVenta.getValueAt(i, 3).toString();
        String total = TableVenta.getValueAt(i, 4).toString();
        tableprod.addCell(cantidad);
        tableprod.addCell(producto);
        tableprod.addCell(precio);
        tableprod.addCell(total);      
      }
      
      doc.add(tableprod);
      
      Paragraph info = new Paragraph();
      info.add(Chunk.NEWLINE);
      info.add("Total a pagar: "+Totalpagar);
      info.setAlignment(Element.ALIGN_RIGHT);
      doc.add(info);
      
      Paragraph firma = new Paragraph();
      firma.add(Chunk.NEWLINE);
      firma.add("Cancelación y Firma:\n\n");
      firma.add("------------------------");
      firma.setAlignment(Element.ALIGN_CENTER);
      doc.add(firma);
      
      Paragraph msj = new Paragraph();
      msj.add(Chunk.NEWLINE);
      msj.add("Gracias por su compra");
      firma.setAlignment(Element.ALIGN_CENTER);
      doc.add(msj);
      
      doc.close();
      archivo.close();
      Desktop.getDesktop().open(file);
    }catch(DocumentException | IOException e){
        System.out.println(e.toString());
        
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnNuevaVentaSistema = new javax.swing.JButton();
        btnVentaSistema = new javax.swing.JButton();
        btnProveedorSistema = new javax.swing.JButton();
        btnProductosSistema = new javax.swing.JButton();
        btnVentasSistema = new javax.swing.JButton();
        btnConfigSistema = new javax.swing.JButton();
        btnExitSistema = new javax.swing.JButton();
        btnMaxSistema = new javax.swing.JButton();
        btnMinSistema = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        LabelVendedor = new javax.swing.JLabel();
        btnUsuarios = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        NuevaVenta = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnEliminarVenta = new javax.swing.JButton();
        txtCodigoVenta = new javax.swing.JTextField();
        txtDescripcionVenta = new javax.swing.JTextField();
        txtCantidadVenta = new javax.swing.JTextField();
        txtPrecioVenta = new javax.swing.JTextField();
        txtStockVenta = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableVenta = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtRucVenta = new javax.swing.JTextField();
        txtNombreClienteventa = new javax.swing.JTextField();
        btnGenerarVenta = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        LabelTotal = new javax.swing.JLabel();
        txtTelefonoCV = new javax.swing.JTextField();
        txtDireccionCV = new javax.swing.JTextField();
        txtRazonCV = new javax.swing.JTextField();
        txtIdProd = new javax.swing.JTextField();
        txtIdVenta = new javax.swing.JTextField();
        btnGraficar = new javax.swing.JButton();
        Midate = new com.toedter.calendar.JDateChooser();
        jLabel45 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtDniCliente = new javax.swing.JTextField();
        txtNombreCliente = new javax.swing.JTextField();
        txtTelefonoCliente = new javax.swing.JTextField();
        txtDireccionCliente = new javax.swing.JTextField();
        txtRazonCliente = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCliente = new javax.swing.JTable();
        btnGuardarCliente = new javax.swing.JButton();
        btnEditarCliente = new javax.swing.JButton();
        btnEliminarCliente = new javax.swing.JButton();
        btnNuevoCliente = new javax.swing.JButton();
        txtIdCliente = new javax.swing.JTextField();
        laGuardarcliente = new javax.swing.JLabel();
        laEliminarcliente = new javax.swing.JLabel();
        laActualizarcliente = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        Proveedor = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtRucProveedor = new javax.swing.JTextField();
        txtNombreProveedor = new javax.swing.JTextField();
        txtTelefonoProveedor = new javax.swing.JTextField();
        txtDireccionProveedor = new javax.swing.JTextField();
        txtRazonProveedor = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableProveedor = new javax.swing.JTable();
        btnGuardarProveedor = new javax.swing.JButton();
        btnEditarProveedor = new javax.swing.JButton();
        btnEliminarProveedor = new javax.swing.JButton();
        btnNuevoProveedor = new javax.swing.JButton();
        txtIdProveedor = new javax.swing.JTextField();
        laGuardarproveedor = new javax.swing.JLabel();
        laEliminarproveedor = new javax.swing.JLabel();
        laActualizarproveedor = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaVentas = new javax.swing.JTable();
        btnPdfVentas = new javax.swing.JButton();
        txtIdVentas = new javax.swing.JTextField();
        btnEliminarPdfVentas = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtNombreConfig = new javax.swing.JTextField();
        txtTelefonoConfig = new javax.swing.JTextField();
        txtRucConfig = new javax.swing.JTextField();
        txtRazonConfig = new javax.swing.JTextField();
        txtDireccionConfig = new javax.swing.JTextField();
        btnActualizarConfig = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        txtIdConfig = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtDesPro = new javax.swing.JTextField();
        txtCodigoPro = new javax.swing.JTextField();
        txtCantPro = new javax.swing.JTextField();
        txtPrecioPro = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableProducto = new javax.swing.JTable();
        btnGuardarPro = new javax.swing.JButton();
        btnEditarPro = new javax.swing.JButton();
        btnEliminarPro = new javax.swing.JButton();
        btnNuevoPro = new javax.swing.JButton();
        cbxProveedorPro = new javax.swing.JComboBox<>();
        btnExcelPro = new javax.swing.JButton();
        txtIdproducto = new javax.swing.JTextField();
        laGuardarproductos = new javax.swing.JLabel();
        laEliminarproductos = new javax.swing.JLabel();
        laActualizarproductos = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        paUsu = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        TableUsuario = new javax.swing.JTable();
        jLabel46 = new javax.swing.JLabel();
        txtNombreUsuario = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        txtCorreoUsuario = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        txtContraUsuario = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        txtRolUsuario = new javax.swing.JTextField();
        btnGuardarUsuario = new javax.swing.JButton();
        btnActualizarUsuario = new javax.swing.JButton();
        btnEliminarUsuario = new javax.swing.JButton();
        btnNuevoUsuario = new javax.swing.JButton();
        laGuardarUsuarios = new javax.swing.JLabel();
        laActualizarUsuarios = new javax.swing.JLabel();
        laVaciarUsuarios = new javax.swing.JLabel();
        laEliminarUsuarios = new javax.swing.JLabel();
        txtIdUsuario = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        btnNuevaVentaSistema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Nventa.png"))); // NOI18N
        btnNuevaVentaSistema.setText("Nueva Venta");
        btnNuevaVentaSistema.setBorderPainted(false);
        btnNuevaVentaSistema.setContentAreaFilled(false);
        btnNuevaVentaSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaVentaSistemaActionPerformed(evt);
            }
        });

        btnVentaSistema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Clientes.png"))); // NOI18N
        btnVentaSistema.setText("Clientes");
        btnVentaSistema.setBorderPainted(false);
        btnVentaSistema.setContentAreaFilled(false);
        btnVentaSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentaSistemaActionPerformed(evt);
            }
        });

        btnProveedorSistema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/proveedor.png"))); // NOI18N
        btnProveedorSistema.setText("Proveedor");
        btnProveedorSistema.setBorderPainted(false);
        btnProveedorSistema.setContentAreaFilled(false);
        btnProveedorSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedorSistemaActionPerformed(evt);
            }
        });

        btnProductosSistema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/producto.png"))); // NOI18N
        btnProductosSistema.setText("Productos");
        btnProductosSistema.setBorderPainted(false);
        btnProductosSistema.setContentAreaFilled(false);
        btnProductosSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosSistemaActionPerformed(evt);
            }
        });

        btnVentasSistema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cash.png"))); // NOI18N
        btnVentasSistema.setText("PDF Ventas");
        btnVentasSistema.setBorderPainted(false);
        btnVentasSistema.setContentAreaFilled(false);
        btnVentasSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasSistemaActionPerformed(evt);
            }
        });

        btnConfigSistema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/confg.png"))); // NOI18N
        btnConfigSistema.setText("Config");
        btnConfigSistema.setBorderPainted(false);
        btnConfigSistema.setContentAreaFilled(false);
        btnConfigSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigSistemaActionPerformed(evt);
            }
        });

        btnExitSistema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cerrar.png"))); // NOI18N
        btnExitSistema.setBorderPainted(false);
        btnExitSistema.setContentAreaFilled(false);
        btnExitSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitSistemaActionPerformed(evt);
            }
        });

        btnMaxSistema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/rr.png"))); // NOI18N
        btnMaxSistema.setBorderPainted(false);
        btnMaxSistema.setContentAreaFilled(false);
        btnMaxSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaxSistemaActionPerformed(evt);
            }
        });

        btnMinSistema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/min.png"))); // NOI18N
        btnMinSistema.setBorderPainted(false);
        btnMinSistema.setContentAreaFilled(false);
        btnMinSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinSistemaActionPerformed(evt);
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Rafa.png"))); // NOI18N

        LabelVendedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        LabelVendedor.setForeground(new java.awt.Color(255, 0, 0));
        LabelVendedor.setText("Red");

        btnUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Clientes.png"))); // NOI18N
        btnUsuarios.setText("Usuarios");
        btnUsuarios.setBorderPainted(false);
        btnUsuarios.setContentAreaFilled(false);
        btnUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnNuevaVentaSistema, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
            .addComponent(btnVentaSistema, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnConfigSistema, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnVentasSistema, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnProductosSistema, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnProveedorSistema, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnExitSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMaxSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMinSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel11))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(LabelVendedor)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExitSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMaxSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMinSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LabelVendedor)
                .addGap(18, 18, 18)
                .addComponent(btnUsuarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNuevaVentaSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVentaSistema)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnProveedorSistema)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnProductosSistema)
                .addGap(18, 18, 18)
                .addComponent(btnVentasSistema)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConfigSistema)
                .addGap(24, 24, 24))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 590));

        NuevaVenta.setBackground(new java.awt.Color(255, 255, 255));
        NuevaVenta.setOpaque(false);

        jLabel3.setText("Codigo Prod:");

        jLabel4.setText("Descripcion");

        jLabel5.setText("Cantidad");

        jLabel6.setText("Precio");

        jLabel7.setText("Stock Disponible");

        btnEliminarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/x.png"))); // NOI18N
        btnEliminarVenta.setBorderPainted(false);
        btnEliminarVenta.setContentAreaFilled(false);
        btnEliminarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarVentaActionPerformed(evt);
            }
        });

        txtCodigoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoVentaActionPerformed(evt);
            }
        });
        txtCodigoVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoVentaKeyTyped(evt);
            }
        });

        txtDescripcionVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionVentaKeyTyped(evt);
            }
        });

        txtCantidadVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadVentaActionPerformed(evt);
            }
        });
        txtCantidadVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadVentaKeyTyped(evt);
            }
        });

        txtStockVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockVentaActionPerformed(evt);
            }
        });

        TableVenta.setBackground(new java.awt.Color(204, 153, 255));
        TableVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "CANTIDAD", "PRECIO U.", "TOTAL"
            }
        ));
        TableVenta.getTableHeader().setReorderingAllowed(false);
        TableVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableVentaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableVenta);
        if (TableVenta.getColumnModel().getColumnCount() > 0) {
            TableVenta.getColumnModel().getColumn(0).setPreferredWidth(30);
            TableVenta.getColumnModel().getColumn(1).setPreferredWidth(100);
            TableVenta.getColumnModel().getColumn(2).setPreferredWidth(30);
            TableVenta.getColumnModel().getColumn(3).setPreferredWidth(30);
            TableVenta.getColumnModel().getColumn(4).setPreferredWidth(40);
        }

        jLabel8.setText("DNI Cliente:");

        jLabel9.setText("NOMBRE:");

        txtRucVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRucVentaActionPerformed(evt);
            }
        });
        txtRucVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRucVentaKeyPressed(evt);
            }
        });

        txtNombreClienteventa.setEditable(false);

        btnGenerarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/print.png"))); // NOI18N
        btnGenerarVenta.setBorderPainted(false);
        btnGenerarVenta.setContentAreaFilled(false);
        btnGenerarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarVentaActionPerformed(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/dollars.png"))); // NOI18N
        jLabel10.setText("Total a Pagar:");

        LabelTotal.setText("---------");

        btnGraficar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/compras.png"))); // NOI18N
        btnGraficar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficarActionPerformed(evt);
            }
        });

        jLabel45.setText("Seleccionar:");

        javax.swing.GroupLayout NuevaVentaLayout = new javax.swing.GroupLayout(NuevaVenta);
        NuevaVenta.setLayout(NuevaVentaLayout);
        NuevaVentaLayout.setHorizontalGroup(
            NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NuevaVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(NuevaVentaLayout.createSequentialGroup()
                        .addGroup(NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtCodigoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(NuevaVentaLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(33, 33, 33)
                                .addGroup(NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(txtCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtDescripcionVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(NuevaVentaLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel7))
                            .addGroup(NuevaVentaLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtStockVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(txtIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(NuevaVentaLayout.createSequentialGroup()
                        .addGroup(NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(NuevaVentaLayout.createSequentialGroup()
                                .addGroup(NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtRazonCV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                                    .addComponent(txtTelefonoCV, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdProd, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDireccionCV, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel45)
                                    .addGroup(NuevaVentaLayout.createSequentialGroup()
                                        .addComponent(Midate, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(61, 61, 61)
                                        .addComponent(btnGraficar)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(NuevaVentaLayout.createSequentialGroup()
                                .addGroup(NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(txtRucVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(NuevaVentaLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel9))
                                    .addGroup(NuevaVentaLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(txtNombreClienteventa, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnGenerarVenta)))
                                .addGap(26, 26, 26)
                                .addComponent(jLabel10)
                                .addGap(32, 32, 32)))
                        .addComponent(LabelTotal)))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        NuevaVentaLayout.setVerticalGroup(
            NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NuevaVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(NuevaVentaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGraficar))
                    .addGroup(NuevaVentaLayout.createSequentialGroup()
                        .addGroup(NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(NuevaVentaLayout.createSequentialGroup()
                                .addGroup(NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7))
                                .addGap(7, 7, 7)
                                .addGroup(NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCodigoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDescripcionVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnEliminarVenta)
                                .addGroup(NuevaVentaLayout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtStockVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(NuevaVentaLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtRucVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtNombreClienteventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnGenerarVenta))))
                            .addGroup(NuevaVentaLayout.createSequentialGroup()
                                .addGap(18, 58, Short.MAX_VALUE)
                                .addGroup(NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(LabelTotal))
                                .addGap(33, 33, 33)))
                        .addGroup(NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(NuevaVentaLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTelefonoCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDireccionCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel45))
                        .addGroup(NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtRazonCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtIdProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(NuevaVentaLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(Midate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nueva Venta", NuevaVenta);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("DNI");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Nombre");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Telefono");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Direccion");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Razon Social");

        tableCliente.setBackground(new java.awt.Color(204, 153, 255));
        tableCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DNI", "Nombre", "Telefono", "Direccion", "Razon Social"
            }
        ));
        tableCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableClienteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableCliente);
        if (tableCliente.getColumnModel().getColumnCount() > 0) {
            tableCliente.getColumnModel().getColumn(0).setPreferredWidth(50);
            tableCliente.getColumnModel().getColumn(1).setPreferredWidth(80);
            tableCliente.getColumnModel().getColumn(2).setPreferredWidth(100);
            tableCliente.getColumnModel().getColumn(3).setPreferredWidth(70);
            tableCliente.getColumnModel().getColumn(4).setPreferredWidth(80);
            tableCliente.getColumnModel().getColumn(5).setPreferredWidth(80);
        }

        btnGuardarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/saveicon.png"))); // NOI18N
        btnGuardarCliente.setBorderPainted(false);
        btnGuardarCliente.setContentAreaFilled(false);
        btnGuardarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarClienteActionPerformed(evt);
            }
        });

        btnEditarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/actualizar.png"))); // NOI18N
        btnEditarCliente.setBorderPainted(false);
        btnEditarCliente.setContentAreaFilled(false);
        btnEditarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarClienteActionPerformed(evt);
            }
        });

        btnEliminarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/x.png"))); // NOI18N
        btnEliminarCliente.setBorderPainted(false);
        btnEliminarCliente.setContentAreaFilled(false);
        btnEliminarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClienteActionPerformed(evt);
            }
        });

        btnNuevoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/trash.png"))); // NOI18N
        btnNuevoCliente.setBorderPainted(false);
        btnNuevoCliente.setContentAreaFilled(false);
        btnNuevoCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoClienteActionPerformed(evt);
            }
        });

        laGuardarcliente.setText("Guardar");

        laEliminarcliente.setText("Eliminar");

        laActualizarcliente.setText("Actualizar");

        jLabel35.setText("Vaciar Campos");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtRazonCliente))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15))
                                .addGap(16, 16, 16)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTelefonoCliente)
                                    .addComponent(txtDireccionCliente)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel12))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txtDniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtIdCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                                    .addComponent(txtNombreCliente))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnGuardarCliente)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(laGuardarcliente)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnEliminarCliente)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(laEliminarcliente))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnEditarCliente)
                                    .addComponent(laActualizarcliente))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel35)
                                    .addComponent(btnNuevoCliente))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtDniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtDireccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtRazonCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardarCliente)
                    .addComponent(btnEliminarCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(laEliminarcliente)
                    .addComponent(laGuardarcliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditarCliente)
                    .addComponent(btnNuevoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(laActualizarcliente)
                    .addComponent(jLabel35))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Clientes", jPanel3);

        Proveedor.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("RUC");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("NOMBRE:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("TELEFONO");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("DIRECCION");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("RAZON SOCIAL:");

        txtTelefonoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoProveedorActionPerformed(evt);
            }
        });

        TableProveedor.setBackground(new java.awt.Color(230, 179, 219));
        TableProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "RUC", "NOMBRE", "TELEFONO", "DIRECION", "RAZON SOCIAL"
            }
        ));
        TableProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableProveedorMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TableProveedor);
        if (TableProveedor.getColumnModel().getColumnCount() > 0) {
            TableProveedor.getColumnModel().getColumn(0).setPreferredWidth(100);
            TableProveedor.getColumnModel().getColumn(1).setPreferredWidth(100);
            TableProveedor.getColumnModel().getColumn(2).setPreferredWidth(100);
            TableProveedor.getColumnModel().getColumn(3).setPreferredWidth(100);
            TableProveedor.getColumnModel().getColumn(4).setPreferredWidth(100);
            TableProveedor.getColumnModel().getColumn(5).setPreferredWidth(70);
        }

        btnGuardarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/saveicon.png"))); // NOI18N
        btnGuardarProveedor.setBorderPainted(false);
        btnGuardarProveedor.setContentAreaFilled(false);
        btnGuardarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProveedorActionPerformed(evt);
            }
        });

        btnEditarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/actualizar.png"))); // NOI18N
        btnEditarProveedor.setBorderPainted(false);
        btnEditarProveedor.setContentAreaFilled(false);
        btnEditarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProveedorActionPerformed(evt);
            }
        });

        btnEliminarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/x.png"))); // NOI18N
        btnEliminarProveedor.setBorderPainted(false);
        btnEliminarProveedor.setContentAreaFilled(false);
        btnEliminarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProveedorActionPerformed(evt);
            }
        });

        btnNuevoProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/trash.png"))); // NOI18N
        btnNuevoProveedor.setBorderPainted(false);
        btnNuevoProveedor.setContentAreaFilled(false);
        btnNuevoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProveedorActionPerformed(evt);
            }
        });

        laGuardarproveedor.setText("Guardar");

        laEliminarproveedor.setText("Eliminar");

        laActualizarproveedor.setText("Actualizar");

        jLabel39.setText("Vaciar Campos");

        javax.swing.GroupLayout ProveedorLayout = new javax.swing.GroupLayout(Proveedor);
        Proveedor.setLayout(ProveedorLayout);
        ProveedorLayout.setHorizontalGroup(
            ProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProveedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(ProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(ProveedorLayout.createSequentialGroup()
                            .addGroup(ProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel18)
                                .addComponent(jLabel17))
                            .addGroup(ProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(ProveedorLayout.createSequentialGroup()
                                    .addGap(37, 37, 37)
                                    .addComponent(txtNombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ProveedorLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtRucProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(ProveedorLayout.createSequentialGroup()
                            .addComponent(jLabel21)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtRazonProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ProveedorLayout.createSequentialGroup()
                        .addGroup(ProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(ProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDireccionProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                            .addComponent(txtTelefonoProveedor))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(ProveedorLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(ProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ProveedorLayout.createSequentialGroup()
                        .addComponent(btnGuardarProveedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarProveedor))
                    .addGroup(ProveedorLayout.createSequentialGroup()
                        .addGroup(ProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(laGuardarproveedor)
                            .addComponent(btnEditarProveedor)
                            .addComponent(laActualizarproveedor))
                        .addGroup(ProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ProveedorLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(laEliminarproveedor))
                            .addGroup(ProveedorLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(ProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel39)
                                    .addComponent(btnNuevoProveedor))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ProveedorLayout.setVerticalGroup(
            ProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProveedorLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(ProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ProveedorLayout.createSequentialGroup()
                        .addGroup(ProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtRucProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(ProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtNombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(ProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtTelefonoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(ProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtDireccionProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(ProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtRazonProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(ProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(ProveedorLayout.createSequentialGroup()
                        .addComponent(btnGuardarProveedor)
                        .addGap(6, 6, 6)
                        .addComponent(laGuardarproveedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditarProveedor))
                    .addGroup(ProveedorLayout.createSequentialGroup()
                        .addComponent(btnEliminarProveedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(laEliminarproveedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNuevoProveedor)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addComponent(laActualizarproveedor))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Proveedor", Proveedor);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        tablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CLIENTE", "VENDEDOR", "TOTAL"
            }
        ));
        tablaVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaVentasMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tablaVentas);
        if (tablaVentas.getColumnModel().getColumnCount() > 0) {
            tablaVentas.getColumnModel().getColumn(0).setPreferredWidth(30);
            tablaVentas.getColumnModel().getColumn(1).setPreferredWidth(70);
            tablaVentas.getColumnModel().getColumn(2).setPreferredWidth(80);
        }

        btnPdfVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/pdf.png"))); // NOI18N
        btnPdfVentas.setBorderPainted(false);
        btnPdfVentas.setContentAreaFilled(false);
        btnPdfVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPdfVentasActionPerformed(evt);
            }
        });

        btnEliminarPdfVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cerrar.png"))); // NOI18N
        btnEliminarPdfVentas.setBorderPainted(false);
        btnEliminarPdfVentas.setContentAreaFilled(false);
        btnEliminarPdfVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPdfVentasActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Eliminar PDF");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnPdfVentas)
                        .addGap(26, 26, 26)
                        .addComponent(txtIdVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(btnEliminarPdfVentas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnPdfVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminarPdfVentas)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jTabbedPane1.addTab("Reporte De Ventas", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel27.setText("RUC");

        jLabel28.setText("NOMBRE");

        jLabel29.setText("TELEFONO");

        jLabel30.setText("DIRECCION");

        jLabel31.setText("RAZON SOCIAL");

        txtRucConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRucConfigActionPerformed(evt);
            }
        });

        btnActualizarConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/actualizar.png"))); // NOI18N
        btnActualizarConfig.setText("ACTUALIZAR");
        btnActualizarConfig.setBorderPainted(false);
        btnActualizarConfig.setContentAreaFilled(false);
        btnActualizarConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarConfigActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setText("INFORMACION DE LA COMPAÑIA");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 198, Short.MAX_VALUE)
                .addComponent(jLabel33)
                .addGap(210, 210, 210))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addGap(18, 18, 18)
                        .addComponent(txtRazonConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(jLabel29)
                            .addComponent(jLabel28)
                            .addComponent(jLabel27))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtDireccionConfig)
                                .addComponent(txtNombreConfig)
                                .addComponent(txtRucConfig)
                                .addComponent(txtTelefonoConfig, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnActualizarConfig)
                .addGap(184, 184, 184))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel33)
                .addGap(22, 22, 22)
                .addComponent(txtIdConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnActualizarConfig)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel27)
                            .addComponent(txtRucConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel28)
                    .addComponent(txtNombreConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDireccionConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addGap(24, 24, 24)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(txtTelefonoConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRazonConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGap(94, 94, 94))
        );

        jTabbedPane1.addTab("Configuración", jPanel7);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Código:");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Descripción:");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Cantidad:");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Precio:");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("Proveedor:");

        txtPrecioPro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioProKeyTyped(evt);
            }
        });

        TableProducto.setBackground(new java.awt.Color(204, 153, 255));
        TableProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Código", "Descripcion", "Proveedor", "Stock", "Precio"
            }
        ));
        TableProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableProductoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TableProducto);
        if (TableProducto.getColumnModel().getColumnCount() > 0) {
            TableProducto.getColumnModel().getColumn(0).setPreferredWidth(100);
            TableProducto.getColumnModel().getColumn(1).setPreferredWidth(100);
            TableProducto.getColumnModel().getColumn(2).setPreferredWidth(200);
            TableProducto.getColumnModel().getColumn(3).setPreferredWidth(100);
            TableProducto.getColumnModel().getColumn(4).setPreferredWidth(100);
            TableProducto.getColumnModel().getColumn(5).setPreferredWidth(100);
        }

        btnGuardarPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/saveicon.png"))); // NOI18N
        btnGuardarPro.setBorderPainted(false);
        btnGuardarPro.setContentAreaFilled(false);
        btnGuardarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProActionPerformed(evt);
            }
        });

        btnEditarPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/actualizar.png"))); // NOI18N
        btnEditarPro.setBorderPainted(false);
        btnEditarPro.setContentAreaFilled(false);
        btnEditarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProActionPerformed(evt);
            }
        });

        btnEliminarPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/x.png"))); // NOI18N
        btnEliminarPro.setBorderPainted(false);
        btnEliminarPro.setContentAreaFilled(false);
        btnEliminarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProActionPerformed(evt);
            }
        });

        btnNuevoPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/trash.png"))); // NOI18N
        btnNuevoPro.setBorderPainted(false);
        btnNuevoPro.setContentAreaFilled(false);
        btnNuevoPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProActionPerformed(evt);
            }
        });

        cbxProveedorPro.setEditable(true);
        cbxProveedorPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxProveedorProActionPerformed(evt);
            }
        });

        btnExcelPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/excel.png"))); // NOI18N
        btnExcelPro.setBorderPainted(false);
        btnExcelPro.setContentAreaFilled(false);
        btnExcelPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelProActionPerformed(evt);
            }
        });

        laGuardarproductos.setText("Guardar");

        laEliminarproductos.setText("Eliminar");

        laActualizarproductos.setText("Actualizar");

        jLabel43.setText("Vaciar Campos");

        jLabel44.setText("Excel");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel23))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtDesPro, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(txtCodigoPro, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtIdproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel26))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPrecioPro, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbxProveedorPro, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCantPro))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGuardarPro, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(laGuardarproductos))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminarPro, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEditarPro, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(laEliminarproductos)
                                .addGap(18, 18, 18)
                                .addComponent(laActualizarproductos))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(119, 119, 119)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnExcelPro, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel44)))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel43)
                                    .addComponent(btnNuevoPro, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(308, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtCodigoPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(txtDesPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(txtCantPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(txtPrecioPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(cbxProveedorPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnGuardarPro)
                        .addComponent(btnEliminarPro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnEditarPro, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(laGuardarproductos)
                    .addComponent(laEliminarproductos)
                    .addComponent(laActualizarproductos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnExcelPro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNuevoPro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jLabel44))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Productos", jPanel5);

        paUsu.setBackground(new java.awt.Color(255, 255, 255));

        TableUsuario.setBackground(new java.awt.Color(255, 153, 255));
        TableUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Correo", "Contraseña", "Rol"
            }
        ));
        TableUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableUsuarioMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(TableUsuario);
        if (TableUsuario.getColumnModel().getColumnCount() > 0) {
            TableUsuario.getColumnModel().getColumn(0).setPreferredWidth(100);
            TableUsuario.getColumnModel().getColumn(1).setPreferredWidth(100);
            TableUsuario.getColumnModel().getColumn(2).setPreferredWidth(100);
            TableUsuario.getColumnModel().getColumn(3).setPreferredWidth(100);
            TableUsuario.getColumnModel().getColumn(4).setPreferredWidth(100);
        }

        jLabel46.setText("Nombre:");

        jLabel47.setText("Correo:");

        jLabel48.setText("Contraseña:");

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel49.setText("USUARIOS");

        jLabel50.setText("Rol:");

        btnGuardarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/saveicon.png"))); // NOI18N
        btnGuardarUsuario.setBorderPainted(false);
        btnGuardarUsuario.setContentAreaFilled(false);
        btnGuardarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarUsuarioActionPerformed(evt);
            }
        });

        btnActualizarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/actualizar.png"))); // NOI18N
        btnActualizarUsuario.setBorderPainted(false);
        btnActualizarUsuario.setContentAreaFilled(false);
        btnActualizarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarUsuarioActionPerformed(evt);
            }
        });

        btnEliminarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/x.png"))); // NOI18N
        btnEliminarUsuario.setBorderPainted(false);
        btnEliminarUsuario.setContentAreaFilled(false);
        btnEliminarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUsuarioActionPerformed(evt);
            }
        });

        btnNuevoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/trash.png"))); // NOI18N
        btnNuevoUsuario.setBorderPainted(false);
        btnNuevoUsuario.setContentAreaFilled(false);
        btnNuevoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoUsuarioActionPerformed(evt);
            }
        });

        laGuardarUsuarios.setText("Guardar");

        laActualizarUsuarios.setText("Actualizar");

        laVaciarUsuarios.setText("Vaciar Campos");

        laEliminarUsuarios.setText("Eliminar");

        javax.swing.GroupLayout paUsuLayout = new javax.swing.GroupLayout(paUsu);
        paUsu.setLayout(paUsuLayout);
        paUsuLayout.setHorizontalGroup(
            paUsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paUsuLayout.createSequentialGroup()
                .addGroup(paUsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paUsuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6))
                    .addGroup(paUsuLayout.createSequentialGroup()
                        .addGroup(paUsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(paUsuLayout.createSequentialGroup()
                                .addGap(260, 260, 260)
                                .addComponent(jLabel49))
                            .addGroup(paUsuLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(paUsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel46)
                                    .addComponent(jLabel47))
                                .addGap(18, 18, 18)
                                .addGroup(paUsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(paUsuLayout.createSequentialGroup()
                                        .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtCorreoUsuario))
                                .addGap(45, 45, 45)
                                .addGroup(paUsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel48)
                                    .addComponent(jLabel50))
                                .addGap(18, 18, 18)
                                .addGroup(paUsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtContraUsuario)
                                    .addComponent(txtRolUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addGroup(paUsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(paUsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnGuardarUsuario, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(laGuardarUsuarios)
                                .addComponent(btnNuevoUsuario))
                            .addComponent(laVaciarUsuarios))
                        .addGap(27, 27, 27)
                        .addGroup(paUsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(laActualizarUsuarios)
                            .addComponent(btnActualizarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminarUsuario)
                            .addGroup(paUsuLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(laEliminarUsuarios)))
                        .addGap(15, 15, 15)))
                .addContainerGap())
        );
        paUsuLayout.setVerticalGroup(
            paUsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paUsuLayout.createSequentialGroup()
                .addGroup(paUsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paUsuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel49)
                        .addGap(30, 30, 30)
                        .addGroup(paUsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(paUsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel46)
                                .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(paUsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel48)
                                .addComponent(txtContraUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(paUsuLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(paUsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnActualizarUsuario)
                            .addComponent(btnGuardarUsuario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(paUsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(laGuardarUsuarios)
                            .addComponent(laActualizarUsuarios))))
                .addGroup(paUsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paUsuLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(paUsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47)
                            .addComponent(txtCorreoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50)
                            .addComponent(txtRolUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(paUsuLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(paUsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEliminarUsuario)
                            .addComponent(btnNuevoUsuario))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paUsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(laEliminarUsuarios)
                    .addComponent(laVaciarUsuarios))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Usuarios", paUsu);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 650, 430));

        jPanel9.setBackground(new java.awt.Color(204, 153, 255));

        jLabel1.setBackground(new java.awt.Color(204, 204, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/murci.png"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 650, 160));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtRucConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRucConfigActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRucConfigActionPerformed

    private void txtTelefonoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoProveedorActionPerformed

    private void btnGuardarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarClienteActionPerformed
      if(!"".equals(txtDniCliente.getText())|| !"".equals(txtNombreCliente.getText())|| !"".equals(txtTelefonoCliente.getText()) || !"".equals(txtDireccionCliente.getText()) ){
          cl.setDniCliente(Integer.parseInt(txtDniCliente.getText()));
          cl.setNombreCliente(txtNombreCliente.getText());
          cl.setTelefonoCliente(Integer.parseInt(txtTelefonoCliente.getText()));
          cl.setDireccionCliente(txtDireccionCliente.getText());
          cl.setRazonCliente(txtRazonCliente.getText());
          clien.RegistrarCliente(cl);
          LimpiarTable();
          LimpiarCliente();
           ListaCliente();
        //  btnGuardarCliente.setEnabled(true);
          JOptionPane.showMessageDialog(null,"Cliente Registrado");
      }else{
          JOptionPane.showMessageDialog(null,"Los campos estan vacios");
      }
        
    }//GEN-LAST:event_btnGuardarClienteActionPerformed

    private void btnVentaSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentaSistemaActionPerformed
       LimpiarTable();
        ListaCliente();
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btnVentaSistemaActionPerformed

    private void tableClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableClienteMouseClicked
      // btnEditarCliente.setEnabled(true);
       //btnEliminarCliente.setEnabled(true);
       //btnGuardarCliente.setEnabled(false);
       int fila = tableCliente.rowAtPoint(evt.getPoint());
       txtIdCliente.setText(tableCliente.getValueAt(fila, 0).toString());
       txtDniCliente.setText(tableCliente.getValueAt(fila,1).toString());
       txtNombreCliente.setText(tableCliente.getValueAt(fila, 2).toString());
       txtTelefonoCliente.setText(tableCliente.getValueAt(fila, 3).toString());
       txtDireccionCliente.setText(tableCliente.getValueAt(fila,4).toString());
       txtRazonCliente.setText(tableCliente.getValueAt(fila, 5).toString());
    }//GEN-LAST:event_tableClienteMouseClicked

    private void btnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClienteActionPerformed
        if(!"".equals(txtIdCliente.getText())){
            int  pregunta=JOptionPane.showConfirmDialog(null,"Esta seguro de eliminar?");
            if(pregunta==0){
                int id= Integer.parseInt(txtIdCliente.getText());
                clien.eliminarCliente(id);
                LimpiarTable();
                LimpiarCliente();
                ListaCliente();
            }
        }
    }//GEN-LAST:event_btnEliminarClienteActionPerformed

    private void btnNuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoClienteActionPerformed
        LimpiarCliente();
      //  btnEditarCliente.setEnabled(false);
        //btnEliminarCliente.setEnabled(false);
        //btnGuardarCliente.setEnabled(true);
        
    }//GEN-LAST:event_btnNuevoClienteActionPerformed

    private void btnEditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarClienteActionPerformed
        if ("".equals(txtIdCliente.getText())) {
            JOptionPane.showMessageDialog(null, "seleccione una fila");
        } else {

            if (!"".equals(txtDniCliente.getText()) || !"".equals(txtNombreCliente.getText()) || !"".equals(txtTelefonoCliente.getText())) {
                cl.setDniCliente(Integer.parseInt(txtDniCliente.getText()));
                cl.setNombreCliente(txtNombreCliente.getText());
                cl.setTelefonoCliente(Integer.parseInt(txtTelefonoCliente.getText()));
                cl.setDireccionCliente(txtDireccionCliente.getText());
                cl.setRazonCliente(txtRazonCliente.getText());
                cl.setIdCliente(Integer.parseInt(txtIdCliente.getText()));
                clien.ModificarCliente(cl);
                JOptionPane.showMessageDialog(null, "Cliente Modificado");
                LimpiarTable();
                LimpiarCliente();
                ListaCliente();
            } else {
                JOptionPane.showMessageDialog(null, "Los campos estan vacios");
            }
        }
        
    }//GEN-LAST:event_btnEditarClienteActionPerformed

    private void btnNuevaVentaSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaVentaSistemaActionPerformed
      jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_btnNuevaVentaSistemaActionPerformed

    private void btnProveedorSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedorSistemaActionPerformed
       
        
        LimpiarTable();
       ListarProveedor();
       jTabbedPane1.setSelectedIndex(2);
      // btnEditarProveedor.setEnabled(true);
       //btnEliminarProveedor.setEnabled(true);
       
        
    }//GEN-LAST:event_btnProveedorSistemaActionPerformed

    private void btnProductosSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosSistemaActionPerformed
        LimpiarTable();
        ListarProductos();
        jTabbedPane1.setSelectedIndex(5);
        
       
    }//GEN-LAST:event_btnProductosSistemaActionPerformed

    private void btnVentasSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasSistemaActionPerformed
        jTabbedPane1.setSelectedIndex(3);
        LimpiarTable();
        ListarVentas();
       
        
    }//GEN-LAST:event_btnVentasSistemaActionPerformed

    private void btnConfigSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigSistemaActionPerformed
        //LimpiarTable();
        jTabbedPane1.setSelectedIndex(4);       
    }//GEN-LAST:event_btnConfigSistemaActionPerformed

    private void btnGuardarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProveedorActionPerformed
        
        if (!"".equals(txtRucProveedor.getText()) || !"".equals(txtNombreProveedor.getText()) || !"".equals(txtTelefonoProveedor.getText()) || !"".equals(txtDireccionProveedor.getText())) {
            pr.setRucProveedor(Integer.parseInt(txtRucProveedor.getText()));
            pr.setNombreProveedor(txtNombreProveedor.getText());
            pr.setTelefonoProveedor(Integer.parseInt(txtTelefonoProveedor.getText()));
            pr.setDireccionProveedor(txtDireccionProveedor.getText());
            pr.setRazonProveedor(txtRazonProveedor.getText());
            prdao.RegistrarProveedor(pr);
            JOptionPane.showMessageDialog(null, "Proveedor Registrado");
            LimpiarTable();
            LimpiarProveedor();
            ListarProveedor();
            //btnGuardarProveedor.setEnabled(true); 
            
        } else {
            JOptionPane.showMessageDialog(null, "Los campos esta vacios");
        }
        
    }//GEN-LAST:event_btnGuardarProveedorActionPerformed

    private void TableProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableProveedorMouseClicked
       //  btnEditarProveedor.setEnabled(true);
      // btnEliminarProveedor.setEnabled(true);
       //btnGuardarCliente.setEnabled(false);
       int fila = TableProveedor.rowAtPoint(evt.getPoint());
       txtIdProveedor.setText(TableProveedor.getValueAt(fila, 0).toString());
       txtRucProveedor.setText(TableProveedor.getValueAt(fila,1).toString());
       txtNombreProveedor.setText(TableProveedor.getValueAt(fila, 2).toString());
       txtTelefonoProveedor.setText(TableProveedor.getValueAt(fila, 3).toString());
       txtDireccionProveedor.setText(TableProveedor.getValueAt(fila,4).toString());
       txtRazonProveedor.setText(TableProveedor.getValueAt(fila, 5).toString());
        
    }//GEN-LAST:event_TableProveedorMouseClicked

    private void btnExitSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitSistemaActionPerformed
        dispose();
    }//GEN-LAST:event_btnExitSistemaActionPerformed

    private void btnMaxSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaxSistemaActionPerformed
        this.setExtendedState(MAXIMIZED_BOTH);
    }//GEN-LAST:event_btnMaxSistemaActionPerformed

    private void btnMinSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinSistemaActionPerformed
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_btnMinSistemaActionPerformed

    private void btnPdfVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPdfVentasActionPerformed
        try { 
            int id = Integer.parseInt(txtIdVentas.getText());
            // C:\Users\rafae\Documents\NetBeansProjects\SistemaVenta\src\pdf\venta.pdf
            File file = new File("C:\\Users\\rafae\\Documents\\NetBeansProjects\\SistemaVenta\\src\\pdf\\venta"+id+".pdf");
           
            Desktop.getDesktop().open(file);
            
        } catch (IOException ex) {
            ex.printStackTrace();
             }
    }//GEN-LAST:event_btnPdfVentasActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_formFocusGained

    private void btnEliminarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProveedorActionPerformed
             if (!"".equals(txtIdProveedor.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdProveedor.getText());
                prdao.EliminarProveedor(id);
                LimpiarTable();
                LimpiarProveedor();
                ListarProveedor();
                
            }
        } 
             
    }//GEN-LAST:event_btnEliminarProveedorActionPerformed

    private void btnEditarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProveedorActionPerformed
        if ("".equals(txtIdProveedor.getText())) {
            JOptionPane.showMessageDialog(null, "Seleecione una fila");
        } else {
            if (!"".equals(txtRucProveedor.getText()) || !"".equals(txtNombreProveedor.getText()) || !"".equals(txtTelefonoProveedor.getText()) || !"".equals(txtDireccionProveedor.getText())) {
                pr.setRucProveedor(Integer.parseInt(txtRucProveedor.getText()));
                pr.setNombreProveedor(txtNombreProveedor.getText());
                pr.setTelefonoProveedor(Integer.parseInt(txtTelefonoProveedor.getText()));
                pr.setDireccionProveedor(txtDireccionProveedor.getText());
                pr.setIdProveedor(Integer.parseInt(txtIdProveedor.getText()));
                pr.setRazonProveedor(txtRazonProveedor.getText());
                prdao.ModificarProveedor(pr);
                JOptionPane.showMessageDialog(null, "Proveedor Modificado");
                LimpiarTable();
                ListarProveedor();
                LimpiarProveedor();
               
               
            }
        }
        
        
    }//GEN-LAST:event_btnEditarProveedorActionPerformed

    private void btnNuevoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProveedorActionPerformed
       LimpiarProveedor();
    }//GEN-LAST:event_btnNuevoProveedorActionPerformed

    private void btnGuardarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProActionPerformed
        if (!"".equals(txtCodigoPro.getText()) || !"".equals(txtDesPro.getText()) || !"".equals(cbxProveedorPro.getSelectedItem()) || !"".equals(txtCantPro.getText()) || !"".equals(txtPrecioPro.getText())) {
            pro.setCodigoProductos(txtCodigoPro.getText());
            pro.setNombreProductos(txtDesPro.getText());
            pro.setProveedorProductos(cbxProveedorPro.getSelectedItem().toString());
//            Combo itemP = (Combo) cbxProveedorPro.getSelectedItem();
   //         pro.setProveedor(itemP.getId());
            pro.setStockProductos(Integer.parseInt(txtCantPro.getText()));
            pro.setPrecioProductos(Double.parseDouble(txtPrecioPro.getText()));
            proDao.RegistrarProductos(pro);
            
            JOptionPane.showMessageDialog(null, "Productos Registrado");
            LimpiarTable();
            ListarProductos();
            LimpiarProductos();
            cbxProveedorPro.removeAllItems();
            llenarProveedor();
            
        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
        
    }//GEN-LAST:event_btnGuardarProActionPerformed

    private void btnEliminarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProActionPerformed
       if (!"".equals(txtIdproducto.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdproducto.getText());
                proDao.EliminarProductos(id);
                LimpiarTable();
                LimpiarProductos();
                ListarProductos();
               
            }
        }else{
            JOptionPane.showMessageDialog(null, "Selecciona una fila");
        }
        
    }//GEN-LAST:event_btnEliminarProActionPerformed

    private void btnEditarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProActionPerformed
        if ("".equals(txtIdproducto.getText())) {
            JOptionPane.showMessageDialog(null, "Seleecione una fila");
        } else {
            if (!"".equals(txtCodigoPro.getText()) || !"".equals(txtDesPro.getText()) || !"".equals(txtCantPro.getText()) || !"".equals(txtPrecioPro.getText())) {
                pro.setCodigoProductos(txtCodigoPro.getText());
                pro.setNombreProductos(txtDesPro.getText());
                pro.setProveedorProductos(cbxProveedorPro.getSelectedItem().toString());                
                pro.setStockProductos(Integer.parseInt(txtCantPro.getText()));
                pro.setPrecioProductos(Double.parseDouble(txtPrecioPro.getText()));
                pro.setIdProductos(Integer.parseInt(txtIdproducto.getText()));
                proDao.ModificarProductos(pro);
                JOptionPane.showMessageDialog(null, "Producto Modificado");
                LimpiarTable();
                ListarProductos();
                LimpiarProductos();
                //cbxProveedorPro.removeAllItems();
                //llenarProveedor();
                
            }
        }
        
    }//GEN-LAST:event_btnEditarProActionPerformed

    private void btnNuevoProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProActionPerformed
        LimpiarProductos();
    }//GEN-LAST:event_btnNuevoProActionPerformed

    private void TableProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableProductoMouseClicked
        
         int fila = TableProducto.rowAtPoint(evt.getPoint());
       txtIdproducto.setText(TableProducto.getValueAt(fila, 0).toString());
       txtCodigoPro.setText(TableProducto.getValueAt(fila,1).toString());
       txtDesPro.setText(TableProducto.getValueAt(fila, 2).toString());
       cbxProveedorPro.setSelectedItem(TableProducto.getValueAt(fila, 3).toString());
       txtCantPro.setText(TableProducto.getValueAt(fila,4).toString());
       txtPrecioPro.setText(TableProducto.getValueAt(fila, 5).toString());
        
        
       /* int fila = TableProducto.rowAtPoint(evt.getPoint());
        txtIdproducto.setText(TableProducto.getValueAt(fila, 0).toString());
        pro = proDao.BuscarId(Integer.parseInt(txtIdproducto.getText()));
        txtCodigoPro.setText(pro.getCodigoProductos());
        txtDesPro.setText(pro.getNombreProductos());
        txtCantPro.setText("" + pro.getStockProductos());
        txtPrecioPro.setText("" + pro.getPrecioProductos());*/
        
       // cbxProveedorPro.setSelectedItem(new Combo(pro.getProveedor(), pro.getProveedorPro()));
        
    }//GEN-LAST:event_TableProductoMouseClicked

    private void btnExcelProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelProActionPerformed
        Excel.reporte();
    }//GEN-LAST:event_btnExcelProActionPerformed

    private void txtCodigoVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoVentaKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtCodigoVenta.getText())) {
                String cod = txtCodigoVenta.getText();
                pro = proDao.BuscarPro(cod);
                if (pro.getNombreProductos()!= null) {
                   // txtCodigoVenta.setText("" + pro.getIdProductos());
                    txtDescripcionVenta.setText("" + pro.getNombreProductos());
                    txtPrecioVenta.setText("" + pro.getPrecioProductos());
                    txtStockVenta.setText("" + pro.getStockProductos());
                    txtCantidadVenta.requestFocus();
                } else {
                    LimpiarVenta();
                    txtCodigoVenta.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el codigo del productos");
                txtCodigoVenta.requestFocus();
            }
        }
    }//GEN-LAST:event_txtCodigoVentaKeyPressed

    private void txtCodigoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoVentaActionPerformed

    private void txtCantidadVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadVentaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //txtCantidadVenta
            if (!"".equals(txtCantidadVenta.getText())) {
                String cod= txtCodigoVenta.getText();
                
              //  int id = Integer.parseInt(txtCodigoPro.getText());
                String descripcion = txtDescripcionVenta.getText();
               
                int cant = Integer.parseInt(txtCantidadVenta.getText());
                double precio = Double.parseDouble(txtPrecioVenta.getText());
                double total = cant * precio;
                int stock = Integer.parseInt(txtStockVenta.getText());
                if (stock >= cant) {
                    item = item + 1;  
                    tmp = (DefaultTableModel) TableVenta.getModel();
                     for (int i = 0; i < TableVenta.getRowCount(); i++) {
                        if (TableVenta.getValueAt(i, 1).equals(txtDescripcionVenta.getText())) {
                            JOptionPane.showMessageDialog(null, "El producto ya esta registrado");
                            return;
                        }
                    }
                    
                    ArrayList lista = new ArrayList();
                    lista.add(item);
                    lista.add(cod);
                    lista.add(descripcion);
                    lista.add(cant);
                    lista.add(precio);
                    lista.add(total);
                    Object[] O = new Object[5];
                    O[0] = lista.get(1);
                    O[1] = lista.get(2);
                    O[2] = lista.get(3);
                    O[3] = lista.get(4);
                    O[4] = lista.get(4);
                    tmp.addRow(O);
                    TableVenta.setModel(tmp);
                    TotalPagar();
                    LimpiarVenta();
                    txtCodigoVenta.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Stock no disponible");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese Cantidad");
            }
        }
        
    }//GEN-LAST:event_txtCantidadVentaKeyPressed

    private void txtStockVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStockVentaActionPerformed

    private void txtCantidadVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadVentaActionPerformed

    private void btnEliminarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarVentaActionPerformed
        modelo = (DefaultTableModel) TableVenta.getModel();
        modelo.removeRow(TableVenta.getSelectedRow());
        TotalPagar();
        txtCodigoVenta.requestFocus();
        
    }//GEN-LAST:event_btnEliminarVentaActionPerformed

    private void txtRucVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucVentaKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtRucVenta.getText())) {
                int dni = Integer.parseInt(txtRucVenta.getText());
                cl = clien.Buscarcliente(dni);
                if (cl.getNombreCliente()!= null) {
                    txtNombreClienteventa.setText("" + cl.getNombreCliente());
                    txtTelefonoCV.setText(""+cl.getTelefonoCliente());
                    txtDireccionCV.setText(""+cl.getDireccionCliente());
                    txtRazonCV.setText(""+cl.getRazonCliente());
                 //   txtIdProd.setText("" + cl.getIdCliente());
                } else {
                    txtRucVenta.setText("");
                    JOptionPane.showMessageDialog(null, "El cliente no existe");
                }
            }
        }
        
    }//GEN-LAST:event_txtRucVentaKeyPressed

    private void btnGenerarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarVentaActionPerformed
      RegistrarVenta();
        
        /*if(TableVenta.getRowCount() >0){
          if(!"".equals(txtNombreClienteventa.getText())){
       RegistrarVenta();
       RegistrarDetalle();
       ActualizarStock();
       pdf();
       LimpiarTableVenta(); 
       LimpiarClienteventa();
       
                 }else{
              JOptionPane.showInputDialog(null,"Debes buscar un cliente");
          }
      }else{
          JOptionPane.showInputDialog(null,"No hay productos en la venta");
      }
        */
       
      // LimpiarClienteVenta();
    }//GEN-LAST:event_btnGenerarVentaActionPerformed

    private void cbxProveedorProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxProveedorProActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxProveedorProActionPerformed

    private void txtRucVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRucVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRucVentaActionPerformed

    private void TableVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableVentaMouseClicked
        int fila = TableVenta.rowAtPoint(evt.getPoint());
        txtCodigoVenta.setText(TableVenta.getValueAt(fila, 0).toString());
        //Vd= Vdao
        
    }//GEN-LAST:event_TableVentaMouseClicked

    private void txtCodigoVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoVentaKeyTyped
        event.numberKeyPress(evt);
        
        
    }//GEN-LAST:event_txtCodigoVentaKeyTyped

    private void txtDescripcionVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionVentaKeyTyped
       event.textKeyPress(evt);
        
    }//GEN-LAST:event_txtDescripcionVentaKeyTyped

    private void txtCantidadVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadVentaKeyTyped
    //   event.numberKeyPress(evt);
    }//GEN-LAST:event_txtCantidadVentaKeyTyped

    private void txtPrecioProKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioProKeyTyped
       event.numberDecimalKeyPress(evt,txtIdProd);
    }//GEN-LAST:event_txtPrecioProKeyTyped

    private void btnActualizarConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarConfigActionPerformed
          if (!"".equals(txtRucConfig.getText()) || !"".equals(txtNombreConfig.getText()) || !"".equals(txtTelefonoConfig.getText()) || !"".equals(txtDireccionConfig.getText()) || !"".equals(txtRazonConfig.getText())) {
                conf.setRucConfig(Integer.parseInt(txtRucConfig.getText()));
                conf.setNombreConfig(txtNombreConfig.getText());
                conf.setTelefonoConfig(Integer.parseInt(txtTelefonoConfig.getText()));
                conf.setDireccionConfig(txtDireccionConfig.getText());
                conf.setRazonConfig(txtRazonConfig.getText());
                conf.setIdConfig(Integer.parseInt(txtIdConfig.getText()));
                proDao.ModificarDatos(conf);
                JOptionPane.showMessageDialog(null, "Empresa Modificada");
               ListarConfig();
            } else {
                JOptionPane.showMessageDialog(null, "Los campos estan vacios");
            }
        
        
    }//GEN-LAST:event_btnActualizarConfigActionPerformed

    private void tablaVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaVentasMouseClicked
        int fila = tablaVentas.rowAtPoint(evt.getPoint());
        txtIdVenta.setText(tablaVentas.getValueAt(fila,0).toString());
       
    }//GEN-LAST:event_tablaVentasMouseClicked

    private void btnGraficarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficarActionPerformed
       
        String fechaReporte = new SimpleDateFormat("dd/MM/yyyy").format(Midate.getDate());
        Grafico.Grafica2(fechaReporte);
        
    }//GEN-LAST:event_btnGraficarActionPerformed

    private void btnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosActionPerformed
      LimpiarTable();
      ListarUsuario();
      jTabbedPane1.setSelectedIndex(6);
        
        
    }//GEN-LAST:event_btnUsuariosActionPerformed

    private void TableUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableUsuarioMouseClicked
       int fila = TableUsuario.rowAtPoint(evt.getPoint());
       txtIdUsuario.setText(TableUsuario.getValueAt(fila, 0).toString());
       txtNombreUsuario.setText(TableUsuario.getValueAt(fila,1).toString());
       txtCorreoUsuario.setText(TableUsuario.getValueAt(fila, 2).toString());
       txtContraUsuario.setText(TableUsuario.getValueAt(fila, 3).toString());
       txtRolUsuario.setText(TableUsuario.getValueAt(fila,4).toString());
       
    }//GEN-LAST:event_TableUsuarioMouseClicked

    private void btnGuardarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarUsuarioActionPerformed
         if (!"".equals(txtNombreUsuario.getText()) || !"".equals(txtCorreoUsuario.getText()) || !"".equals(txtContraUsuario.getText()) || !"".equals(txtRolUsuario.getText())) {
            us.setNombreUsuario(txtNombreUsuario.getText());
            us.setCorreoUsuario(txtCorreoUsuario.getText());
            us.setContraUsuario(txtContraUsuario.getText());
            us.setRolUsuario(txtRolUsuario.getText());
             boolean RegistrarUsuario = usdao.RegistrarUsuario(us);
             
            
            JOptionPane.showMessageDialog(null, "Usuario Registrado");
            LimpiarTable();
            LimpiarUsuario();
            ListarUsuario();
            //btnGuardarProveedor.setEnabled(true); 
            
        } else {
            JOptionPane.showMessageDialog(null, "Los campos esta vacios");
        }
    }//GEN-LAST:event_btnGuardarUsuarioActionPerformed

    private void btnActualizarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarUsuarioActionPerformed
    
            if (!"".equals(txtNombreUsuario.getText()) || !"".equals(txtCorreoUsuario.getText()) || !"".equals(txtContraUsuario.getText()) || !"".equals(txtRolUsuario.getText())) {
                us.setNombreUsuario(txtNombreUsuario.getText());
                us.setCorreoUsuario(txtCorreoUsuario.getText());
                us.setContraUsuario(txtContraUsuario.getText());
                us.setRolUsuario(txtRolUsuario.getText());
                us.setIdUsuario(Integer.parseInt(txtIdUsuario.getText()));
                usdao.ModificarUsuarios(us);
                JOptionPane.showMessageDialog(null, "Usuario Modificado");
                                
                LimpiarTable();
                LimpiarUsuario();
                ListarUsuario();
                
            }else{
                JOptionPane.showMessageDialog(null, "Los campos estan vacios");
                           
               
            }
        
    }//GEN-LAST:event_btnActualizarUsuarioActionPerformed

    private void btnEliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUsuarioActionPerformed
          if (!"".equals(txtIdUsuario.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdUsuario.getText());
               usdao.EliminarUsuarios(id);
                
                LimpiarTable();
                LimpiarUsuario();
                ListarUsuario();
                
            }
        } 
    }//GEN-LAST:event_btnEliminarUsuarioActionPerformed

    private void btnNuevoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoUsuarioActionPerformed
        LimpiarUsuario();
    }//GEN-LAST:event_btnNuevoUsuarioActionPerformed

    private void btnEliminarPdfVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPdfVentasActionPerformed
        if (!"".equals(txtIdVentas.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdVentas.getText());
                Vdao.EliminarVentas(id);
                LimpiarTable();
                LimpiarVentas();
                ListarVentas();
                
                /*  
                rafa_19@gmail.com
                LimpiarTable();
                LimpiarProveedor();
                ListarProveedor();
                */
                
            }
        } 
        
        
    }//GEN-LAST:event_btnEliminarPdfVentasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sistema().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelTotal;
    private javax.swing.JLabel LabelVendedor;
    private com.toedter.calendar.JDateChooser Midate;
    private javax.swing.JPanel NuevaVenta;
    private javax.swing.JPanel Proveedor;
    private javax.swing.JTable TableProducto;
    private javax.swing.JTable TableProveedor;
    private javax.swing.JTable TableUsuario;
    private javax.swing.JTable TableVenta;
    private javax.swing.JButton btnActualizarConfig;
    private javax.swing.JButton btnActualizarUsuario;
    private javax.swing.JButton btnConfigSistema;
    private javax.swing.JButton btnEditarCliente;
    private javax.swing.JButton btnEditarPro;
    private javax.swing.JButton btnEditarProveedor;
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnEliminarPdfVentas;
    private javax.swing.JButton btnEliminarPro;
    private javax.swing.JButton btnEliminarProveedor;
    private javax.swing.JButton btnEliminarUsuario;
    private javax.swing.JButton btnEliminarVenta;
    private javax.swing.JButton btnExcelPro;
    private javax.swing.JButton btnExitSistema;
    private javax.swing.JButton btnGenerarVenta;
    private javax.swing.JButton btnGraficar;
    private javax.swing.JButton btnGuardarCliente;
    private javax.swing.JButton btnGuardarPro;
    private javax.swing.JButton btnGuardarProveedor;
    private javax.swing.JButton btnGuardarUsuario;
    private javax.swing.JButton btnMaxSistema;
    private javax.swing.JButton btnMinSistema;
    private javax.swing.JButton btnNuevaVentaSistema;
    private javax.swing.JButton btnNuevoCliente;
    private javax.swing.JButton btnNuevoPro;
    private javax.swing.JButton btnNuevoProveedor;
    private javax.swing.JButton btnNuevoUsuario;
    private javax.swing.JButton btnPdfVentas;
    private javax.swing.JButton btnProductosSistema;
    private javax.swing.JButton btnProveedorSistema;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JButton btnVentaSistema;
    private javax.swing.JButton btnVentasSistema;
    private javax.swing.JComboBox<String> cbxProveedorPro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel laActualizarUsuarios;
    private javax.swing.JLabel laActualizarcliente;
    private javax.swing.JLabel laActualizarproductos;
    private javax.swing.JLabel laActualizarproveedor;
    private javax.swing.JLabel laEliminarUsuarios;
    private javax.swing.JLabel laEliminarcliente;
    private javax.swing.JLabel laEliminarproductos;
    private javax.swing.JLabel laEliminarproveedor;
    private javax.swing.JLabel laGuardarUsuarios;
    private javax.swing.JLabel laGuardarcliente;
    private javax.swing.JLabel laGuardarproductos;
    private javax.swing.JLabel laGuardarproveedor;
    private javax.swing.JLabel laVaciarUsuarios;
    private javax.swing.JPanel paUsu;
    private javax.swing.JTable tablaVentas;
    private javax.swing.JTable tableCliente;
    private javax.swing.JTextField txtCantPro;
    private javax.swing.JTextField txtCantidadVenta;
    private javax.swing.JTextField txtCodigoPro;
    private javax.swing.JTextField txtCodigoVenta;
    private javax.swing.JTextField txtContraUsuario;
    private javax.swing.JTextField txtCorreoUsuario;
    private javax.swing.JTextField txtDesPro;
    private javax.swing.JTextField txtDescripcionVenta;
    private javax.swing.JTextField txtDireccionCV;
    private javax.swing.JTextField txtDireccionCliente;
    private javax.swing.JTextField txtDireccionConfig;
    private javax.swing.JTextField txtDireccionProveedor;
    private javax.swing.JTextField txtDniCliente;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtIdConfig;
    private javax.swing.JTextField txtIdProd;
    private javax.swing.JTextField txtIdProveedor;
    private javax.swing.JTextField txtIdUsuario;
    private javax.swing.JTextField txtIdVenta;
    private javax.swing.JTextField txtIdVentas;
    private javax.swing.JTextField txtIdproducto;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreClienteventa;
    private javax.swing.JTextField txtNombreConfig;
    private javax.swing.JTextField txtNombreProveedor;
    private javax.swing.JTextField txtNombreUsuario;
    private javax.swing.JTextField txtPrecioPro;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtRazonCV;
    private javax.swing.JTextField txtRazonCliente;
    private javax.swing.JTextField txtRazonConfig;
    private javax.swing.JTextField txtRazonProveedor;
    private javax.swing.JTextField txtRolUsuario;
    private javax.swing.JTextField txtRucConfig;
    private javax.swing.JTextField txtRucProveedor;
    private javax.swing.JTextField txtRucVenta;
    private javax.swing.JTextField txtStockVenta;
    private javax.swing.JTextField txtTelefonoCV;
    private javax.swing.JTextField txtTelefonoCliente;
    private javax.swing.JTextField txtTelefonoConfig;
    private javax.swing.JTextField txtTelefonoProveedor;
    // End of variables declaration//GEN-END:variables
private void LimpiarCliente(){
    txtIdCliente.setText("");
    txtDniCliente.setText("");
    txtNombreCliente.setText("");
    txtTelefonoCliente.setText("");
    txtDireccionCliente.setText("");
    txtRazonCliente.setText("");
    
    
    
}
}

