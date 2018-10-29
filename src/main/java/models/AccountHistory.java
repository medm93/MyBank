package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AccountHistory {
    private ObservableList<AccountRecord> accountRecords;

    public ObservableList<AccountRecord> getAccountRecords() {
        return accountRecords;
    }

    public void addAccountRecord(AccountRecord accountRecord) {
        accountRecords.add(accountRecord);
    }

    public void clear() {
        accountRecords.clear();
    }

    public AccountHistory() {
        this.accountRecords = FXCollections.observableArrayList();
    }

}
