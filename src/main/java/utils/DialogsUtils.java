package utils;

import javafx.scene.control.Alert;

import java.util.ResourceBundle;

public class DialogsUtils {

    public static final ResourceBundle RESOURCE_BUNDLE = FXMLUtils.getResourceBundle();

    public static void errorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void informationAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void fxmlLoaderError(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(RESOURCE_BUNDLE.getString("dialogs.fxmlLoaderError.title"));
        alert.setHeaderText(RESOURCE_BUNDLE.getString("dialogs.fxmlLoaderError.header"));
        alert.setContentText(error);
        alert.showAndWait();
    }
}
