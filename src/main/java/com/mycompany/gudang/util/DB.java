/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gudang.util;

/**
 *
 * @author FERY
 */

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

    private static final String URL = "jdbc:mysql://localhost:3306/logistik_center";
    private static final String USER = "root"; // sesuaikan
    private static final String PASS = "";     // sesuaikan

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            System.out.println("Koneksi DB gagal: " + e.getMessage());
            return null;
        }
    }
}
