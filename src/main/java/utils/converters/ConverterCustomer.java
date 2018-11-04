package utils.converters;

import model.Customer;
import modelFX.CustomerFX;

public class ConverterCustomer {

    public static Customer convertToCustomer(CustomerFX customerFX) {
        Customer customer = new Customer();
        customer.setId(customerFX.getId());
        customer.setFirstName(customerFX.getFirstName());
        customer.setLastName(customerFX.getLastName());
        customer.setEmail(customerFX.getEmail());
        customer.setLogin(customerFX.getLogin());
        customer.setPassword(customerFX.getPassword());
        customer.setAccountBalance(customerFX.getAccountBalance());
        return customer;
    }

    public static CustomerFX convertToCustomerFX(Customer customer) {
        CustomerFX customerFX = new CustomerFX();
        customerFX.setId(customer.getId());
        customerFX.setFirstName(customer.getFirstName());
        customerFX.setLastName(customer.getLastName());
        customerFX.setEmail(customer.getEmail());
        customerFX.setLogin(customer.getLogin());
        customerFX.setPassword(customer.getPassword());
        customerFX.setAccountBalance(customer.getAccountBalance());
        return customerFX;
    }
}
