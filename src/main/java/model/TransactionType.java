package model;

import utils.FXMLUtils;

public enum TransactionType {
    PAYMENT(FXMLUtils.getResourceBundle().getString("enum.transactionType.payment")),
    WITHDRAWAL(FXMLUtils.getResourceBundle().getString("enum.transactionType.withdrawal"));

    private String description;

    TransactionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
