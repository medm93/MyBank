package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Customer;
import utils.Dialogs;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import static controller.MainPanelController.MAIN_CONTROLLER;
import static controller.CustomerPanelController.CUSTOMER_PANEL_CONTROLLER;

public class PaymentPanelController implements Initializable {

    private final static String USER_PANEL_VIEW = "/fxml/CustomerPanelView.fxml";

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
            return;
        }
        if (amountOfMoney.getText().contains(".")) {
            if (amountOfMoney.getText().substring(amountOfMoney.getText().indexOf(".")).length() > 3) {
                Dialogs.errorAlert("Błąd!", "Nie właściwy format liczby.");
                return;
            }
        }
        try {
            customerList.get(session).doPayment(title.getText(), amountOfMoney.getText());
        } catch (NumberFormatException exception) {
            Dialogs.errorAlert("Błąd!", "Kwota została odzielona: \",\" zamiast \".\".");
        } finally {
            MAIN_CONTROLLER.setCenter(USER_PANEL_VIEW);
//            CUSTOMER_PANEL_CONTROLLER.logIn();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerList = MAIN_CONTROLLER.getCustomerList().getCustomerList();
        session = MAIN_CONTROLLER.getSession();
    }
}
