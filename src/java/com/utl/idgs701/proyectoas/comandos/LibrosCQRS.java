package com.utl.idgs701.proyectoas.comandos;

import com.utl.idgs701.proyectoas.dao.LibrosDAO;
import java.util.List;
import org.utl.idgs701.proyectoas.model.Libros;

public class LibrosCQRS {

    private LibrosDAO librosDAO;

    public LibrosCQRS() {
        librosDAO = new LibrosDAO();
    }

    public void insertarLibro(Libros libro) throws Exception {
        validaciones(libro); 
        librosDAO.insertarLibros(libro); 
    }

    public List<Libros> getAll() throws Exception {
        return librosDAO.getAll();
    }

    public Libros getById(int idLibro) throws Exception {
        return librosDAO.getById(idLibro);
    }
    
    public void updateLibro(Libros libro) throws Exception {
        librosDAO.updateLibro(libro); 
    }

    private void validaciones(Libros libro) throws Exception {
        if (libro.getTitulo() == null || libro.getTitulo().trim().isEmpty()) {
            throw new Exception("Ingrese el nombre del libro");
        }
        if (libro.getTitulo().length() < 5 || libro.getTitulo().length() > 100) {
            throw new Exception("Ingrese un nombre del libro valido mayor que 5 letras y menor que 100");
        }

        if (libro.getGenero() == null || libro.getGenero().trim().isEmpty()) {
            throw new Exception("Ingrese una categoria");
        }
        if (libro.getGenero().length() < 5 || libro.getGenero().length() > 30) {
            throw new Exception("Ingrese una categoria valida mayor que 5 letras y menor que 100");
        }
    }
}
