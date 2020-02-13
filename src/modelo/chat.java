/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author David
 */
public class chat {
    private SimpleStringProperty usuario;
    private SimpleStringProperty mensaje;
    private SimpleStringProperty fecha;
    private SimpleIntegerProperty id;

    public chat(String usuario, String mensaje, String fecha, Integer id) {
        this.usuario =new SimpleStringProperty( usuario);
        this.mensaje = new SimpleStringProperty(mensaje);
        this.fecha = new SimpleStringProperty(fecha);
        this.id = new SimpleIntegerProperty(id);
    }

    public String getUsuario() {
        return usuario.get();
    }

    public void setUsuario(SimpleStringProperty usuario) {
        this.usuario = usuario;
    }

    public String getMensaje() {
        return mensaje.get();
    }

    public void setMensaje(SimpleStringProperty mensaje) {
        this.mensaje = mensaje;
    }

    public String getFecha() {
        return fecha.get();
    }

    public void setFecha(SimpleStringProperty fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id.get();
    }

    public void setId(SimpleIntegerProperty id) {
        this.id = id;
    }
    
    
    
    
}
