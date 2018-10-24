package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage primaryStage) throws Exception {
        Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setTitle("MyBank");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
