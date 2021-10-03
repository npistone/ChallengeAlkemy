
package disney.entidades;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author NPist
 */

@Entity
@Table(name="Personaje")
public class Personaje {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idPersonaje")
    private Integer id;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "edad")
    private long edad;
    
    @Column(name = "peso")
    private float peso;
    
    @Column(name = "historia")
    private String historia;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Imagen imagen;
    
    
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "personajes")
    private List<Pelicula> pelicula;

    public Personaje() {
    }
    
    

    public Personaje(Integer id, String nombre, long edad, float peso, String historia, Imagen imagen) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.historia = historia;
        this.imagen = imagen;
        this.pelicula = new ArrayList<>();
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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the edad
     */
    public long getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(long edad) {
        this.edad = edad;
    }

    /**
     * @return the peso
     */
    public float getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(float peso) {
        this.peso = peso;
    }

    /**
     * @return the historia
     */
    public String getHistoria() {
        return historia;
    }

    /**
     * @param historia the historia to set
     */
    public void setHistoria(String historia) {
        this.historia = historia;
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
     * @return the pelicula
     */
    public List<Pelicula> getPelicula() {
        return pelicula;
    }

    /**
     * @param pelicula the pelicula to set
     */
    public void setPelicula(List<Pelicula> pelicula) {
        this.pelicula = pelicula;
    }


    
    
    
    
}
