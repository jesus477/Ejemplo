/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utl.idgs701.proyectoas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.utl.idgs701.proyectoas.bd.ConexionMySql;
import org.utl.idgs701.proyectoas.model.Libros;
import org.utl.idgs701.proyectoas.model.Usuario;

/**
 *
 * @author jesus
 */
public class UsuarioDAO {

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

    public List<Usuario> getAll() throws Exception {
        String sql = "SELECT * FROM usuario";
        ConexionMySql connMySQL = new ConexionMySql();
        Connection conn = connMySQL.open();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        //import java.util.List
        ArrayList<Usuario> usuario = new ArrayList<>();

        while (rs.next()) {
            Usuario usu = fill(rs);
            usuario.add(usu);
        }

        rs.close();
        pstmt.close();
        conn.close();

        return usuario;
    }

    public void insert(Usuario usuario) throws Exception {
        String sql = "INSERT INTO usuario (nombreUsuario, contrasenia, perfil) VALUES (?, ?, ?)";
        ConexionMySql connMySQL = new ConexionMySql();
        Connection conn = connMySQL.open();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, usuario.getUsuario());
        pstmt.setString(2, usuario.getContrasenia());
        pstmt.setString(3, usuario.getPerfil());

        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }
    
    public void updateUsuario(Usuario usuario) throws Exception {
        String sql = "UPDATE usuario SET nombreUsuario = ?, contrasenia = ?, perfil = ? WHERE idUsuario = ?";
        ConexionMySql connMySQL = new ConexionMySql();
        Connection conn = connMySQL.open();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, usuario.getUsuario());
        pstmt.setString(2, usuario.getContrasenia());
        pstmt.setString(3, usuario.getPerfil());

        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }
}
