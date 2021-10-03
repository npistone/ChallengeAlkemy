/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package disney.controladores;

import disney.entidades.Pelicula;
import disney.repositorios.PeliculaRepositorio;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author NPist
 */

@RestController
@RequestMapping(value ="/movies")
public class PeliculaControlador {
    
    @Autowired
    private PeliculaRepositorio peliRepo;
    
    
    
    
    @GetMapping
    public ResponseEntity<List<Pelicula>> getMovies(
          @RequestParam(required = false) String titulo,
          @RequestParam(required = false) String genero,
          @RequestParam(required = false) String orden)
            {
    
                
    try {
        
      List<Pelicula> peliculas= new ArrayList<Pelicula>();
      if(titulo.isEmpty() && genero.isEmpty() && orden.isEmpty() ){
           peliRepo.findAll().forEach(peliculas::add);
           return new ResponseEntity<>(peliculas, HttpStatus.OK);
      }
      
      /**
       * @return peliculas Orden Asc
       */
      if(titulo.isEmpty() && genero.isEmpty() && !orden.isEmpty() && orden.equalsIgnoreCase("asc")){
           peliRepo.findAll().forEach(peliculas::add);
           return new ResponseEntity<>(peliculas, HttpStatus.OK);
      }
      /**
       * @return peliculas Orden Desc
       */
      if(titulo.isEmpty() && genero.isEmpty() && !orden.isEmpty() && orden.equalsIgnoreCase("desc")){
           peliRepo.findAllDesc().forEach(peliculas::add);
           return new ResponseEntity<>(peliculas, HttpStatus.OK);
      }
       /**
       * @param Titulo
       * @return peliculas
       */
      if(!titulo.isEmpty() && genero.isEmpty() && orden.isEmpty()){
          peliRepo.buscarPorNombreAsc(titulo).forEach(peliculas::add);
          return new ResponseEntity<>(peliculas, HttpStatus.OK);
      }
      
      /**
       * @param Titulo
       * @param orden
       * @return peliculas
       */
      if(!titulo.isEmpty() && genero.isEmpty() && !orden.isEmpty() && orden.equalsIgnoreCase("ASC")){
          peliRepo.buscarPorNombreAsc(titulo).forEach(peliculas::add);
          return new ResponseEntity<>(peliculas, HttpStatus.OK);
      }
      /**
       * @param Titulo
       * @param orden
       * return peliculas
       */
      if(!titulo.isEmpty() && genero.isEmpty() && !orden.isEmpty() && orden.equalsIgnoreCase("DESC")){
          peliRepo.buscarPorNombreDesc(titulo).forEach(peliculas::add);
          return new ResponseEntity<>(peliculas, HttpStatus.OK);
      }
      
      /**
       * @Param Titulo
       * @Param Genero
       * @Param Orden
       * return peliculas
       * 
       */
      if(!titulo.isEmpty() && !genero.isEmpty() && !orden.isEmpty()){
          peliRepo.buscarPorNombreGeneroYOrden(titulo, orden, genero ).forEach(peliculas::add);
          return new ResponseEntity<>(peliculas, HttpStatus.OK);
      }    
                  
      if (peliculas.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      
      
      return new ResponseEntity<>(peliculas, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
  
    @GetMapping( value ="/{id}")
    public ResponseEntity<Pelicula> findById(@PathVariable Integer id){
        Optional<Pelicula> busca = peliRepo.findById(id);
        ResponseEntity<Pelicula> responseEntity= null;
        Pelicula pelicula = null;
        if(busca.isPresent()){
             pelicula =busca.get();
            responseEntity = new ResponseEntity<Pelicula>(pelicula, HttpStatus.OK);
            
            
        }else{
            responseEntity = new ResponseEntity<Pelicula>(pelicula, HttpStatus.NO_CONTENT);
            
        }
        
    return responseEntity;
    }
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody Pelicula pelicula,
                                           BindingResult resultado ){
        
        Map<String, Object> responseAsMap = new HashMap<String, Object>();
        List<String> errores = null;
        ResponseEntity<Map<String, Object>> responseEntity = null;
        
        if(resultado.hasErrors()){
            errores=new ArrayList<String>();
            for (ObjectError error : resultado.getAllErrors()) {
                errores.add(error.getDefaultMessage());
            }
        }
        if(errores !=null && errores.size() >0){
            responseAsMap.put("errors", errores);
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
        }else{
            Pelicula peliculaDb = peliRepo.save(pelicula);
            if(peliculaDb !=null){
                responseAsMap.put("pelicula", pelicula);
                responseAsMap.put("mensaje", "Pelicula creada correctamente, id "+pelicula.getId());
                responseEntity =new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.OK);
            }else{
                
              responseAsMap.put("mensaje", "Pelicula no ha sido creada");
              responseEntity =new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.INTERNAL_SERVER_ERROR);
                
            }

       }
     return responseEntity;  
    }
    
}
