package com.cd.testjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestInput {

    public static void main(String[] args) {
        if(args.length != 3) {
            System.out.println("Parameter Error！Please Input Again!");
            System.exit(-1);
        }

        int grade = 0;

        try{
            grade = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Parameter Error! Please Input Correct Number Format!");
            System.exit(-1);
        }

        String snum = args[0];
        String cnum = args[1];

        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Student_info","sa","12345");
            stmt = conn.createStatement();
            String sql = "insert into SC values('"+ snum +"','" + cnum + "'," + grade + ")";
            System.out.println(sql);
            stmt.executeUpdate(sql);

        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try{
                if(stmt != null) stmt.close();
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
