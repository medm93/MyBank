package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import models.Customer;
import utils.Dialogs;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import static controllers.MainController.MAIN_CONTROLLER;
import static controllers.UserPanelController.USER_PANEL_CONTROLLER;

public class PaymentPanelController implements Initializable {

    @FXML
    private TextField title;

    @FXML
    private TextField amountOfMoney;

    @FXML
    private Button paymentButton;

    private Map<String, Customer> customerList;
    private String session;

    @FXML
    void payment(ActionEvent event) throws IOException {
        if (title.getText().isEmpty() || amountOfMoney.getText().isEmpty()) {
            Dialogs.errorAlert("Błąd!", "Wszystkie pola muszą być uzupełnione.");
        } else {
            try {
                customerList.get(session).doPayment(amountOfMoney.getText());
            } catch (NumberFormatException exception) {
                Dialogs.errorAlert("Błąd!", "Kwota została odzielona: \",\" zamiast \".\".");
            } finally {
                MAIN_CONTROLLER.setCenter("/fxml/UserPanelView.fxml");
                USER_PANEL_CONTROLLER.logIn();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerList = MAIN_CONTROLLER.getCustomerList().getCustomerList();
        session = MAIN_CONTROLLER.getStatus();
    }
}
