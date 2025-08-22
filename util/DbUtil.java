/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.axora.communityskillexchange.util;

import java.sql.*;

public class DbUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/skillshare?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "admin";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
