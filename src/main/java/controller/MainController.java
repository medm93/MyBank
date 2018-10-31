package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import model.CustomerList;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public static MainController MAIN_CONTROLLER;
    private static final String WELCOME_PANEL_VIEW_FXML = "/fxml/WelcomePanelView.fxml";

    @FXML
    private BorderPane borderPane;

    @FXML
    private LoginPanelController loginPanelController;

    @FXML
    private UserPanelController userPanelController;

    private CustomerList customerList;
    private String session;

    public void initialize(URL location, ResourceBundle resources) {
        MAIN_CONTROLLER = this;
        try {
            setCenter(WELCOME_PANEL_VIEW_FXML);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        customerList = new CustomerList();
//        loginPanelController.setMainController(this);
//        loginPanelController.setCustomerList(customerList);

    }

    public void setCenter(String fxmlPath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(fxmlPath));
        Parent parent = fxmlLoader.load();
        borderPane.setCenter(parent);
    }

    public FXMLLoader getFXMLLoader(String fxmlPath) {
        return new FXMLLoader(this.getClass().getResource(fxmlPath));
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public CustomerList getCustomerList() {
        return customerList;
    }

    public void setCustomerList(CustomerList customerList) {
        this.customerList = customerList;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public LoginPanelController getLoginPanelController() {
        return loginPanelController;
    }

    public void setLoginPanelController(LoginPanelController loginPanelController) {
        this.loginPanelController = loginPanelController;
    }

    public UserPanelController getUserPanelController() {
        return userPanelController;
    }

    public void setUserPanelController(UserPanelController userPanelController) {
        this.userPanelController = userPanelController;
    }
}
