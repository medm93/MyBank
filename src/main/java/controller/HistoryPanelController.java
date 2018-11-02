package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AccountRecord;
import model.Customer;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import static controller.MainPanelController.MAIN_CONTROLLER;

public class HistoryPanelController implements Initializable {
    @FXML
    private TableView<AccountRecord> accountHistoryTable;

    private Map<String, Customer> customerList;
    private String session;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerList = MAIN_CONTROLLER.getCustomerList().getCustomerList();
        session = MAIN_CONTROLLER.getSession();
        TableColumn<AccountRecord, AccountRecord> dataColumn = new TableColumn<>("data");
        TableColumn<AccountRecord, String> timeColumn = new TableColumn<>("czas");
        TableColumn<AccountRecord, String> titleColumn = new TableColumn<>("tytuł");
        TableColumn<AccountRecord, String> amountOfMoneyColumn = new TableColumn<>("kwota");
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        amountOfMoneyColumn.setCellValueFactory(new PropertyValueFactory<>("amountOfMoney"));
        accountHistoryTable.getColumns().add(dataColumn);
        accountHistoryTable.getColumns().add(timeColumn);
        accountHistoryTable.getColumns().add(titleColumn);
        accountHistoryTable.getColumns().add(amountOfMoneyColumn);
        //accountHistoryTable.setItems(customerList.get(session).getAccountHistory().getAccountRecords());
    }
}