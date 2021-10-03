/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package disney.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import disney.entidades.Pelicula;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author NPist
 */

public interface PeliculaRepositorio extends JpaRepository<Pelicula, Integer>, JpaSpecificationExecutor<Pelicula>{
    
    @Query(nativeQuery = true, value ="select a.nombre, a.imagen from Pelicula where a.nombre= :nombre "
            + "and a.genero.nombre= :genero order by a.creacion :orden ")
    public List<Pelicula>buscarPorNombreGeneroYOrden(@Param("nombre")String nombre,
                                                     @Param("orden")String orden,
                                                     @Param("genero")String genero);
    
    @Query("SELECT a FROM Pelicula a ORDER BY a.creacion DESC")
    List<Pelicula>findAllDesc();
    
    @Query("SELECT a FROM Pelicula a WHERE a.titulo= :nombre ORDER BY a.creacion ASC")
    List<Pelicula>buscarPorNombreAsc(@Param("nombre")String nombre);
    
    @Query("SELECT a FROM Pelicula a WHERE a.titulo= :nombre ORDER BY a.creacion DESC")
    List<Pelicula>buscarPorNombreDesc(@Param("nombre")String nombre);
    
   @Query("SELECT a FROM Pelicula a WHERE a.titulo= :nombre AND a.genero.nombre= :genero ORDER BY a.creacion DESC")
    List<Pelicula>buscarPorNombreYGeneroOrdenDes(@Param("nombre")String nombre,
                                                 @Param("genero")String genero);
    
    @Query("SELECT a FROM Pelicula a WHERE a.titulo= :nombre AND a.genero.nombre= :genero ORDER BY a.creacion ASC")
    List<Pelicula>buscarPorNombreYGeneroOrdenAsc(@Param("nombre")String nombre,
                                            @Param("genero")String genero);
    
        
    
    /*public interface UserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<Pelicula> {
         // Nombre el nombre del método de acuerdo con la palabra clave springData, sin escribir la clase de implementación
    List<User> findByNameAndAge(String name, Integer age);
 
    List<User> findByNameLikeAndAge(String name, Integer age);
         // Consulta la declaración JPQL en la anotación @Query para definir la consulta
    @Query(nativeQuery = false,value = " SELECT p FROM User p WHERE id = ?1")
    User readId(Integer id);
         // Las anotaciones de consulta también pueden definir sentencias SQL, siempre que la propiedad nativeQuery se cambie a true
    @Query(nativeQuery = true, value = "select name from user where id = :id")
    String findNamebyId(@Param("id")Integer id);
         @Modificación del método de modificación, dígale a SpringData que esta es una operación UPATE o DELETE
    // Agregar operaciones de transacción en el método de la capa de servicio
    @Modifying
    @Query(nativeQuery = true,value = "update user set name = ?1  where id = ?2 ")
    int updateUserNameById(String name,Integer id);
 
}*/
}
