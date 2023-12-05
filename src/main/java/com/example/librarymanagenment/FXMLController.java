package com.example.librarymanagenment; // ham cho login.fxml

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

// ham cho login.fxml
public class FXMLController implements Initializable {
    @FXML
    private Button button_login;

    @FXML
    private PasswordField pass;

    @FXML
    private TextField login_id;


    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void exit(){
        System.exit(0);
    }

    public void login() {
        String sql = "SELECT * FROM login WHERE login_id = ? and password = ?";

        connect = Database.connectDB();

        try{

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, login_id.getText());
            prepare.setString(2, pass.getText());
            result = prepare.executeQuery();

            Alert alert;

            if(login_id.getText().isEmpty() || pass.getText().isEmpty()){

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Admin Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields.");
                alert.showAndWait();
            }else{
                if(result.next()){


                    getData.msv = login_id.getText();


                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Admin Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();

//                    TO HIDE THE LOGIN FORM, hide di login.fxml
                    button_login.getScene().getWindow().hide();
//                    FOR DASHBOARD
                    Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));

                    Stage stage = new Stage();

                    Scene scene = new Scene(root);



                    stage.setScene(scene);
                    stage.show();
                }else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Admin Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username or Password.");
                    alert.showAndWait();
                }

            }

        }catch(Exception e){e.printStackTrace();}

    }


    // ONLY NUMBERS ALLOWED, o onKeyType cua stuNum
    public void numbersOnly(KeyEvent event){

        if(event.getCharacter().matches("[^\\e\t\r\\d+$]")){
            event.consume();

            login_id.setStyle("-fx-border-color:#e04040");  // nhap sai phan gach chan doi thanh mau do
        }else{
            login_id.setStyle("-fx-border-color:#fff"); //khong nhap sai phan gach chan doi thanh mau trang
        }

    }
}

