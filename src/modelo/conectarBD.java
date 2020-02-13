/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author David
 */
public class conectarBD {

    static Connection cnx;
    static Statement st;
    static ResultSet rs;
    static PreparedStatement pstm;
    static String user, clave;

    
    public conectarBD(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx=DriverManager.getConnection("jdbc:mysql://remotemysql.com/"+user, user, clave);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(conectarBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(conectarBD.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    public conectarBD(String cadena1,String cadena2){
        this.user = cadena1;
        this.clave = cadena2;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx=DriverManager.getConnection("jdbc:mysql://remotemysql.com/"+user, user, clave);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(conectarBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(conectarBD.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public static boolean accesoUsuario(String usuario, String clave) {
        boolean resultado = false;
        
        try {
            String cadenaSql="select * from user where usuario=? and clave=?";
            pstm = cnx.prepareStatement(cadenaSql);
            
            pstm.setString(1,usuario);
            pstm.setString(2, clave);
            rs=pstm.executeQuery();
            while (rs.next()){
                resultado = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(conectarBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    public static int registrarUsuario(String usuario, String clave) {
        int resultado=-1;
        
        try {
            String cadenaSql="insert into user(usuario,clave) values(?,?)";
            PreparedStatement pstm=cnx.prepareStatement(cadenaSql);
            pstm.setString(1, usuario);
            pstm.setString(2, clave);
            
            resultado = pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(conectarBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultado;
    }

    public ObservableList<chat> cargarChat() {
        ObservableList<chat> lista = FXCollections.observableArrayList();
        
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-YYYY kk:mm:ss");
        
        try{
            String cadenaSql="select * from chat order by 1 desc";
            PreparedStatement pstm = cnx.prepareStatement(cadenaSql);
            
            String fecha;
         
            rs=pstm.executeQuery();
            while (rs.next()){
                fecha = formato.format(rs.getTimestamp(3));
                System.out.println(rs.getString(4));
                chat ch=new chat(
                    rs.getString(2),
                    rs.getString(4),
                    fecha,
                    rs.getInt(1)
                );
                lista.add(ch);
                
            }
            
        }catch (SQLException ex) {
            Logger.getLogger(conectarBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public void insertarMensaje(String usuario, String mensaje) {
        try {
            Timestamp fecha = new Timestamp(System.currentTimeMillis());
            
            String cadenaSql="insert into chat(id,usuario,fecha,mensaje) values(null,?,?,?)";
            PreparedStatement pstm=cnx.prepareStatement(cadenaSql);
            pstm.setString(1, usuario);
            pstm.setTimestamp(2, fecha);
            pstm.setString(3, mensaje);
            
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(conectarBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
