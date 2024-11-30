/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.idgs701.proyectoas.core;

import com.utl.idgs701.proyectoas.comandos.UsuarioCQRS;
import com.utl.idgs701.proyectoas.dao.UsuarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import org.utl.idgs701.proyectoas.bd.ConexionMySql;
import org.utl.idgs701.proyectoas.model.Libros;
import org.utl.idgs701.proyectoas.model.Usuario;

/**
 *
 * @author jesus
 */
public class UsuarioController {

    private UsuarioDAO usuarioDAO;
    private UsuarioCQRS usuarioCQRS;

    public UsuarioController() {
        usuarioDAO = new UsuarioDAO();
        usuarioCQRS = new UsuarioCQRS();
    }

    public List<Usuario> getAll() throws Exception {
        return usuarioDAO.getAll();
    }

    public void insert(Usuario usuario) throws Exception {
        usuarioCQRS.insert(usuario);
    }

    public void actualizarUsuario(Usuario usuario) throws Exception {
        usuarioCQRS.updateUsuario(usuario); 
    }
    

}
