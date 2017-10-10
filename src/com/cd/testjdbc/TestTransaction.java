package com.cd.testjdbc;

import java.sql.*;

public class TestTransaction {
    public static void main(String[] args) {
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Student_info","sa","12345");

            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.addBatch("insert into SC values('201524070101','004',69)");
            stmt.addBatch("insert into SC values('201524070102','004',79)");
            stmt.addBatch("insert into SC values('201524070104','003',99)");
            stmt.executeBatch();
            conn.commit();
            conn.setAutoCommit(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            try{
                if(conn != null) {
                    conn.rollback();
                    conn.setAutoCommit(true);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

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
