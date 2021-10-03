/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package disney.controladores;

import disney.entidades.Pelicula;
import disney.repositorios.PeliculaRepositorio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author NPist
 */

@RestController
public class PeliculaControlador {
    
    @Autowired
    private PeliculaRepositorio peliRepo;
    @GetMapping("/movies")
  public ResponseEntity<List<Pelicula>> getMovies(
          @RequestParam(required = false) String titulo,
          @RequestParam(required = false) String genero,
          @RequestParam(required = false) String orden)
            {
    
                
    try {
        
      List<Pelicula> peliculas= new ArrayList<Pelicula>();
      if(titulo.isEmpty() && genero.isEmpty() && orden.isEmpty()){
           peliRepo.findAll().forEach(peliculas::add);
           return new ResponseEntity<>(peliculas, HttpStatus.OK);
      }
      if(!titulo.isEmpty() && genero.isEmpty() && orden.isEmpty()){
          peliRepo.findAll().forEach(peliculas::add);
      }
      
      if (peliculas.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      
      
      return new ResponseEntity<>(peliculas, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
            }
  
    
    
    
}
