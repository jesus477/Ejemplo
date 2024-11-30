/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utl.idgs701.proyectoas.mvvm;

import org.utl.idgs701.proyectoas.model.Libros;


public class LibrosViewModel {
    
    private int idLibro;
    private String titulo;
    private String autor;
    private String universidad;
    private String genero;
    private String estatus;
    private String pdf;
    
    public LibrosViewModel(Libros libro) {
        this.idLibro = libro.getIdLibro();
        this.titulo = libro.getTitulo();
        this.autor = libro.getAutor();
        this.universidad = libro.getUniversidad();
        this.genero = libro.getGenero();
        this.estatus= libro.getEstatus();
        this.pdf = libro.getPdf();
    }

    public LibrosViewModel() {}

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

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
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

    
}