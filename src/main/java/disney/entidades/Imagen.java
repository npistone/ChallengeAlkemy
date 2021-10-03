/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package disney.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;


/**
 *
 * @author NPist
 */
@Entity
@Table(name="Imagen")
public class Imagen {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idImagen")
    private Integer id;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="mime")
    private String mime;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name="contenido")
    private byte[] contenido;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
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
     * @return the mime
     */
    public String getMime() {
        return mime;
    }

    /**
     * @param mime the mime to set
     */
    public void setMime(String mime) {
        this.mime = mime;
    }

    /**
     * @return the contenido
     */
    public byte[] getContenido() {
        return contenido;
    }

    /**
     * @param contenido the contenido to set
     */
    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }
    
    
}
