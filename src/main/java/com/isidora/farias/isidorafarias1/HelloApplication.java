package com.isidora.farias.isidorafarias1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                HelloApplication.class.getResource("usuarios-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 700, 550);

        stage.setTitle("Mantenedor de Usuarios - Isidora Farías");
        stage.setScene(scene);
        stage.setMinWidth(600);
        stage.setMinHeight(450);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}