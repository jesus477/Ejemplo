/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utl.idgs701.proyectoas.comandos;

import com.utl.idgs701.proyectoas.dao.UsuarioDAO;
import java.util.List;
import org.utl.idgs701.proyectoas.model.Libros;
import org.utl.idgs701.proyectoas.model.Usuario;

/**
 *
 * @author jesus
 */
public class UsuarioCQRS {
    private UsuarioDAO usuarioDAO;

    public UsuarioCQRS() {
        usuarioDAO = new UsuarioDAO();
    }

    public void insert(Usuario usuario) throws Exception {
        usuarioDAO.insert(usuario); 
    }

    public List<Usuario> getAll() throws Exception {
        return usuarioDAO.getAll();
    }
    
    public void updateUsuario(Usuario usuario) throws Exception {
        usuarioDAO.updateUsuario(usuario); 
    }
}
