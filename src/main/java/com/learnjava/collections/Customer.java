package com.learnjava.collections;

/**
 *
 * @author vikmalik
 */
public class Customer implements Comparable<Customer> {
    private final int id;
    private final String name;

    public Customer(int i, String n) {
        this.id = i;
        this.name = n;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name + '}';
    }

    @Override
    public int compareTo(Customer customer) {
        return this.name.compareTo(customer.name);
    }

}
