package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static controllers.LoginController.LOGIN_CONTROLLER;
import static controllers.MainController.MAIN_CONTROLLER;

public class UserPanelController implements Initializable {

    public static UserPanelController USER_PANEL_CONTROLLER;

    @FXML
    private Label name;

    @FXML
    private Label login;

    @FXML
    private Button logoutButton;

    @FXML
    private Label accountBalance;

    @FXML
    private ToggleButton paymentButton;

    @FXML
    private ToggleGroup toggleGroup;

    @FXML
    private ToggleButton withdrawalButton;

    @FXML
    private Pane pane;

    private MainController mainController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        USER_PANEL_CONTROLLER = this;

    }

    public void logIn() {
        login.setText(MAIN_CONTROLLER.getStatus());
        String firstName = MAIN_CONTROLLER.getCustomerList().getCustomerList().get(login.getText()).getFirstName();
        String lastName = MAIN_CONTROLLER.getCustomerList().getCustomerList().get(login.getText()).getLastName();
        name.setText(firstName + " " + lastName);
        accountBalance.setText(MAIN_CONTROLLER.getCustomerList().getCustomerList().get(login.getText()).getAccountBalance() + " PLN");
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        MAIN_CONTROLLER.setCenter("/fxml/LoginPanelView.fxml");
        LOGIN_CONTROLLER.setCustomerList(MAIN_CONTROLLER.getCustomerList());
        LOGIN_CONTROLLER.setMainController(MAIN_CONTROLLER);

    }

    @FXML
    void paymentOnAction(ActionEvent event) throws IOException {
        if(paymentButton.isSelected()) {
            setPane("/fxml/PaymentPanelView.fxml");
        }
    }

    public void setPane(String fxmlPath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(fxmlPath));
        Parent parent = fxmlLoader.load();
        pane.getChildren().add(parent);
    }

    public Label getName() {
        return name;
    }

    public void setName(Label name) {
        this.name = name;
    }

    public Label getLogin() {
        return login;
    }

    public void setLogin(Label login) {
        this.login = login;
    }

    public Button getLogoutButton() {
        return logoutButton;
    }

    public void setLogoutButton(Button logoutButton) {
        this.logoutButton = logoutButton;
    }

    public Label getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Label accountBalance) {
        this.accountBalance = accountBalance;
    }

    public ToggleGroup getToggleGroup() {
        return toggleGroup;
    }

    public void setToggleGroup(ToggleGroup toggleGroup) {
        this.toggleGroup = toggleGroup;
    }

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


}