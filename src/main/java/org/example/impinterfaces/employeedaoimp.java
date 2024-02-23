package org.example.impinterfaces;
import org.example.dao.DBConnection;
import org.example.entites.Employee;
import org.example.interfaces.querydao;
import org.example.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class employeedaoimp implements querydao {
    private List<Employee> findallExe() {
        Connection con = DBConnection.getConnection();
        if(con == null)
            return null;
        String query = "SELECT * FROM employee";
        List<Employee> ll = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
           ResultSet r =  preparedStatement.executeQuery();
           while (r.next()){
               Employee e = new Employee(r.getInt("id"),r.getString("fname")
                       ,r.getString("lname"),r.getString("ssn"),r.getString("phone")
                       ,r.getString("email"),r.getDouble("salary"),r.getInt("depId"));
               ll.add(e);
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
    public void findall(){
        try {
            List<Employee> l = findallExe();
            for (Employee x : l)
                System.out.println(x.toString());
        }catch (NullPointerException ex){
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public Employee findById(int id) {
        Connection con = DBConnection.getConnection();
        if(con == null)
            return null;
        String query = "SELECT *  FROM employee WHERE id = " + id;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet r = preparedStatement.executeQuery();
            if(r.next()){
                return new Employee(r.getInt("id"),r.getString("fname")
                        ,r.getString("lname"),r.getString("ssn"),r.getString("phone")
                        ,r.getString("email"),r.getDouble("salary"),r.getInt("depId"));
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
        Connection con = DBConnection.getConnection();
        if(con == null)
            return;
        Employee ee = (Employee) e;
        if(ee.getId() > 0){
            String query = "UPDATE employee SET fname = ? , lname = ? , ssn = ?, phone = ? ,email = ?,salary = ? , depId = ? WHERE id = ?";
            try {
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, ee.getFname());
                preparedStatement.setString(2, ee.getLname());
                preparedStatement.setString(3, ee.getSsn());
                preparedStatement.setString(4, ee.getPhone());
                preparedStatement.setString(5, ee.getEmail());
                preparedStatement.setDouble(6, ee.getSalary());
                preparedStatement.setInt(7, ee.getDepId());
                preparedStatement.setInt(8, ee.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } finally {
                try {
                    con.close();
                }
                catch (SQLException ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        }
        else {
            String query = "INSERT INTO employee(fname,lname,ssn,phone,email,salary,depId) VALUES(?,?,?,?,?,?,?)";
            try {
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, ee.getFname());
                preparedStatement.setString(2, ee.getLname());
                preparedStatement.setString(3, ee.getSsn());
                preparedStatement.setString(4, ee.getPhone());
                preparedStatement.setString(5, ee.getEmail());
                preparedStatement.setDouble(6, ee.getSalary());
                preparedStatement.setInt(7, ee.getDepId());
                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } finally {
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
        String query = "DELETE FROM employee WHERE id = " +id;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.executeUpdate();
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
