package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import model.CustomerList;
import utils.DBManager;
import utils.FXMLUtils;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPanelController implements Initializable {

    public static MainPanelController MAIN_CONTROLLER;
    private static final String WELCOME_PANEL_VIEW_FXML = "/fxml/WelcomePanelView.fxml";

    @FXML
    private BorderPane borderPane;

    @FXML
    private LoginPanelController loginPanelController;

    @FXML
    private CustomerPanelController customerPanelController;

    private CustomerList customerList;
    private String session;

    public void initialize(URL location, ResourceBundle resources) {
        MAIN_CONTROLLER = this;
        setCenter(WELCOME_PANEL_VIEW_FXML);
//        customerList = new CustomerList();
//        loginPanelController.setMainPanelController(this);
//        loginPanelController.setCustomerList(customerList);

    }

    @FXML
    void exit(ActionEvent event) {
        DBManager.closeConnectionSource();
        Platform.exit();
        System.exit(0);
    }

    public void setCenter(String fxmlPath) {
//        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(fxmlPath));
//        Parent parent = fxmlLoader.load();
//        borderPane.setCenter(parent);
        borderPane.setCenter(FXMLUtils.fxmlLoader(fxmlPath));
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

    public CustomerPanelController getCustomerPanelController() {
        return customerPanelController;
    }

    public void setCustomerPanelController(CustomerPanelController customerPanelController) {
        this.customerPanelController = customerPanelController;
    }
}
