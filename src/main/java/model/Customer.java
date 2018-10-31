package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.math.RoundingMode;

@DatabaseTable(tableName = "customers")
public class Customer implements BaseModel{

    @DatabaseField(columnName = "ID", generatedId = true)
    private int id;

    @DatabaseField(columnName = "FIRST_NAME", canBeNull = false)
    private String firstName;

    @DatabaseField(columnName = "LAST_NAME", canBeNull = false)
    private String lastName;

    @DatabaseField(columnName = "EMAIL", canBeNull = false, unique = true)
    private String email;

    @DatabaseField(columnName = "LOGIN", unique = true)
    private String login;

    @DatabaseField(columnName = "PASSWORD", canBeNull = false)
    private String password;

    @DatabaseField(columnName = "ACCOUNT_BALANCE", defaultValue = "0.00")
    private String accountBalance;

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Customer(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Customer(String firstName, String lastName, String login, String password, String accountBalance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.accountBalance = accountBalance;
        //this.accountHistory = new AccountHistory();
    }

    public void doPayment(String title, String value) throws NumberFormatException {
        BigDecimal currentAccountBalance = new BigDecimal(accountBalance).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal payment = new BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal newAccountBalance = currentAccountBalance.add(payment).setScale(2, RoundingMode.HALF_EVEN);
        accountBalance = newAccountBalance.toString();
        AccountRecord accountRecord = new AccountRecord();
        //accountHistory.addAccountRecord(accountRecord);
        accountRecord.addEntry(title, value);
    }

    public void doWithdrawal(String title, String value) throws NumberFormatException {
        BigDecimal currentAccountBalance = new BigDecimal(accountBalance).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal payment = new BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal newAccountBalance = currentAccountBalance.subtract(payment).setScale(2, RoundingMode.HALF_EVEN);
        accountBalance = newAccountBalance.toString();
        AccountRecord accountRecord = new AccountRecord();
        //accountHistory.addAccountRecord(accountRecord);
        accountRecord.addEntry(title, "-" + value);
    }
}
