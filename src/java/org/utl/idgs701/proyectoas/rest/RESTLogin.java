/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.idgs701.proyectoas.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.utl.idgs701.proyectoas.core.LibrosController;
import org.utl.idgs701.proyectoas.core.LoginController;
import org.utl.idgs701.proyectoas.core.UsuarioController;
import org.utl.idgs701.proyectoas.model.Libros;
import org.utl.idgs701.proyectoas.model.Usuario;

/**
 *
 * @author jesus
 */
@Path("login")
public class RESTLogin {

    @Path("validar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(
            @QueryParam("nombreUsuario") @DefaultValue("") String nombreUsuario,
            @QueryParam("contrasenia") @DefaultValue("") String contrasenia,
            @QueryParam("perfil") @DefaultValue("") String perfil){
        String out = null;
        LoginController cl = new LoginController();
        Gson gson = new Gson();
        Usuario usu = null;
        try {
            usu = cl.login(nombreUsuario, contrasenia, perfil);  
            if (usu != null) {
                out = gson.toJson(usu);
            } else {
                out = "{\"error\":\"Datos de acceso incorrectos.\"}";
            }
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception\":\"" + e.toString().replaceAll("\"", "") + "\"}";
        }
        return Response.ok(out).build();
    }
    
    @Path("getAllUsuario")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        String out;
        UsuarioController cl = new UsuarioController();
        Gson gson = new Gson();
        try {
            List<Usuario> usuario = cl.getAll();
            out = gson.toJson(usuario);
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception\":\"" + e.toString().replaceAll("\"", "") + "\"}";
        }
        return Response.ok(out).build();
    }
    
     @Path("saveUsuario")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveLibro(@FormParam("datosUsuario") @DefaultValue("") String datosUsuario) {
        UsuarioController uc = new UsuarioController();
        Gson gson = new Gson();
        String out = null;

        try {
            Usuario libro = gson.fromJson(datosUsuario, Usuario.class);
            if (libro == null) {
                throw new IllegalArgumentException("Datos inválidos para el libro");
            } else {
                uc.insert(libro);
            }

            out = "{\"result\":\"Libro registrado correctamente.\"}";
        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\":\"" + ex.toString().replaceAll("\"", "") + "\"}";
        }

        return Response.ok(out).build();
    }
    
    @Path("updateUsuario")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLibro(@FormParam("datosUsuario") @DefaultValue("") String datosUsuario) {
        UsuarioController controladorLibro = new UsuarioController();
        Gson gson = new Gson();
        String out = null;

        try {
            Usuario usuario = gson.fromJson(datosUsuario, Usuario.class);

            // Verifica si el libro recibido tiene un ID válido
            if (usuario == null || usuario.getIdUsuario() == 0) {
                throw new IllegalArgumentException("Datos inválidos para actualizar el libro. El ID es necesario.");
            }

            // Aquí deberías llamar al método para actualizar el libro en la base de datos
            controladorLibro.actualizarUsuario(usuario);

            out = "{\"result\":\"Libro actualizado correctamente.\"}";
        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\":\"" + ex.toString().replaceAll("\"", "") + "\"}";
        }

        return Response.ok(out).build();
    }

}
