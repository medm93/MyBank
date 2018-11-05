package app;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.DBManager;
import utils.FXMLUtils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Main extends Application {

    private static final String MAIN_PANEL_VIEW_FXML = "/fxml/MainPanelView.fxml";

    public void start(Stage primaryStage) throws Exception {
//        Locale.setDefault(Locale.ENGLISH);
        Parent parent = FXMLUtils.fxmlLoader(MAIN_PANEL_VIEW_FXML);
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setTitle("MyBank");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setMaximized(true);
        DBManager.initializeDatabase();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
