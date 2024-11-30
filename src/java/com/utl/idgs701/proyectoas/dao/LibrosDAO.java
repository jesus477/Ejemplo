package com.utl.idgs701.proyectoas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.utl.idgs701.proyectoas.bd.ConexionMySql;
import org.utl.idgs701.proyectoas.model.Libros;

public class LibrosDAO {

    public List<Libros> getAll() throws Exception {
        String sql = "SELECT * FROM libros";
        ConexionMySql connMySQL = new ConexionMySql();
        Connection conn = connMySQL.open();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Libros> libros = new ArrayList<>();

        while (rs.next()) {
            Libros libro = fill(rs);
            libros.add(libro);
        }

        rs.close();
        pstmt.close();
        conn.close();

        return libros;
    }

    public Libros getById(int idLibro) throws Exception {
        String sql = "SELECT * FROM libros WHERE idLibro = ?";
        ConexionMySql connMySQL = new ConexionMySql();
        Connection conn = connMySQL.open();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, idLibro);
        ResultSet rs = pstmt.executeQuery();
        Libros libro = null;

        if (rs.next()) {
            libro = fill(rs);
        }

        rs.close();
        pstmt.close();
        conn.close();

        return libro;
    }

    public void insertarLibros(Libros libro) throws Exception {
        String sql = "INSERT INTO libros (nombreLibros, autor, universidad, genero, estatus, archivo, url) VALUES (?, ?, ?, ?, ?, ?, ?)";
        ConexionMySql connMySQL = new ConexionMySql();
        Connection conn = connMySQL.open();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, libro.getTitulo());
        pstmt.setString(2, libro.getAutor());
        pstmt.setString(3, libro.getUniversidad());
        pstmt.setString(4, libro.getGenero());
        pstmt.setString(5, libro.getEstatus());
        pstmt.setString(6, libro.getPdf()); 
        pstmt.setString(7, libro.getUrl());

        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }

    public void updateLibro(Libros libro) throws Exception {
        String sql = "UPDATE libros SET nombreLibros = ?, autor = ?, universidad = ?, genero = ?, estatus = ?, archivo = ?, url = ? WHERE idLibro = ?";
        ConexionMySql connMySQL = new ConexionMySql();
        Connection conn = connMySQL.open();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, libro.getTitulo());
        pstmt.setString(2, libro.getAutor());
        pstmt.setString(3, libro.getUniversidad());
        pstmt.setString(4, libro.getGenero());
        pstmt.setString(5, libro.getEstatus());
        pstmt.setString(6, libro.getPdf()); 
        pstmt.setString(7, libro.getUrl()); 
        pstmt.setInt(8, libro.getIdLibro());

        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }

    private Libros fill(ResultSet rs) throws Exception {
        Libros libro = new Libros();
        libro.setIdLibro(rs.getInt("idLibro"));
        libro.setTitulo(rs.getString("nombreLibros"));
        libro.setAutor(rs.getString("autor"));
        libro.setUniversidad(rs.getString("universidad"));
        libro.setGenero(rs.getString("genero"));
        libro.setEstatus(rs.getString("estatus"));
        libro.setPdf(rs.getString("archivo")); 
        libro.setUrl(rs.getString("url")); 
        return libro;
    }
}
