package modelFX;

import javafx.beans.property.*;
import model.TransactionType;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionFX {

    private IntegerProperty id = new SimpleIntegerProperty();
    private ObjectProperty<CustomerFX> customerFX = new SimpleObjectProperty<>();
    private ObjectProperty<Date> date = new SimpleObjectProperty<>();
    private StringProperty title = new SimpleStringProperty();
    private ObjectProperty<TransactionType> transactionType = new SimpleObjectProperty<>();
    private ObjectProperty<BigDecimal> amount = new SimpleObjectProperty<>();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public CustomerFX getCustomerFX() {
        return customerFX.get();
    }

    public ObjectProperty<CustomerFX> customerFXProperty() {
        return customerFX;
    }

    public void setCustomerFX(CustomerFX customerFX) {
        this.customerFX.set(customerFX);
    }

    public Date getDate() {
        return date.get();
    }

    public ObjectProperty<Date> dateProperty() {
        return date;
    }

    public void setDate(Date date) {
        this.date.set(date);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public TransactionType getTransactionType() {
        return transactionType.get();
    }

    public ObjectProperty<TransactionType> transactionTypeProperty() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType.set(transactionType);
    }

    public BigDecimal getAmount() {
        return amount.get();
    }

    public ObjectProperty<BigDecimal> amountProperty() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount.set(amount);
    }
}
