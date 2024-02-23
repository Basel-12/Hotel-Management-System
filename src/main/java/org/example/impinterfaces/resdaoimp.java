package org.example.impinterfaces;
import org.example.dao.DBConnection;
import org.example.entites.Customer;
import org.example.entites.Department;
import org.example.entites.Employee;
import org.example.entites.Reservation;
import org.example.interfaces.querydao;
import org.example.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class resdaoimp implements querydao{
    private List<Reservation> findallExe(){
        Connection con = DBConnection.getConnection();
        if (con == null)
            return null;
        List<Reservation> ll = new ArrayList<>();
        String query = "SELECT  * FROM reservation";
        try {
                PreparedStatement preparedStatement = con.prepareStatement(query);
                ResultSet r = preparedStatement.executeQuery();
                while (r.next())
                    ll.add(new Reservation(r.getInt("id"),r.getInt("custId"),r.getInt("roomId"),r.getDate("startDate"),r.getDate("endDate")));
        }catch (SQLException ex){
            System.out.println(ex.getMessage());

        }
        catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try {
                con.close();
            }catch (SQLException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        return ll;
    }
    @Override
    public void findall() {
        try {
            List<Reservation> l = findallExe();
            for(Reservation r : l)
                System.out.println(r.toString());
        }catch (NullPointerException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Reservation findById(int id) {
        Connection con = DBConnection.getConnection();
        if (con == null)
            return null;
        String query= "SELECT * FROM reservation WHERE id = " + id;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet r = preparedStatement.executeQuery();
            if (r.next())
                return new Reservation(r.getInt("id"),r.getInt("custId"),
                        r.getInt("roomId"),r.getDate("startDate"),
                        r.getDate("endDate"));
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            try {
                con.close();
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        return null;
    }

    @Override
    public void save(Object e) {
        Connection con = DBConnection.getConnection();
        if (con == null)
            return;
        Reservation r = (Reservation) e;
        if (r.getId() > 0){
            String query = "UPDATE reservation SET custId = ? ,roomId = ? , endDate = ? where id = ?";
            try {
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1,r.getCustId());
                preparedStatement.setInt(2,r.getRooomId());
                preparedStatement.setDate(3,Utils.getSqlDate(r.getEndDate()));
                preparedStatement.setInt(4,r.getId());
                preparedStatement.execute();
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
            }finally {
                try {
                    con.close();
                }catch (SQLException ex){
                    System.out.println(ex.getMessage());
                }
            }
        }else{
            String query = "INSERT INTO reservation(custId,roomId,endDate) VALUES (?,?,?)";
            try {
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1,r.getCustId());
                preparedStatement.setInt(2,r.getRooomId());
                preparedStatement.setDate(3,Utils.getSqlDate(r.getEndDate()));
                preparedStatement.execute();
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
            }finally {
                try {
                    con.close();
                }catch (SQLException ex){
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    @Override
    public void deleteById(int id) {
        Connection con = DBConnection.getConnection();
        if (con == null)
            return;
        String query = "DELETE  FROM reservation WHERE id = "+ id;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.execute();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            try {
                con.close();
            }catch (SQLException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }
}
