package model;


import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;


@DatabaseTable(tableName = "transactions")
public class Transaction implements BaseModel {

    @DatabaseField(columnName = "ID", generatedId = true)
    private int id;

    @DatabaseField(columnName = "CUSTOMER_ID", foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Customer customer;

    @DatabaseField(columnName = "DATE", dataType = DataType.DATE_STRING, format = "dd-MM-yyyy HH:mm:ss")
    private Date date;

    @NotEmpty
    @DatabaseField(columnName = "TITLE", canBeNull = false)
    private String title;

    @NotNull
    @DatabaseField(columnName = "TRANSACTION_TYPE", canBeNull = false)
    private TransactionType transactionType;

//    @NotNull
//    @Pattern(regexp = "^(\\d+(?:\\.\\d{2})?|\\\\.\\\\d+)&")
    @DecimalMin("0.01")
    @Digits(integer=100, fraction=2)
    @DatabaseField(columnName = "AMOUNT", canBeNull = false)
    private String amount;

    public Transaction() {
    }

    public Transaction(String title, TransactionType transactionType, String amount) {
        this.title = title;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String  getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", customer=" + customer +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", transactionType=" + transactionType +
                ", amount=" + amount +
                '}';
    }
}
