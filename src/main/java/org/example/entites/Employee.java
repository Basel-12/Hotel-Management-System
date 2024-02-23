package org.example.entites;

public class Employee extends Person{
    private String email;
    private double salary;
    private int depId;

    public Employee() {
    }

    public Employee(int id, String fname, String lname, String ssn, String phone, String email, double salary, int depId) {
        super(id, fname, lname, ssn, phone);
        this.email = email;
        this.salary = salary;
        this.depId = depId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    @Override
    public String toString() {
        return super.toString() + email + " " + salary + " " + depId;
    }
}
