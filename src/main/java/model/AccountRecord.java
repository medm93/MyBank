package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.LocalTime;

public class AccountRecord {
    private ObjectProperty<LocalDate> date;
    private ObjectProperty<LocalTime> time;
    private StringProperty title;
    private StringProperty amountOfMoney;

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.setValue(date);
    }

    public LocalTime getTime() {
        return time.get();
    }

    public ObjectProperty<LocalTime> timeProperty() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time.setValue(time);
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

    public String getAmountOfMoney() {
        return amountOfMoney.get();
    }

    public StringProperty amountOfMoneyProperty() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(String amountOfMoney) {
        this.amountOfMoney.set(amountOfMoney);
    }

    public AccountRecord() {
        this.date = new SimpleObjectProperty<>();
        this.time = new SimpleObjectProperty<>();
        this.title = new SimpleStringProperty();
        this.amountOfMoney =  new SimpleStringProperty();
    }

    public void addEntry(String title, String amountOfMoney) {
        setDate(LocalDate.now());
        setTime(LocalTime.now());
        setTitle(title);
        setAmountOfMoney(amountOfMoney);
    }

}
