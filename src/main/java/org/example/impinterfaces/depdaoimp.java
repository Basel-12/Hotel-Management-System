package org.example.impinterfaces;
import org.example.dao.DBConnection;
import org.example.entites.Customer;
import org.example.entites.Department;
import org.example.entites.Employee;
import org.example.interfaces.querydao;
import org.example.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class depdaoimp implements querydao{

    private List<Department> findallExe(){
        Connection con = DBConnection.getConnection();
        if(con == null)
            return null;
        String  query = "SELECT * FROM department";
        List<Department> ll = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet r = preparedStatement.executeQuery();
            while (r.next())
            {
                Department c = new Department(r.getInt("id"),r.getString("name"));
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
            List<Department> l = findallExe();
            for (Department x : l)
                System.out.println(x.toString());
        }catch (NullPointerException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Department findById(int id) {
        Connection con = DBConnection.getConnection();
        if(con == null)
            return null;
        String query = "SELECT *  FROM department WHERE id = " + id;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet r = preparedStatement.executeQuery();
            if(r.next()){
                return new Department(r.getInt("id"),r.getString("name"));
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
        Department ee = (Department) e;
        if(ee.getId() > 0){
            String query = "UPDATE department SET name = ?  WHERE id = ?";
            try {
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, ee.getName());
                preparedStatement.setInt(2, ee.getId());
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
            String query = "INSERT INTO department(name) VALUES(?)";
            try {
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, ee.getName());
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
        String query = "DELETE FROM department WHERE id = " +id;
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
