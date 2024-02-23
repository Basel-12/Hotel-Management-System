package org.example;
import org.example.interfaces.*;
import org.example.entites.*;
import org.example.impinterfaces.*;
import org.example.dao.*;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        querydao  q = new employeedaoimp();
        querydao c = new custdaoimp();
        querydao d = new depdaoimp();
        querydao r = new roomdaoimp();
        querydao res = new resdaoimp();
        Department dd = new Department(0,"waiter");
        Employee e = new Employee(0,"ahmed","mohamed","3031101","0114","dlakjaad",500.4,1);
        Room rr = new Room(0,"single",true,500);
        Customer cc = new Customer(0,"mohamed","fathy","30300","0114");
        Reservation rrr = new Reservation(0,1,1,new Date(),new Date());
        res.save(rrr);

    }
}