package org.example.utils;

public class Utils {
    public static java.sql.Date getSqlDate(java.util.Date d){
        return new java.sql.Date(d.getTime());
    }
}
