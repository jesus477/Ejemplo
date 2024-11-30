package org.utl.idgs701.proyectoas.core;

import com.utl.idgs701.proyecto.appservice.LibroExternoAppService;
import com.utl.idgs701.proyectoas.comandos.LibrosCQRS;
import com.utl.idgs701.proyectoas.dao.LibrosDAO;
import com.utl.idgs701.proyectoas.mvvm.LibrosViewModel;
import java.util.ArrayList;
import java.util.List;
import org.utl.idgs701.proyectoas.model.Libros;

public class LibrosController {
    
    private final LibrosDAO librosDAO;
    private final LibrosCQRS librosCommand;
    private final LibroExternoAppService externalService;
    
    public LibrosController() {
        librosDAO = new LibrosDAO(); 
        librosCommand = new LibrosCQRS(); 
        externalService = new LibroExternoAppService();
    }

    public List<Libros> getAllLibros() throws Exception {
        return librosDAO.getAll(); 
    }
    
    public List<LibrosViewModel> getAllViewModel() throws Exception {
        List<Libros> libros = getAllLibros();  
        List<LibrosViewModel> viewModel = new ArrayList<>();  

        for (Libros libro : libros) {
            LibrosViewModel viewM = new LibrosViewModel(libro);  
            viewModel.add(viewM);  
        }
        return viewModel;
    }

    public List<LibrosViewModel> getAllFromExternalService() throws Exception {
        return externalService.getAllExterno(); 
    }

    public List<LibrosViewModel> getCombinedLibrosViewModel() throws Exception {
        List<LibrosViewModel> combinedViewModels = new ArrayList<>();

        List<LibrosViewModel> localViewModels = getAllViewModel();
        combinedViewModels.addAll(localViewModels); 

        List<LibrosViewModel> externalViewModels = getAllFromExternalService();
        combinedViewModels.addAll(externalViewModels); 

        return combinedViewModels;
    }
    
    public Libros getLibroById(int idLibro) throws Exception {
        return librosDAO.getById(idLibro); 
    }

    public void agregarLibro(Libros libro) throws Exception {
        librosCommand.insertarLibro(libro); 
    }

    public void actualizarLibro(Libros libro) throws Exception {
        librosCommand.updateLibro(libro); 
    }
}
