package com.cd.testjdbc;

import java.sql.*;

public class TestProc {
    public static void main(String[] args) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Student_info","sa","12345");
            cstmt = conn.prepareCall("{call average_grade(?,?)}");
            cstmt.registerOutParameter(2, Types.SMALLINT);
            cstmt.setString(1,"C语言程序设计");
            cstmt.execute();
            System.out.println(cstmt.getInt(2));
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(cstmt != null) {
                    cstmt.close();
                }
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
