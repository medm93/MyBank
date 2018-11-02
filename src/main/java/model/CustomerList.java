package model;

import java.util.Map;
import java.util.TreeMap;

public class CustomerList {
    private Map<String, Customer> customerList;

    public Map<String, Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(Map<String, Customer> customerList) {
        this.customerList = customerList;
    }

    public CustomerList() {
        customerList = new TreeMap<>();
        customerList.put(
                "123456",
                new Customer("Mateusz", "Misiak", "tajne", "0.00")
        );

    }
}
