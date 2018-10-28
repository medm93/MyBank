package utils;

import javafx.scene.control.Alert;
import javafx.stage.Window;

public class Dialogs {

    public static void errorAlert(/*Window owner,*/ String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        //alert.initOwner(owner);
        alert.showAndWait();
    }
}
