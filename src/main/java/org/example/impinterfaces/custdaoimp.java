package org.example.impinterfaces;
import org.example.dao.DBConnection;
import org.example.entites.Customer;
import org.example.entites.Employee;
import org.example.interfaces.querydao;
import org.example.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class custdaoimp implements querydao{

    private List<Customer> findallExe(){
        Connection con = DBConnection.getConnection();
        if(con == null)
            return null;
        String  query = "SELECT * FROM customer";
        List<Customer> ll = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet r = preparedStatement.executeQuery();
            while (r.next())
            {
                Customer c = new Customer(r.getInt("id"),r.getString("fname"),r.getString("lname"),r.getString("ssn"),r.getString("phone"));
                ll.add(c);
            }
        }
        catch (SQLException ex){
                System.out.println(ex.getMessage());
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        return ll;
    }
    @Override
    public void findall() {
        try {
            List<Customer> xx = findallExe();
            for (Customer c : xx)
                System.out.println(c.toString());
        }catch (NullPointerException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Customer findById(int id) {
        Connection con = DBConnection.getConnection();
        if(con == null)
            return null;
        String  query = "SELECT * FROM customer";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet r = preparedStatement.executeQuery();
            if (r.next())
            {
                return new Customer(r.getInt("id"),r.getString("fname"),r.getString("lname"),r.getString("ssn"),r.getString("phone"));

            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        return null;
    }

    @Override
    public void save(Object e) {
        Customer c = (Customer) e;
        Connection con = DBConnection.getConnection();
        if(con == null)
            return;
        if (c.getId() >0){
            String query = "UPDATE customer SET fname = ? , lname = ?, ssn = ? , phone = ? WHERE id = ?";
            try{
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1,c.getFname());
                preparedStatement.setString(2,c.getLname());
                preparedStatement.setString(3,c.getSsn());
                preparedStatement.setString(4,c.getPhone());
                preparedStatement.setInt(4,c.getId());
                preparedStatement.execute();
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
            finally {
                try {
                    con.close();
                }
                catch (SQLException ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        }
        else{
            String query = "INSERT INTO customer(fname,lname,ssn,phone) VALUES(?,?,?,?)";
            try{
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1,c.getFname());
                preparedStatement.setString(2,c.getLname());
                preparedStatement.setString(3,c.getSsn());
                preparedStatement.setString(4,c.getPhone());
                preparedStatement.execute();
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
            finally {
                try {
                    con.close();
                }
                catch (SQLException ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    @Override
    public void deleteById(int id) {
        Connection con = DBConnection.getConnection();
        if(con == null)
            return;
        String query = "DELETE FROM customer WHERE id = " + id;
        try{
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.execute();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }
}
