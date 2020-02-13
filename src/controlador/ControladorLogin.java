/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import modelo.conectarBD;

/**
 *
 * @author David
 */
public class ControladorLogin implements Initializable {
    public Stage ventanaLogin;
    conectarBD cnx;
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtClave;

    @FXML
    private Button btnAcceso;
    @FXML
    private Button btnSalir;
    @FXML
    private Button btnRegistrarse;
    @FXML
    private ToggleGroup radio;
    @FXML
    public RadioButton rb1;
    @FXML
    public RadioButton rb2;
    @FXML
    public RadioButton rb3;
    
    String cadena1,cadena2;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         
        
 
    }    

    @FXML
    private void btnAcceso_OnClick(ActionEvent event) {
        if(rb1.isSelected()){
            cadena1="Pr1mdxAdrh";
            cadena2="fNBUrxid1O";
        }
        else if(rb2.isSelected()){
            cadena1="j3vYEeCG3p";
            cadena2="GPpsVJJ4SK";
        }else{
            cadena1="BergjSfgJL";
            cadena2="2WkhdwjZWd";
        }
        cnx = new conectarBD(cadena1,cadena2);
        
        
       if(conectarBD.accesoUsuario(txtUsuario.getText(), txtClave.getText())){
           abrirChat();
           
       }else{
           Alert alert = new Alert(AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText(null);
           alert.setContentText("Vaya, usuario no registrado o uno de los campos incorrecto...");
           alert.showAndWait();
       }
       txtUsuario.setText("");
       txtClave.setText("");
        
    }

    @FXML
    private void btnSalir_OnClick(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmacion");
        alert.setHeaderText("Has elegido salir de la aplicación");
        alert.setContentText("¿Estás seguro de esto?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Platform.exit();
        }
    }

    @FXML
    private void btnRegistrarse_OnClick(ActionEvent event) {
        if(rb1.isSelected()){
            cadena1="Pr1mdxAdrh";
            cadena2="fNBUrxid1O";
        }
        else if(rb2.isSelected()){
            cadena1="j3vYEeCG3p";
            cadena2="GPpsVJJ4SK";
        }else{
            cadena1="BergjSfgJL";
            cadena2="2WkhdwjZWd";
        }
        
        cnx = new conectarBD(cadena1,cadena2);
        
        int n = conectarBD.registrarUsuario(txtUsuario.getText(), txtClave.getText());
        
        if(n==1){
           Alert alert = new Alert(AlertType.INFORMATION);
           alert.setTitle("Informacion");
           alert.setHeaderText(null);
           alert.setContentText("Usuario Registrado Correctamente");
           alert.showAndWait();
        }else{
            Alert alert = new Alert(AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText(null);
           alert.setContentText("Vaya, error al registrar usuario");
           alert.showAndWait();
        } 
        
        txtUsuario.setText("");
        txtClave.setText("");
    }  

    private void abrirChat() {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/FXML_Chat.fxml"));
        
        Parent root =  loader.load();
        ControladorChat controladorChat = loader.getController();
       
        Stage ventana = new Stage();
        controladorChat.ventataChat=ventana;
        //controladorChat.cad_conexion1 = cadena1;
        //controladorChat.cad_conexion2 = cadena2;
        controladorChat.lbNombre.setText(txtUsuario.getText());
        //controladorChat.cnx = cnx;
        
        ventana.setResizable(false);
        
        ventana.setTitle("CHAT");
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/recursos/estilo.css").toURI().toString());
        ventana.setScene(scene);
        //ventana.setWidth(1000);
        //ventana.setHeight(600);
        ventana.setX(0);
        ventana.setY(0);
        ventana.show();
        
        Stage stage_actual = (Stage) btnAcceso.getScene().getWindow();
        stage_actual.close();
    } catch (IOException ex) {
        Logger.getLogger(ControladorChat.class.getName()).log(Level.SEVERE, null, ex);
    }   catch (URISyntaxException ex) {
            Logger.getLogger(ControladorLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
