package org.example.entites;

public class Customer extends Person{
    public Customer() {
    }

    public Customer(int id, String fname, String lname, String ssn, String phone) {
        super(id, fname, lname, ssn, phone);
    }
}
