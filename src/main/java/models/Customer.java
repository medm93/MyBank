package models;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Customer {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String accountBalance;
    private AccountHistory accountHistory;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }

    public AccountHistory getAccountHistory() {
        return accountHistory;
    }

    public void setAccountHistory(AccountHistory accountHistory) {
        this.accountHistory = accountHistory;
    }

    public Customer(String firstName, String lastName, String login, String password, String accountBalance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.accountBalance = accountBalance;
        this.accountHistory = new AccountHistory();
    }

    public void doPayment(String title, String value) throws NumberFormatException {
        BigDecimal currentAccountBalance = new BigDecimal(accountBalance).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal payment = new BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal newAccountBalance = currentAccountBalance.add(payment).setScale(2, RoundingMode.HALF_EVEN);
        accountBalance = newAccountBalance.toString();
        AccountRecord accountRecord = new AccountRecord();
        accountHistory.addAccountRecord(accountRecord);
        accountRecord.addEntry(title, value);
    }

    public void doWithdrawal(String title, String value) throws NumberFormatException {
        BigDecimal currentAccountBalance = new BigDecimal(accountBalance).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal payment = new BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal newAccountBalance = currentAccountBalance.subtract(payment).setScale(2, RoundingMode.HALF_EVEN);
        accountBalance = newAccountBalance.toString();
        AccountRecord accountRecord = new AccountRecord();
        accountHistory.addAccountRecord(accountRecord);
        accountRecord.addEntry(title, "-" + value);
    }
}
