package com.example.librarymanagenment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class LibraryManagement extends Application {

    private double x = 0;
    private double y = 0;


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("nhaplogin.fxml"));

        Scene scene = new Scene(root);

        root.setOnMousePressed((MouseEvent event) ->{

            x = event.getSceneX();
            y = event.getSceneY();

        });

        root.setOnMouseDragged((MouseEvent event) ->{

            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);

        });


        //stage.initStyle(StageStyle.TRANSPARENT); // no lam mat - [] x o goc phai tren man hinh

//        String css = this.getClass().getResource("designLogin.css").toExternalForm();
//        scene.getStylesheets().add(css);

        //stage.initStyle(StageStyle.TRANSPARENT);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
