/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.idgs701.proyectoas.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.utl.idgs701.proyectoas.bd.ConexionMySql;
import org.utl.idgs701.proyectoas.model.Usuario;

/**
 *
 * @author jesus
 */
public class LoginController {
    public Usuario login(String nombreUsuario, String contrasenia, String perfil) throws Exception {
        String sql = "SELECT * FROM usuario WHERE nombreUsuario=? AND contrasenia=? AND perfil=? ";
        ConexionMySql connMySQL = new ConexionMySql();
        Connection conn = connMySQL.open();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = null;
        Usuario usu = null;

        pstmt.setString(1, nombreUsuario);
        pstmt.setString(2, contrasenia);
        pstmt.setString(3, perfil);
        rs = pstmt.executeQuery();

        if (rs.next()) {
            usu = fill(rs);
        }

        rs.close();
        pstmt.close();
        conn.close();

        return usu;
    }

    private Usuario fill(ResultSet rs) throws Exception {
        Usuario usu = new Usuario();

        usu.setIdUsuario(rs.getInt("idUsuario"));
        usu.setUsuario(rs.getString("nombreUsuario"));
        usu.setContrasenia(rs.getString("contrasenia"));
        usu.setPerfil(rs.getString("perfil"));

        return usu;
    }
}
