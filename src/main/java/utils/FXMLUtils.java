package utils;

import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import java.util.ResourceBundle;

public class FXMLUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(FXMLUtils.class);
    private static final String RESOURCE_BUNDLE = "bundles/Messages";

    public static Pane fxmlLoader(String fxmlPath) {
        FXMLLoader fxmlLoader = new FXMLLoader(FXMLUtils.class.getResource(fxmlPath));
        fxmlLoader.setResources(getResourceBundle());
        try {
            return fxmlLoader.load();
        } catch (Exception exception) {
            DialogsUtils.fxmlLoaderError(exception.getMessage());
            LOGGER.warn(exception.getCause().getMessage());
        }
        return null;
    }

    public static ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle(RESOURCE_BUNDLE);
    }
}
