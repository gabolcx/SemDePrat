package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/mainView.fxml"));
            Scene scene = new Scene(root);
            // Agregar el CSS a la escena
            scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
            primaryStage.setTitle("Sistema de Control de Stock");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
