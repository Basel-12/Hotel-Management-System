package org.example.impinterfaces;
import org.example.dao.DBConnection;
import org.example.entites.Customer;
import org.example.entites.Employee;
import org.example.entites.Room;
import org.example.interfaces.querydao;
import org.example.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class roomdaoimp implements querydao {
    private List<Room> findallExe(){
        Connection con = DBConnection.getConnection();
        if (con == null)
            return null;
        List<Room> ll = new ArrayList<>();
        String query = "SELECT * FROM room";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet r = preparedStatement.executeQuery();
            while (r.next()){
                Room rr = new Room(r.getInt("id"),r.getString("type"),r.getBoolean("available"),r.getInt("price"));
                ll.add(rr);
            }
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
        return ll;
    }
    @Override
    public void findall() {
        try {
            List<Room> xx = findallExe();
            for (Room r : xx)
                System.out.println(r.toString());
        }catch (NullPointerException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Room findById(int id) {
        Connection con = DBConnection.getConnection();
        if (con == null)
            return null;
        String query = "SELECT * FROM room WHERE id ="+id;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet r = preparedStatement.executeQuery();
            if (r.next())
                return new Room(r.getInt("id"),r.getString("type"),r.getBoolean("available"),r.getInt("price"));
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally {
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
        Room r = (Room) e;
        if (r.getId() > 0)
        {
            String query = "UPDATE room SET type = ? , available = ? , price = ? WHERE id = ?";
            try{
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1,r.getType());
                preparedStatement.setBoolean(2,r.isAvailable());
                preparedStatement.setInt(3,r.getPrice());
                preparedStatement.setInt(4,r.getId());
                preparedStatement.execute();
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
            finally {
                try {
                    con.close();
                }
                catch (SQLException ex){
                    System.out.println(ex.getMessage());
                }
            }
        }
        else{
            String query = "INSERT INTO room(type,available,price) VALUES (?,?,?)";
            try{
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1,r.getType());
                preparedStatement.setBoolean(2,r.isAvailable());
                preparedStatement.setInt(3,r.getPrice());
                preparedStatement.execute();
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
            finally {
                try {
                    con.close();
                }
                catch (SQLException ex){
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
        String query = "DELETE FROM room WHERE id = " + id;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.execute();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally {
            try {
                con.close();
            }catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
