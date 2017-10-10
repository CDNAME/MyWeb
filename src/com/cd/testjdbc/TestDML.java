package com.cd.testjdbc;

import java.sql.*;

public class TestDML {
    public static void main(String[] args) {
        Statement stmt = null;
        Connection conn = null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Student_info","sa","12345");
            stmt = conn.createStatement();
            String sql = "insert into SC values('201524070101','002',80)";
            stmt.executeUpdate(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
