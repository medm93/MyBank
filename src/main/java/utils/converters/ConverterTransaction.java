package utils.converters;

import model.Transaction;
import modelFX.TransactionFX;

public class ConverterTransaction {

    public static Transaction convertToTransaction(TransactionFX transactionFX) {
        Transaction transaction = new Transaction();
        transaction.setId(transactionFX.getId());
        transaction.setCustomer(ConverterCustomer.convertToCustomer(transactionFX.getCustomerFX()));
        transaction.setDate(transactionFX.getDate());
        transaction.setTitle(transactionFX.getTitle());
        transaction.setTransactionType(transactionFX.getTransactionType());
        transaction.setAmount(transactionFX.getAmount());
        return transaction;
    }

    public static TransactionFX convertToTransactionFX(Transaction transaction) {
        TransactionFX transactionFX = new TransactionFX();
        transactionFX.setId(transaction.getId());
        transactionFX.setCustomerFX(ConverterCustomer.convertToCustomerFX(transaction.getCustomer()));
        transactionFX.setDate(transaction.getDate());
        transactionFX.setTitle(transaction.getTitle());
        transactionFX.setTransactionType(transaction.getTransactionType());
        transactionFX.setAmount(transaction.getAmount());
        return transactionFX;
    }
}
