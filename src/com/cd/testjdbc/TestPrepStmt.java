package com.cd.testjdbc;

import java.sql.*;

public class TestPrepStmt {
    public static void main(String[] args) {
        if(args.length != 3){
            System.out.println("Error!");
            System.exit(-1);
        }
        int grade = 0;
        try {
            grade = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Error!");
            System.exit(-1);
        }
        String snum = args[0];
        String cnum = args[1];

        PreparedStatement pstmt = null;
        Connection conn = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn =  DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Student_info","sa","12345");
            pstmt = conn.prepareStatement("insert into SC values(?,?,?)");
            pstmt.setString(1, snum);
            pstmt.setString(2, cnum);
            pstmt.setInt(3, grade);
            pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                if(pstmt != null) {
                    pstmt.close();
                }
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
