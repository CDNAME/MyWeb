package com.cd.testjdbc;

import java.sql.*;

public class TestBatch {
    public static void main(String[] args) {
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Student_info","sa","12345");
            stmt = conn.createStatement();
            stmt.addBatch("insert into SC values('201524070103','001',66)");
            stmt.addBatch("insert into SC values('201524070103','003',76)");
            stmt.addBatch("insert into SC values('201524070104','001',86)");
            stmt.executeBatch();

            /* 也使用PreparedStatement做批处理
            PreparedStatement pstmt = conn.prepareStatement("insert into SC values(?,?,?)");
            pstmt.setString(1,"201524070103");
            pstmt.setString(2,"001");
            pstmt.setInt(3,66);
            pstmt.addBatch();

            pstmt.executeBatch();
            */

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                stmt.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
