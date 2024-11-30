/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.idgs701.proyectoas.rest;

import com.google.gson.Gson;
import com.utl.idgs701.proyectoas.mvvm.LibrosViewModel;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.utl.idgs701.proyectoas.core.UsuarioController;
import org.utl.idgs701.proyectoas.core.LibrosController;
import org.utl.idgs701.proyectoas.model.Libros;
import org.utl.idgs701.proyectoas.model.Usuario;

/**
 *
 * @author jesus
 */
@Path("libros")
public class RESTLibros {

    @Path("getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLibros() {
        String out;
        LibrosController cl = new LibrosController();
        Gson gson = new Gson();
        try {
            List<Libros> usuarios = cl.getAllLibros();
            out = gson.toJson(usuarios);
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception\":\"" + e.toString().replaceAll("\"", "") + "\"}";
        }
        return Response.ok(out).build();
    }

    @Path("getAllViewModel")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllViewModel() {
        String out;
        LibrosController controller = new LibrosController();
        Gson gson = new Gson();
        try {
            List<LibrosViewModel> combinedViewModels = controller.getCombinedLibrosViewModel();
            out = gson.toJson(combinedViewModels);
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception\":\"" + e.toString().replaceAll("\"", "") + "\"}";
        }
        return Response.ok(out).build();
    }

    @Path("save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveLibro(@FormParam("datosLibro") @DefaultValue("") String datosLibro) {
        LibrosController controladorLibro = new LibrosController();
        Gson gson = new Gson();
        String out = null;

        try {
            Libros libro = gson.fromJson(datosLibro, Libros.class);
            if (libro == null) {
                throw new IllegalArgumentException("Datos inválidos para el libro");
            } else {
                controladorLibro.agregarLibro(libro);
            }

            out = "{\"result\":\"Libro registrado correctamente.\"}";
        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\":\"" + ex.toString().replaceAll("\"", "") + "\"}";
        }

        return Response.ok(out).build();
    }

    @Path("update")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLibro(@FormParam("datosLibro") @DefaultValue("") String datosLibro) {
        LibrosController controladorLibro = new LibrosController();
        Gson gson = new Gson();
        String out = null;

        try {
            Libros libro = gson.fromJson(datosLibro, Libros.class);

            // Verifica si el libro recibido tiene un ID válido
            if (libro == null || libro.getIdLibro() == 0) {
                throw new IllegalArgumentException("Datos inválidos para actualizar el libro. El ID es necesario.");
            }

            // Aquí deberías llamar al método para actualizar el libro en la base de datos
            controladorLibro.actualizarLibro(libro);

            out = "{\"result\":\"Libro actualizado correctamente.\"}";
        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\":\"" + ex.toString().replaceAll("\"", "") + "\"}";
        }

        return Response.ok(out).build();
    }

}
