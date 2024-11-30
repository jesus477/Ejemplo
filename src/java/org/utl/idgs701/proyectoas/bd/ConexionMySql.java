/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.idgs701.proyectoas.bd;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author jesus
 */
public class ConexionMySql {

    public Connection open() throws Exception {
        String user = "root";
        String password = "root";
        String url = "jdbc:mysql://127.0.0.1:3306/login?"
                + "useSSL=false&"
                + "allowPublicKeyRetrieval=true&"
                + "useUnicode=true&"
                + "characterEncoding=utf-8";

        Connection conn = null;

        //Primero registramos el Driver JDBC de MySQL: 
        Class.forName("com.mysql.cj.jdbc.Driver");

        //Abrimos una conexion con MySQL:
        conn = DriverManager.getConnection(url, user, password);

        return conn;
    }
}
