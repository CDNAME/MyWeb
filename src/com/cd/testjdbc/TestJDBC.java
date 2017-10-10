package com.cd.testjdbc;

import java.sql.*;

public class TestJDBC {
    public static void main(String[] args){
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //new com.microsoft.sqlserver.jdbc.SQLServerDriver();
            System.out.println("数据库驱动加载成功!");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Student_info", "sa", "12345");
            System.out.println("数据库连接成功！");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from Student");
            while (rs.next()) {
                System.out.println(rs.getString("Snum") + "\t" + rs.getString("Sname"));
            }
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
