/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.chat;
import modelo.conectarBD;

/**
 * FXML Controller class
 *
 * @author David
 */
public class ControladorChat implements Initializable {

    ObservableList <chat> listaChat=FXCollections.observableArrayList();
    
    String cad_conexion1,cad_conexion2;
    conectarBD cnx = new conectarBD();
    public Stage ventataChat;
    @FXML
    private TextField txtMensaje;
    @FXML
    private Button btnEnviar;
    @FXML
    private Button btnVolver;
    @FXML
    private Button btnListar;
    @FXML
    public Label lbNombre;
    @FXML
    private TableView<chat> tableChat;
    @FXML
    private TableColumn<chat, Integer> c1;
    @FXML
    private TableColumn<chat, String> c2;
    @FXML
    private TableColumn<chat, String> c4;
    @FXML
    private TableColumn<chat, String> c3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtMensaje.requestFocus();
        
        c1.setCellValueFactory(new PropertyValueFactory<>("id"));
        c2.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        c3.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        c4.setCellValueFactory(new PropertyValueFactory<>("mensaje"));
        
        
        listaChat = cnx.cargarChat();
        
        tableChat.setItems(listaChat);
        
        tableChat.refresh();
    }    

    @FXML
    private void btnEnviar_OnAction(ActionEvent event) {
        cnx.insertarMensaje(lbNombre.getText(), txtMensaje.getText());
        txtMensaje.setText("");
        
        recargar();
    }
    
      @FXML
    private void btnListar_OnClick(ActionEvent event) {
        recargar();
        
    }
    public void recargar(){
        tableChat.getItems().clear();
        listaChat.clear();
        
        
        c1.setCellValueFactory(new PropertyValueFactory<>("id"));
        c2.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        c3.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        c4.setCellValueFactory(new PropertyValueFactory<>("mensaje"));
        
        
        listaChat = cnx.cargarChat();
        
        tableChat.setItems(listaChat);
        
        tableChat.refresh();
    }
    
    

    @FXML
    private void btnVolver_OnClick(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/FXML_Login.fxml"));
        
        Parent root =  loader.load();
        ControladorLogin controladorLogin = loader.getController();
       
        Stage ventana = new Stage();
        controladorLogin.ventanaLogin=ventana;
        
        ventana.setResizable(false);
        
        ventana.setTitle("LOGIN");
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/recursos/estilo.css").toURI().toString());
        ventana.setScene(scene);
        /*ventana.setWidth(800);
        ventana.setHeight(400);*/
        ventana.setX(0);
        ventana.setY(0);
        ventana.show();
        
        Stage stage_actual = (Stage) btnVolver.getScene().getWindow();
        stage_actual.close();
    } catch (IOException ex) {
        Logger.getLogger(ControladorLogin.class.getName()).log(Level.SEVERE, null, ex);
    }   catch (URISyntaxException ex) {
            Logger.getLogger(ControladorChat.class.getName()).log(Level.SEVERE, null, ex);
        }
   }


  
    
}
