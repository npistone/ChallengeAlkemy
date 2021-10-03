
package disney.entidades;


import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


/**
 *
 * @author NPist
 */
@Entity
@Table(name="Pelicula")
public class Pelicula {

    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idPelicula")
    private Integer id;
    
    @Column(name="titulo")
    @NotEmpty(message="Debe ingresar un valor")
    private String titulo;
    
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Imagen imagen;
    
    @Column(name="calificacion")
    @Min(value=0)
    @Max(value=5)
    @NotEmpty(message="Debe ingresar un valor")
    private Integer calificacion;
    
    @Temporal (TemporalType.DATE)
    @Column(name="creacion")
    @NotEmpty(message="Debe ingresar un valor")
    private Date creacion;
    
    
    @ManyToMany
    @JoinTable(name = "personajes_xpelicula", joinColumns = 
        @JoinColumn(name = "pelicula_id"),
        inverseJoinColumns = @JoinColumn(name = "personaje_id"))
    private List<Personaje> personajes;
    
   @ManyToOne
   @JoinColumn(name="peliculas_xGenero")
    private Genero genero;

    public Pelicula() {
    }

    public Pelicula(Integer id, String titulo, Imagen imagen, Integer calificacion, Date creacion, List<Personaje> personajes, Genero genero) {
        this.id = id;
        this.titulo = titulo;
        this.imagen = imagen;
        this.calificacion = calificacion;
        this.creacion = creacion;
        this.personajes = personajes;
        this.genero = genero;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the imagen
     */
    public Imagen getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the calificacion
     */
    public Integer getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the creacion
     */
    public Date getCreacion() {
        return creacion;
    }

    /**
     * @param creacion the creacion to set
     */
    public void setCreacion(Date creacion) {
        this.creacion = creacion;
    }

    /**
     * @return the personajes
     */
    public List<Personaje> getPersonajes() {
        return personajes;
    }

    /**
     * @param personajes the personajes to set
     */
    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }

    /**
     * @return the genero
     */
    public Genero getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(Genero genero) {
        this.genero = genero;
    }

          
    

    
    
    
    
    

    
}
