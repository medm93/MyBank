package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.TransactionType;
import modelFX.CustomerModel;
import modelFX.TransactionFX;
import modelFX.TransactionModel;
import utils.ValidatorManager;
import utils.converters.ConverterCustomer;
import utils.exception.ApplicationException;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static controller.LoginPanelController.LOGIN_PANEL_CONTROLLER;

public class CustomerPanelController implements Initializable {

    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String DOT = ".";
    private static final String COMMA = ",";
    private static final String ONE_ZERO = "0";
    private static final String TWO_ZEROS = "00";
    private static final String DOT_AND_TWO_ZEROS = ".00";

    @FXML
    private Label firstName;

    @FXML
    private Label lastName;

    @FXML
    private Label login;

    @FXML
    private Button logoutButton;

    @FXML
    private Label accountBalance;

    @FXML
    private TextField title;

    @FXML
    private Label titleError;

    @FXML
    private ComboBox<TransactionType> transactionType;

    @FXML
    private Label transactionTypeError;

    @FXML
    private TextField amount;

    @FXML
    private Label amountError;

    @FXML
    private Button send;

    @FXML
    private TableView<TransactionFX> accountHistory;

    private TransactionModel transactionModel;
    private CustomerModel customerModel;

    @FXML
    void logOut(ActionEvent event) {

    }

    @FXML
    void send(ActionEvent event) {
        if (ValidatorManager.validationOfTransactionData(this)) return;
        switch (transactionType.getSelectionModel().getSelectedItem()) {
            case PAYMENT: {
                customerModel.getCustomerFXObjectProperty().setAccountBalance(payment());
                try {
                    transactionModel.saveTransactionInDatabase(
                            ConverterCustomer.convertToCustomer(customerModel.getCustomerFXObjectProperty()),
                            title.getText(),
                            transactionType.getSelectionModel().getSelectedItem(),
                            amountFormat(PLUS, amount.getText())
                    );
                } catch (ApplicationException e) {
                    e.printStackTrace();
                }
                break;
            }
            case WITHDRAWAL: {
                customerModel.getCustomerFXObjectProperty().setAccountBalance(withdrawal());
                try {
                    transactionModel.saveTransactionInDatabase(
                            ConverterCustomer.convertToCustomer(customerModel.getCustomerFXObjectProperty()),
                            title.getText(),
                            transactionType.getSelectionModel().getSelectedItem(),
                            amountFormat(MINUS, amount.getText())
                    );
                } catch (ApplicationException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        try {
            customerModel.updateAccountBalanceInDatabase();
        } catch (ApplicationException | SQLException e) {
            e.printStackTrace();
        }
//        title.setText(null);
//        transactionType.getSelectionModel().select(TransactionType.PAYMENT);
//        amount.setText(null);
    }

    private String amountFormat(String mathematicalSign, String amount) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(mathematicalSign);
        if (!amount.contains(DOT)) {
            stringBuilder.append(DOT_AND_TWO_ZEROS);
        } else {
            int length = amount.substring(amount.indexOf(DOT)).length();
            switch (length) {
                case 1: {
                    stringBuilder.append(TWO_ZEROS);
                    break;
                }
                case 2: {
                    stringBuilder.append(ONE_ZERO);
                    break;
                }
                default: {
                    stringBuilder.append(amount);
                }
            }
        }

        return stringBuilder.toString();

    }

    private String payment() {
        return new BigDecimal(customerModel.getCustomerFXObjectProperty().getAccountBalance())
                .add(new BigDecimal(amount.getText())).toString();
    }

    private String withdrawal() {
        return new BigDecimal(customerModel.getCustomerFXObjectProperty().getAccountBalance())
                .subtract(new BigDecimal(amount.getText())).toString();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        transactionModel = new TransactionModel();
        customerModel = LOGIN_PANEL_CONTROLLER.getCustomerModel();
        initializeCustomer();
        initializeTransaction();
        initializeAccountHistory();
        accountHistory.setItems(transactionModel.getTransactions());
        title.textProperty().addListener(observable -> titleError.setText(null));
        amount.textProperty().addListener(observable -> amountError.setText(null));
    }

    private void initializeCustomer() {
        firstName.textProperty().bindBidirectional(customerModel.getCustomerFXObjectProperty().firstNameProperty());
        lastName.textProperty().bindBidirectional(customerModel.getCustomerFXObjectProperty().lastNameProperty());
        login.textProperty().bindBidirectional(customerModel.getCustomerFXObjectProperty().loginProperty());
        accountBalance.textProperty().bindBidirectional(customerModel.getCustomerFXObjectProperty().accountBalanceProperty());
    }

    private void initializeTransaction() {
        transactionType.getItems().setAll(TransactionType.values());
        transactionType.getSelectionModel().select(TransactionType.PAYMENT);
    }

    private void initializeAccountHistory() {
        TableColumn<TransactionFX, String> dataColumn = new TableColumn<>("DATA");
        TableColumn<TransactionFX, String> titleColumn = new TableColumn<>("TYTUŁ");
        TableColumn<TransactionFX, TransactionType> transactionTypeColumn = new TableColumn<>("TYP TRANSAKCJI");
        TableColumn<TransactionFX, String> amountOfMoneyColumn = new TableColumn<>("KWOTA");
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        transactionTypeColumn.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        amountOfMoneyColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        accountHistory.getColumns().add(dataColumn);
        accountHistory.getColumns().add(transactionTypeColumn);
        accountHistory.getColumns().add(titleColumn);
        accountHistory.getColumns().add(amountOfMoneyColumn);
        transactionModel.init(LOGIN_PANEL_CONTROLLER.getCustomerModel().getCustomerFXObjectProperty());
    }

    public Label getFirstName() {
        return firstName;
    }

    public void setFirstName(Label firstName) {
        this.firstName = firstName;
    }

    public Label getLastName() {
        return lastName;
    }

    public void setLastName(Label lastName) {
        this.lastName = lastName;
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

    public TextField getTitle() {
        return title;
    }

    public void setTitle(TextField title) {
        this.title = title;
    }

    public Label getTitleError() {
        return titleError;
    }

    public void setTitleError(Label titleError) {
        this.titleError = titleError;
    }

    public ComboBox<TransactionType> getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(ComboBox<TransactionType> transactionType) {
        this.transactionType = transactionType;
    }

    public Label getTransactionTypeError() {
        return transactionTypeError;
    }

    public void setTransactionTypeError(Label transactionTypeError) {
        this.transactionTypeError = transactionTypeError;
    }

    public TextField getAmount() {
        return amount;
    }

    public void setAmount(TextField amount) {
        this.amount = amount;
    }

    public Label getAmountError() {
        return amountError;
    }

    public void setAmountError(Label amountError) {
        this.amountError = amountError;
    }

    public Button getSend() {
        return send;
    }

    public void setSend(Button send) {
        this.send = send;
    }

    public TableView<TransactionFX> getAccountHistory() {
        return accountHistory;
    }

    public void setAccountHistory(TableView<TransactionFX> accountHistory) {
        this.accountHistory = accountHistory;
    }

    public TransactionModel getTransactionModel() {
        return transactionModel;
    }

    public void setTransactionModel(TransactionModel transactionModel) {
        this.transactionModel = transactionModel;
    }
}
