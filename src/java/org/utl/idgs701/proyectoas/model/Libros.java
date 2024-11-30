/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.idgs701.proyectoas.model;

/**
 *
 * @author jesus
 */
public class Libros {
    private int idLibro;
    private String titulo;
    private String autor;
    private String universidad;
    private String genero;
    private String estatus;
    private String pdf;
    private String url;

    public Libros(int idLibro, String titulo, String autor, String universidad, String genero, String estatus, String pdf, String url) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.universidad = universidad;
        this.genero = genero;
        this.estatus = estatus;
        this.pdf = pdf;
        this.url = url;
    }
    
    public Libros() {
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }
    
    
    
}
