package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CustomerList {
    private Map<String, Customer> customerList;
    //List<Customer> customerList;

    public Map<String, Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(Map<String, Customer> customerList) {
        this.customerList = customerList;
    }


//    public List<Customer> getCustomerList() {
//        return customerList;
//    }
//
//    public void setCustomerList(List<Customer> customerList) {
//        this.customerList = customerList;
//    }

    public CustomerList() {
//        customerList = new ArrayList<Customer>();
//        customerList.add(new Customer("Mateusz", "Misiak", 123456, "tajne", 0));
        customerList = new TreeMap<String, Customer>();
        customerList.put(
                "123456",
                new Customer("Mateusz", "Misiak", "123456", "tajne", 0)
        );

    }

    @Override
    public String toString() {
        return "CustomerList{" +
                "customerList=" + customerList +
                '}';
    }
}
