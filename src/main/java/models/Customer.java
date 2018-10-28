package models;

import java.math.BigDecimal;

public class Customer {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String accountBalance;
    //private double accountBalance;

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

    public Customer(String firstName, String lastName, String login, String password, String accountBalance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.accountBalance = accountBalance;
    }

    public void doPayment(String value) throws NumberFormatException {
        BigDecimal currentAccountBalance = new BigDecimal(accountBalance);
        BigDecimal payment = new BigDecimal(value);
        BigDecimal newAccountBalance = currentAccountBalance.add(payment);
        accountBalance = newAccountBalance.toString();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login=" + login +
                ", password='" + password + '\'' +
                ", accountBalance=" + accountBalance +
                '}';
    }
}
